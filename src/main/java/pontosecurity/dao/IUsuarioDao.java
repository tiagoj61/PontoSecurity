/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pontosecurity.dao;

import pontosecurity.bean.Usuario;
import pontosecurity.commons.IOperations;

/**
 *
 * @author links
 */
public interface IUsuarioDao extends IOperations<Usuario> {

	Usuario byEmail(String email);

	Usuario byHash(String hash);

}
