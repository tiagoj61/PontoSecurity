package pontosecurity.bo;

import javax.security.auth.login.LoginException;

import pontosecurity.bean.Usuario;
import pontosecurity.commons.IOperations;
import pontosecurity.exception.SenhaException;

/**
 *
 * @author links
 */
public interface IUsuarioBo extends IOperations<Usuario> {

    Usuario persistOrThrow(Usuario usuario) throws Exception;

    Usuario byEmail(String email);

    Usuario byHash(String hash);

    Usuario login(String email, String senha) throws LoginException, SenhaException;


}
