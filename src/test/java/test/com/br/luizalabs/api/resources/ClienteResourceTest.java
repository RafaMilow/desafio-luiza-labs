package test.com.br.luizalabs.api.resources;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Ignore;
import org.junit.Test;

import com.br.luizalabs.api.to.ClienteRequest;

public class ClienteResourceTest extends AbstractResourceTest {
	
	private static final String USERNAME = "rafa";
	private static final String PASSWORD = "ana";
	private HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(USERNAME, PASSWORD);
	
	public ClienteResourceTest() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
    }
	
	@Test
	@Ignore
	public void pingTest() {
		Response response = target("/clientes/ping").register(feature).request().get();
		
		System.out.println(response);
		String output = response.readEntity(String.class);
		System.out.println(output);
		assertEquals("pong", output);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals(Response.Status.OK.toString(), response.getStatusInfo().toString());
	}

	@Test
	@Ignore
	public void listaCLientesTest() {
		Response response = target("/clientes").register(feature).request().get();
		System.out.println(response);
		String output = response.readEntity(String.class);
		System.out.println(output);
		//assertEquals("GET LISTA", output);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals(Response.Status.OK.toString(), response.getStatusInfo().toString());
	}

	@Test
	//@Ignore
	public void getDetalheClienteTest() {
		Integer clientId = 1;
		Response response = target("/clientes/1").register(feature).request().get();
		System.out.println(response);
		String output = response.readEntity(String.class);
		System.out.println("--> " + output);
		//assertEquals("GET: " + clientId, output);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals(Response.Status.OK.toString(), response.getStatusInfo().toString());
	}

	@Test
	@Ignore
	public void postClienteTest() {
		Integer clientId = 1;
		Response response = target("/clientes/" + clientId).request().get();
		System.out.println(response);
		String output = response.readEntity(String.class);
		System.out.println("--> " + output);
		assertEquals("GET: " + clientId, output);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals(Response.Status.OK.toString(), response.getStatusInfo().toString());
	}

	@Test
	@Ignore
	public void deleteClienteTest() {
		Integer clientId = 7;
		Response response = target("/clientes/" + clientId).request().delete();
		System.out.println(response);
		String output = response.readEntity(String.class);
		System.out.println("--> " + output);
		assertEquals("GET: " + clientId, output);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals(Response.Status.OK.toString(), response.getStatusInfo().toString());
	}
	
	@Test
	@Ignore
	public void postClienteWithDataTest() {
		ClienteRequest request = new ClienteRequest();
		request.setEmail("bolovo.sa@bfx.com");
		request.setNome("Milowo");
		
		Response response = target("/clientes").request().post(Entity.json(request));
		System.out.println(response);
		String output = response.readEntity(String.class);
		System.out.println("--> " + output);
		
		
		assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
		assertEquals(Response.Status.CREATED.toString(), response.getStatusInfo().toString());
	}
	
	@Test
	@Ignore
	public void putClienteWithDataTest() {
		ClienteRequest request = new ClienteRequest();
		request.setEmail("rafael.sa@bfx.com");
		request.setNome("Rafa");
		
		Response response = target("/clientes/1").request().put(Entity.json(request));
		System.out.println(response);
		String output = response.readEntity(String.class);
		System.out.println("--> " + output);
		
		
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals(Response.Status.OK.toString(), response.getStatusInfo().toString());
	}


}
