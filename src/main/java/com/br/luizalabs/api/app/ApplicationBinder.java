package com.br.luizalabs.api.app;

import javax.inject.Singleton;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.br.luizalabs.api.jdbi.impl.ClientesDAOImpl;
import com.br.luizalabs.api.jdbi.impl.ProdutosFavoritosDAOImpl;
import com.br.luizalabs.api.jdbi.interfaces.ClientesDAO;
import com.br.luizalabs.api.jdbi.interfaces.ProdutosFavoritosDAO;
import com.br.luizalabs.api.service.impl.ClienteImpl;
import com.br.luizalabs.api.service.impl.ProdutosFavoritosImpl;
import com.br.luizalabs.api.service.interfaces.Cliente;
import com.br.luizalabs.api.service.interfaces.ProdutosFavoritos;

public class ApplicationBinder extends AbstractBinder {

	@Override
	protected void configure() {
		bind(ClienteImpl.class).to(Cliente.class).in(Singleton.class);
		bind(ProdutosFavoritosImpl.class).to(ProdutosFavoritos.class).in(Singleton.class);
		bind(ClientesDAOImpl.class).to(ClientesDAO.class).in(Singleton.class);
		bind(ProdutosFavoritosDAOImpl.class).to(ProdutosFavoritosDAO.class).in(Singleton.class);
	}

}
