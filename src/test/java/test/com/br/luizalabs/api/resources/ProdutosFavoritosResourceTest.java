package test.com.br.luizalabs.api.resources;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.Ignore;
import org.junit.Test;

import com.br.luizalabs.api.to.ProdutosFavoritosTO;

public class ProdutosFavoritosResourceTest extends AbstractResourceTest {

	private static final String USERNAME = "rafa";
	private static final String PASSWORD = "ana";
	private HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(USERNAME, PASSWORD);

	@Test
	@Ignore
	public void listaProdutosFavoritosTest() {
		Response response = target("/clientes/1/produtos-favoritos").register(feature).request().get();
		System.out.println(response);
		String output = response.readEntity(String.class);
		System.out.println(output);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals(Response.Status.OK.toString(), response.getStatusInfo().toString());
	}

	@Test
	//@Ignore
	public void createItemNalistaTest() {
		ProdutosFavoritosTO produto = new ProdutosFavoritosTO();
		produto.setProductId(6);
		Response response = target("/clientes/1/produtos-favoritos").register(feature).request()
				.post(Entity.json(produto));
		System.out.println(response);
		String output = response.readEntity(String.class);
		System.out.println(output);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals(Response.Status.OK.toString(), response.getStatusInfo().toString());
	}

	@Test
	@Ignore
	public void deleteOneTest() {
		Integer clientId = 1;
		Response response = target("/clientes/1/produtos-favoritos/5").register(feature).request().delete();
		System.out.println(response);
		String output = response.readEntity(String.class);
		System.out.println("--> " + output);
		assertEquals("GET: " + clientId, output);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals(Response.Status.OK.toString(), response.getStatusInfo().toString());
	}

	@Test
	@Ignore
	public void deleteAllTest() {
		Integer clientId = 1;
		Response response = target("/clientes/1/produtos-favoritos/").register(feature).request().delete();
		System.out.println(response);
		String output = response.readEntity(String.class);
		System.out.println("--> " + output);
		assertEquals("GET: " + clientId, output);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals(Response.Status.OK.toString(), response.getStatusInfo().toString());
	}
}
