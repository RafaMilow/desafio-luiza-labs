package test.br.com.luizalabs.api.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.Ignore;
import org.junit.Test;

import br.com.luizalabs.api.to.ClienteRequest;

public class ClienteResourceTest extends AbstractResourceTest {
	
	private static final String USERNAME = "rafa";
	private static final String PASSWORD = "ana";
	private HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(USERNAME, PASSWORD);
	
	@Test
	@Ignore
	public void pingTest() {
		Response response = target("/ping").request().get();
		//System.out.println(response);
		String output = response.readEntity(String.class);
		//System.out.println(output);
		assertEquals("pong", output);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals(Response.Status.OK.toString(), response.getStatusInfo().toString());
	}

	@Test
	@Ignore
	public void listaCLientesTest() {
		Response response = target("/clientes").register(feature).request().get();
		//System.out.println(response);
		//String output = response.readEntity(String.class);
		//System.out.println(output);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals(Response.Status.OK.toString(), response.getStatusInfo().toString());
	}

	@Test
	@Ignore
	public void createClienteWithConstratintsTest() {
		ClienteRequest request = new ClienteRequest();
		request.setEmail("teste");
		request.setNome("");
		
		Response response = target("/clientes").register(feature).request().post(Entity.json(request));
		System.out.println(response);
		String output = response.readEntity(String.class);
		System.out.println("--> " + output);
		String location = response.getHeaderString(HttpHeaders.LOCATION);
		System.out.println(location);
		
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
		assertEquals(Response.Status.BAD_REQUEST.toString(), response.getStatusInfo().toString());
		assertTrue(output.contains("Endereço de e-mail invalido"));
		assertTrue(output.contains("Nome não pode ser vazio!"));
	}

	@Test
	@Ignore
	public void createClienteTest() {
		ClienteRequest request = new ClienteRequest();
		request.setEmail("rafael@brasilfx.com");
		request.setNome("Rafael Santos");
		
		Response response = target("/clientes").register(feature).request().post(Entity.json(request));
		//System.out.println(response);
		//String output = response.readEntity(String.class);
		//System.out.println("--> " + output);
		//String location = response.getHeaderString(HttpHeaders.LOCATION);
		//System.out.println(location);
		
		assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
		assertEquals(Response.Status.CREATED.toString(), response.getStatusInfo().toString());
		//assertTrue(output.contains("Endereço de e-mail invalido"));
		//assertTrue(output.contains("Nome não pode ser vazio!"));
	}

	@Test
	@Ignore
	public void getDetalheClienteTest() {
		Integer clientId = 19;
		Response response = target("/clientes/" + clientId).register(feature).request().get();
		System.out.println(response);
		String output = response.readEntity(String.class);
		System.out.println("--> " + output);
		//assertEquals("GET: " + clientId, output);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals(Response.Status.OK.toString(), response.getStatusInfo().toString());
	}

	@Test
	//@Ignore
	public void deleteClienteTest() {
		Integer clientId = 700;
		Response response = target("/clientes/" + clientId).register(feature).request().delete();
		System.out.println(response);
		String output = response.readEntity(String.class);
		System.out.println("--> " + output);
		assertEquals("GET: " + clientId, output);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals(Response.Status.OK.toString(), response.getStatusInfo().toString());
	}
	

	
	@Test
	@Ignore
	public void putClienteWithDataTest() {
		ClienteRequest request = new ClienteRequest();
		request.setEmail("rafael.sa@bfx.com");
		request.setNome("rafa");
		
		Response response = target("/clientes/100").register(feature).request().put(Entity.json(request));
		System.out.println(response);
		String output = response.readEntity(String.class);
		System.out.println("--> " + output);
		
		
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals(Response.Status.OK.toString(), response.getStatusInfo().toString());
	}


}
