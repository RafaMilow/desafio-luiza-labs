package test.com.br.luizalabs.api.resources;

import javax.inject.Singleton;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.test.DeploymentContext;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.ServletDeploymentContext;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerFactory;

import com.br.luizalabs.api.commons.AuthenticationFilter;
import com.br.luizalabs.api.exceptions.GenericExceptionMapper;
import com.br.luizalabs.api.jdbi.impl.ClientesDAOImpl;
import com.br.luizalabs.api.jdbi.impl.ProdutosFavoritosDAOImpl;
import com.br.luizalabs.api.jdbi.interfaces.ClientesDAO;
import com.br.luizalabs.api.jdbi.interfaces.ProdutosFavoritosDAO;
import com.br.luizalabs.api.resources.ClienteResource;
import com.br.luizalabs.api.resources.ProdutosFavoritosResource;
import com.br.luizalabs.api.service.impl.ClienteImpl;
import com.br.luizalabs.api.service.impl.ProdutosFavoritosImpl;
import com.br.luizalabs.api.service.interfaces.Cliente;
import com.br.luizalabs.api.service.interfaces.ProdutosFavoritos;

public abstract class AbstractResourceTest extends JerseyTest {

	@Override
	public ResourceConfig configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		ResourceConfig config = new ResourceConfig()
				.register(AuthenticationFilter.class)
				.register(ClienteResource.class)
				.register(ProdutosFavoritosResource.class)
				.register(GenericExceptionMapper.class);
				//.register(UncaughtException.class);
		
		config.register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(ClienteImpl.class).to(Cliente.class).in(Singleton.class);
				bind(ProdutosFavoritosImpl.class).to(ProdutosFavoritos.class).in(Singleton.class);
				bind(ClientesDAOImpl.class).to(ClientesDAO.class).in(Singleton.class);
				bind(ProdutosFavoritosDAOImpl.class).to(ProdutosFavoritosDAO.class).in(Singleton.class);
			}
		});
		return config;
	}

	@Override
	public TestContainerFactory getTestContainerFactory() {
		return new GrizzlyWebTestContainerFactory();
	}

	@Override
	public DeploymentContext configureDeployment() {
		return ServletDeploymentContext.forServlet(new ServletContainer(configure())).servletPath("/v1").build();
	}
	
}
