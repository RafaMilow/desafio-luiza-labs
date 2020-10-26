package test.com.br.luizalabs.api.snips;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.junit.Ignore;
import org.junit.Test;

import com.br.luizalabs.api.service.impl.ProductClient;
import com.br.luizalabs.api.to.ProductTO;

public class JerseyClientTest {

	private static String URL = "https://5f3589525b91f60016ca4ee6.mockapi.io";
	private static String PATH = "/api/product/";
	
	@Test
	@Ignore
	public void getSingleDataFromAPITest() {
		
		int productId = 1;
		String productPath = PATH + productId;
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL).path(productPath);
	
		
		ProductTO product = target.request(MediaType.APPLICATION_JSON).get(ProductTO.class);
		System.out.println("--> " + product);
		
	}
	
	@Test
	public void getProductTest() {
		try {
			ProductTO product1 = ProductClient.getProductDetails(1);
			System.out.println("P: "+ product1);	
			ProductTO product2 = ProductClient.getProductDetails(1);
			System.out.println("P: "+ product2);
		} catch (javax.ws.rs.NotFoundException e) {
			System.out.println("Nao encontrado!");
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
