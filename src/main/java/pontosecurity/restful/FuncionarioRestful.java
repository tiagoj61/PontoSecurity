package pontosecurity.restful;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pontosecurity.bean.Funcionario;
import pontosecurity.bo.IFuncionarioBo;

@Component
@Path("/funcionario")
public class FuncionarioRestful {

	@Autowired
	private IFuncionarioBo funcionarioBo;

	@Path("login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(Funcionario funcionario) {
		try {
			return Response.status(Response.Status.FORBIDDEN).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Path("logout")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response logout(Funcionario funcionario) {
		try {
			return Response.status(Response.Status.FORBIDDEN).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
