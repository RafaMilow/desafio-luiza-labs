package test.br.com.luizalabs.api.snips;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.junit.Ignore;
import org.junit.Test;

import br.com.luizalabs.api.to.ProductTO;

public class JerseyClientTest {

	private static String URL = "https://5f3589525b91f60016ca4ee6.mockapi.io";
	private static String PATH = "/api/product/";
	
	@Test
	//@Ignore
	public void getSingleDataFromAPITest() {
		
		int productId = 18888;
		String productPath = PATH + productId;
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL).path(productPath);
	
		try {
			ProductTO product = target.request(MediaType.APPLICATION_JSON).get(ProductTO.class);
			System.out.println("--> " + product);
		} catch (javax.ws.rs.NotFoundException e) {
			System.out.println("Moio....");
		} catch (javax.ws.rs.ProcessingException pe) {
			System.out.println("Moio....2");
		}
		
		
	}
	
	
	@Test
	@Ignore
	public void getDataFromAPITest() {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL).path(PATH);
	
		
		List<ProductTO> ListOfProduct = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<ProductTO>>(){});
		System.out.println("--> " + ListOfProduct);
		
	}
}
