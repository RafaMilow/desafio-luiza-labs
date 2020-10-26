package test.com.br.luizalabs.api.snips;

import java.util.List;

import org.junit.Test;

import com.br.luizalabs.api.jdbi.impl.ClientesDAOImpl;
import com.br.luizalabs.api.to.ClienteRequest;

public class DBConnectionTest {

	@Test
	//@Ignore
	public void simpleQueryImplTest() {
		ClientesDAOImpl impl = new ClientesDAOImpl();
		List<ClienteRequest> lista = impl.getList();
		
		lista.forEach(s -> System.out.println(s));
		
	}
	
}
