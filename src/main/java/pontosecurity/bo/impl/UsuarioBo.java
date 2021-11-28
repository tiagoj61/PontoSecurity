/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pontosecurity.bo.impl;

import java.util.Calendar;

import javax.persistence.EntityNotFoundException;
import javax.security.auth.login.LoginException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pontosecurity.bean.Usuario;
import pontosecurity.bo.IUsuarioBo;
import pontosecurity.commons.AbstractService;
import pontosecurity.commons.IOperations;
import pontosecurity.dao.IUsuarioDao;
import pontosecurity.exception.DuplicateConstraintViolationException;
import pontosecurity.exception.SenhaException;

/**
 *
 * @author links
 */
@Service
public class UsuarioBo extends AbstractService<Usuario> implements IUsuarioBo {

    @Autowired
    private IUsuarioDao dao;

    @Override
    protected IOperations<Usuario> getDao() {
        return dao;
    }

    @Override
    public Usuario persistOrThrow(Usuario usuario) throws Exception {
        var keepOldPwd = false;
        if (usuario == null) {
            throw new EntityNotFoundException(Usuario.class.getSimpleName());
        }
        if (usuario.getId() != null) {
            var entity = dao.load(usuario.getId());
            if (StringUtils.isBlank(usuario.getSenha())) {
                usuario.setSenha(entity.getSenha());
                keepOldPwd = true;
            }
        } else {
            if (dao.byEmail(usuario.getEmail()) != null) {
                throw new DuplicateConstraintViolationException("O login/e-mail informado já está associado a outro usuário.");
            }
        }
        if (!keepOldPwd) {
            usuario.setSenha(DigestUtils.sha512Hex(usuario.getSenha()));
        }
        return super.persist(usuario);
    }

    @Override
    public Usuario login(String email, String senha) throws LoginException, SenhaException {
        var usuario = dao.byEmail(email);
        if (usuario == null) {
            throw new LoginException();
        }
        if (!usuario.getSenha().equalsIgnoreCase(senha)) {
            throw new SenhaException();
        }
        usuario.setUltimoAcesso(Calendar.getInstance().getTime());
        usuario.setHash(DigestUtils.md5Hex(String.valueOf(Calendar.getInstance().getTimeInMillis())));
        return dao.persist(usuario);
    }

    @Override
    public Usuario byHash(String hash) {
        return dao.byHash(hash);
    }

    @Override
    public Usuario byEmail(String email) {
        return dao.byEmail(email);
    }


}
