package br.com.luizalabs.api.commons;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

@Provider
@Authentication
public class AuthenticationFilter implements ContainerRequestFilter {

	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHENTICATION_HEADER_PREFIX = "Basic";
	private static final String AUTH_FILE_NAME = "auth.properties";
	private static final Properties PROPS = PropertiesLoader.get(AUTH_FILE_NAME);
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		MultivaluedMap<String, String> headers = requestContext.getHeaders();
		List<String> authorization = headers.get(AUTHORIZATION_HEADER_KEY);
		Response unAuthorizedStatus = Response.status(Response.Status.UNAUTHORIZED).entity("NAO AUTORIZADO").build();
		
		if (authorization == null || authorization.isEmpty()) {
			requestContext.abortWith(unAuthorizedStatus);
			return;
		}

		String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_HEADER_PREFIX + " ", "");
		String usernamePassword = new String(Base64.decode(encodedUserPassword.getBytes()));
		StringTokenizer tokenizer = new StringTokenizer(usernamePassword, ":");
		String username = tokenizer.nextToken();
		String password = tokenizer.nextToken();

		if (PROPS.getProperty("auth.user").equals(username) && PROPS.getProperty("auth.pass").equals(password)) {
			return;
		}

		requestContext.abortWith(unAuthorizedStatus);
		return;
	}
}
