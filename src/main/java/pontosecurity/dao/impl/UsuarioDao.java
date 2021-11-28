/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pontosecurity.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import pontosecurity.bean.Usuario;
import pontosecurity.commons.AbstractHibernateDao;
import pontosecurity.dao.IUsuarioDao;

/**
 *
 * @author links
 */
@Repository
public class UsuarioDao extends AbstractHibernateDao<Usuario> implements IUsuarioDao {

    public UsuarioDao() {
        super(Usuario.class);
    }

    private Usuario by(String key, String value) {
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
        Root<Usuario> root = cq.from(Usuario.class);
        cq.where(
                cb.equal(cb.lower(root.get(key)), StringUtils.trimToEmpty(value).toLowerCase()),
                cb.equal(root.get("ativo"), true)
        );
        return getSingleResultOrNull(cq);
    }

    @Override
    public Usuario byEmail(String email) {
        return this.by("email", email);
    }

    @Override
    public Usuario byHash(String hash) {
        return this.by("hash", hash);
    }



}
