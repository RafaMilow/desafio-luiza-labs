package br.com.luizalabs.api.app;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import br.com.luizalabs.api.commons.AuthenticationFilter;
import br.com.luizalabs.api.resources.ClienteResource;
import br.com.luizalabs.api.resources.ProdutosFavoritosResource;

@ApplicationPath("/v1/*")
public class Application extends ResourceConfig {
	public Application() {
		String packageName = this.getClass().getPackage().getName();
		String p = packageName.substring(0, packageName.lastIndexOf("."));
		packages(true, p)
		.register(AuthenticationFilter.class)
		.register(ClienteResource.class)
		.register(ProdutosFavoritosResource.class)
		.register(new ApplicationBinder());
	}

}
