package test.br.com.luizalabs.api.snips;

import java.util.List;

import org.junit.Test;

import br.com.luizalabs.api.jdbi.impl.ClientesDAOImpl;
import br.com.luizalabs.api.to.ClienteRequest;

public class DBConnectionTest {

	@Test
	//@Ignore
	public void simpleQueryImplTest() {
		ClientesDAOImpl impl = new ClientesDAOImpl();
		List<ClienteRequest> lista = impl.getList();
		
		lista.forEach(s -> System.out.println(s));
		
	}
	
}
