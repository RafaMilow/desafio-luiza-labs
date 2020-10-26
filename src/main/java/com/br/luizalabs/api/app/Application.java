package com.br.luizalabs.api.app;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import com.br.luizalabs.api.commons.AuthenticationFilter;
import com.br.luizalabs.api.resources.ClienteResource;
import com.br.luizalabs.api.resources.ProdutosFavoritosResource;

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
