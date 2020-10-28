package br.com.luizalabs.api.app;

import javax.inject.Singleton;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import br.com.luizalabs.api.jdbi.impl.ClientesDAOImpl;
import br.com.luizalabs.api.jdbi.impl.ProdutosFavoritosDAOImpl;
import br.com.luizalabs.api.jdbi.interfaces.ClientesDAO;
import br.com.luizalabs.api.jdbi.interfaces.ProdutosFavoritosDAO;
import br.com.luizalabs.api.service.impl.ClienteImpl;
import br.com.luizalabs.api.service.impl.ProdutosFavoritosImpl;
import br.com.luizalabs.api.service.interfaces.Cliente;
import br.com.luizalabs.api.service.interfaces.ProdutosFavoritos;

public class ApplicationBinder extends AbstractBinder {

	@Override
	protected void configure() {
		bind(ClienteImpl.class).to(Cliente.class).in(Singleton.class);
		bind(ProdutosFavoritosImpl.class).to(ProdutosFavoritos.class).in(Singleton.class);
		bind(ClientesDAOImpl.class).to(ClientesDAO.class).in(Singleton.class);
		bind(ProdutosFavoritosDAOImpl.class).to(ProdutosFavoritosDAO.class).in(Singleton.class);
	}

}
