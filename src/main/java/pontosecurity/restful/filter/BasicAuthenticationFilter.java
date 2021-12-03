package pontosecurity.restful.filter;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

public class BasicAuthenticationFilter implements Filter {

	public static final String AUTHENTICATION_HEADER = "Authorization";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
			throws IOException, ServletException {

		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			String authCredentials = httpServletRequest.getHeader(AUTHENTICATION_HEADER);

			boolean authenticationStatus = authenticate(request.getServletContext(), authCredentials);
System.out.println(authenticationStatus);
			if (authenticationStatus) {
				filter.doFilter(request, response);
			} else {
				if (response instanceof HttpServletResponse) {
					HttpServletResponse httpServletResponse = (HttpServletResponse) response;
					httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				}
			}
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	public boolean authenticate(ServletContext context, String authCredentials) {
		try {
			if (null == authCredentials) {
				return false;
			}
			final String encodedUserPassword = authCredentials.replaceFirst("Basic" + " ", "");
			final byte[] decodedBytes = Base64.decodeBase64(encodedUserPassword);
			final String pwd = new String(decodedBytes, "UTF-8");
			final StringTokenizer tokenizer = new StringTokenizer(pwd, ":");
			final String user = (tokenizer.nextToken());
			final String pass = tokenizer.nextToken();
			System.out.println(user);
			System.out.println(pass);
			if (user.equals("tiago") && pass.equals("senha"))
				return true;

			return false;
		} catch (Exception e) {
			System.err.println("Erro ao tentar autorizar o uso do webservice");
			e.printStackTrace();
			return false;
		}
	}

}