/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pontosecurity.restful;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pontosecurity.bean.Usuario;
import pontosecurity.bo.IUsuarioBo;

/**
 *
 * @author links
 */
@Component
@Path("/usuario")
public class UsuarioRestful {

	@Autowired
	private IUsuarioBo iUsuarioBo;

	@Path("login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(Usuario usuario) {
		try {
			return Response.status(Response.Status.FORBIDDEN).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

}
