package pontosecurity.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.opensymphony.xwork2.ActionContext;

import pontosecurity.bean.Funcionario;
import pontosecurity.bo.IFuncionarioBo;
import pontosecurity.commons.GenericAction;
import pontosecurity.util.JsonReturn;

public class FuncionarioAction extends GenericAction {

    private Funcionario funcionario;
    private List<Funcionario> funcionarios;
    @Autowired
    private IFuncionarioBo funcionarioBo;

    @Action(value = "prepareFuncionario",
            interceptorRefs = {
                @InterceptorRef(value = "basicStack")},
            results = {
                @Result(name = ERROR, location = "/app/notify/"),
                @Result(name = SUCCESS, location = "/app/funcionario/formulario.jsp")
            })
    public String preparar() {
        try {
            isLogged(request);
            if (this.funcionario != null && this.funcionario.getId() != null) {
                this.funcionario = this.funcionarioBo.load(this.funcionario.getId());
            }

            return SUCCESS;
        } catch (Exception e) {
            addActionError(" Erro: " + e.getMessage());
            return ERROR;
        }
    }

    @Action(value = "persistFuncionario",
            interceptorRefs = {
                @InterceptorRef(value = "basicStack")},
            results = {
                @Result(name = SUCCESS, location = "/app/notify/"),
                @Result(name = ERROR, location = "/app/notify/")
            })
    public String persist() {
        try {
            isLogged(request);
            this.funcionarioBo.persist(funcionario);

            addActionMessage("Registro salvo com sucesso.");
            setRedirectURL("listFuncionario");
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Erro ao processar o registro [persist]. Causa: " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    @Action(value = "deleteFuncionario",
            interceptorRefs = {
                @InterceptorRef(value = "basicStack")},
            results = {
                @Result(name = SUCCESS, location = "/app/notify/")
            })
    public String delete() {
        try {
            isLogged(request);
            funcionarioBo.delete(funcionario.getId());
            addActionMessage("Registro deletado com sucesso.");
            setRedirectURL("listFuncionario");
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            addActionError("Erro de violação de segurança.<br/>Você não pode excluir esse registro pois ele já está sendo utilizado e/ou atrelado a outro registro do banco de dados.");
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Erro: " + e.getMessage());
        }
        return SUCCESS;
    }

    @Action(value = "listFuncionario",
            interceptorRefs = {
                @InterceptorRef(value = "basicStack")},
            results = {
                @Result(name = ERROR, location = "/app/notify/"),
                @Result(name = SUCCESS, location = "/app/funcionario/")
            })
    public String list() {
        try {
            isLogged(request);
            funcionarios = funcionarioBo.listall();
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Erro: " + e.getMessage());
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
            Funcionario entity = this.funcionarioBo.load(getLogged().getId());
            entity.setHash(null);
            this.funcionarioBo.persist(entity);
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
    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    @Override
    public JsonReturn getJsonReturn() {
        return super.getJsonReturn(); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void prepare() throws Exception {
        super.setUserMenu(Funcionario.class.getSimpleName());
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
