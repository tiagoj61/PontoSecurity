/*
' * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pontosecurity.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.opensymphony.xwork2.ActionContext;

import pontosecurity.bean.Usuario;
import pontosecurity.bo.IUsuarioBo;
import pontosecurity.commons.GenericAction;
import pontosecurity.exception.AccessDeniedException;
import pontosecurity.exception.SenhaException;
import pontosecurity.util.JsonReturn;

/**
 *
 * @author links
 */
public class UsuarioAction extends GenericAction {

    private Usuario usuario;
    private List<Usuario> usuarios;
    @Autowired
    private IUsuarioBo iUsuarioBo;

    @Action(value = "resurrectLogin",
            interceptorRefs = {
                @InterceptorRef(value = "basicStack")},
            results = {
                @Result(name = ERROR, location = "/app/home/login.jsp"),
                @Result(name = INPUT, location = "/app/home/login.jsp"),
                @Result(name = SUCCESS, type = "redirectAction", params = {"actionName", "dashboard"})
            })
    public String resurrectLogin() {
        try {
            String hash = testarCookie();
            if (hash == null) {
                return INPUT;
            } else {
                usuario = iUsuarioBo.byHash(hash);
                if (usuario == null) {
                    return INPUT;
                }
                Map session = ActionContext.getContext().getSession();
                session.put("logged", usuario);

                return SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Erro ao tentar logar. " + e.getMessage());
            return ERROR;
        }
    }

    @Action(value = "prepareUsuario",
            interceptorRefs = {
                @InterceptorRef(value = "basicStack")},
            results = {
                @Result(name = ERROR, location = "/app/notify/"),
                @Result(name = SUCCESS, location = "/app/usuario/formulario.jsp")
            })
    public String preparar() {
        try {
            isLogged(request);
            if (this.usuario != null && this.usuario.getId() != null) {
                this.usuario = this.iUsuarioBo.load(this.usuario.getId());
            }

            return SUCCESS;
        } catch (Exception e) {
            addActionError(" Erro: " + e.getMessage());
            return ERROR;
        }
    }

    @Action(value = "persistUsuario",
            interceptorRefs = {
                @InterceptorRef(value = "basicStack")},
            results = {
                @Result(name = SUCCESS, location = "/app/notify/"),
                @Result(name = ERROR, location = "/app/notify/")
            })
    public String persist() {
        try {
            isLogged(request);
            this.iUsuarioBo.persistOrThrow(usuario);

            addActionMessage("Registro salvo com sucesso.");
            setRedirectURL("listUsuario");
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Erro ao processar o registro [persist]. Causa: " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    @Action(value = "deleteUsuario",
            interceptorRefs = {
                @InterceptorRef(value = "basicStack")},
            results = {
                @Result(name = SUCCESS, location = "/app/notify/")
            })
    public String delete() {
        try {
            isLogged(request);
            iUsuarioBo.delete(usuario.getId());
            addActionMessage("Registro deletado com sucesso.");
            setRedirectURL("listUsuario");
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            addActionError("Erro de violação de segurança.<br/>Você não pode excluir esse registro pois ele já está sendo utilizado e/ou atrelado a outro registro do banco de dados.");
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Erro: " + e.getMessage());
        }
        return SUCCESS;
    }

    @Action(value = "listUsuario",
            interceptorRefs = {
                @InterceptorRef(value = "basicStack")},
            results = {
                @Result(name = ERROR, location = "/app/notify/"),
                @Result(name = SUCCESS, location = "/app/usuario/")
            })
    public String list() {
        try {
            isLogged(request);
            usuarios = iUsuarioBo.listall();
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Erro: " + e.getMessage());
            return ERROR;
        }
    }

    @Action(value = "login",
            interceptorRefs = {
                @InterceptorRef(value = "token"),
                @InterceptorRef(value = "basicStack")},
            results = {
                @Result(name = ERROR, location = "/app/home/login.jsp"),
                @Result(name = SUCCESS, type = "redirectAction", params = {"actionName", "dashboard"}),
                @Result(name = "invalid.token", location = "/app/home/login.jsp")
            })
    public String login() {
        Map session = ActionContext.getContext().getSession();
        try {
            if (session.get("tentativa") != null && ((Integer) session.get("tentativa") >= 3)) {
            }
            usuario = iUsuarioBo.login(usuario.getEmail(), DigestUtils.sha512Hex(usuario.getSenha()));
            if (usuario == null) {
                throw new AccessDeniedException();
            }

            session.put("logged", this.usuario);
            if (request.getParameter("keepConnect") != null) {
                if (request.getParameter("keepConnect").equals("true")) {
                    Cookie biscoito = new Cookie("uid", usuario.getHash());
                    biscoito.setPath("/");
                    biscoito.setMaxAge(60 * 60 * 24 * 14);
                    biscoito.setHttpOnly(true);
                    response.addCookie(biscoito);
                }
            } else {
                Cookie[] biscoitos = request.getCookies();
                if (biscoitos != null) {
                    Cookie wafe;
                    for (Cookie biscoito : biscoitos) {
                        wafe = biscoito;
                        wafe.setPath("/");
                        if (wafe.getName().equals("uid")) {
                            wafe.setMaxAge(0);
                            response.addCookie(wafe);
                        }
                    }
                }
            }

            return SUCCESS;
        } catch (AccessDeniedException | SenhaException e) {
            int tentativas = 1;
            if (session.get("tentativa") != null) {
                tentativas = (Integer) session.get("tentativa");
                tentativas++;
            }
            session.put("tentativa", tentativas);

            addActionError("Erro ao tentar logar. " + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Erro ao tentar logar. " + e.getMessage());
            return ERROR;
        }
    }

    @Action(value = "logout",
            interceptorRefs = {
                @InterceptorRef(value = "basicStack")},
            results = {
                @Result(name = SUCCESS, location = "/app/home/login.jsp")
            })
    public String logout() {
        try {
            Usuario entity = this.iUsuarioBo.load(getLogged().getId());
            entity.setHash(null);
            this.iUsuarioBo.persist(entity);
        } catch (Exception e) {
            System.out.println("Erro ao tentar apagar o HASH do usuário que foi deslogado");
        }

        Map session = ActionContext.getContext().getSession();
        session.remove("logged");

        Cookie[] biscoitos = request.getCookies();
        if (biscoitos != null) {
            Cookie wafe;
            for (Cookie biscoito : biscoitos) {
                wafe = biscoito;
                wafe.setPath("/");
                if (wafe.getName().equals("uid")) {
                    wafe.setMaxAge(0);
                    response.addCookie(wafe);
                }
            }
        }

        return SUCCESS;
    }


    @JSON(serialize = false)
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public JsonReturn getJsonReturn() {
        return super.getJsonReturn(); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void prepare() throws Exception {
        super.setUserMenu(Usuario.class.getSimpleName());
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        GenericAction.request = hsr;
    }

    @Override
    public void setServletResponse(HttpServletResponse hsr) {
        GenericAction.response = hsr;
    }

}
