package test.br.com.luizalabs.api.resources;

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

import br.com.luizalabs.api.commons.AuthenticationFilter;
import br.com.luizalabs.api.exceptions.ConstraintViolationMapper;
import br.com.luizalabs.api.exceptions.GenericExceptionMapper;
import br.com.luizalabs.api.exceptions.UncaughtException;
import br.com.luizalabs.api.jdbi.impl.ClientesDAOImpl;
import br.com.luizalabs.api.jdbi.impl.ProdutosFavoritosDAOImpl;
import br.com.luizalabs.api.jdbi.interfaces.ClientesDAO;
import br.com.luizalabs.api.jdbi.interfaces.ProdutosFavoritosDAO;
import br.com.luizalabs.api.resources.ClienteResource;
import br.com.luizalabs.api.resources.ProdutosFavoritosResource;
import br.com.luizalabs.api.service.impl.ClienteImpl;
import br.com.luizalabs.api.service.impl.ProdutosFavoritosImpl;
import br.com.luizalabs.api.service.interfaces.Cliente;
import br.com.luizalabs.api.service.interfaces.ProdutosFavoritos;

public abstract class AbstractResourceTest extends JerseyTest {

	@Override
	public ResourceConfig configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		ResourceConfig config = new ResourceConfig()
				.register(AuthenticationFilter.class)
				.register(ClienteResource.class)
				.register(ProdutosFavoritosResource.class)
				.register(GenericExceptionMapper.class)
				.register(UncaughtException.class)
				.register(ConstraintViolationMapper.class);
		
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
