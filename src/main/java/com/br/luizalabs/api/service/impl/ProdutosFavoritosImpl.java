package com.br.luizalabs.api.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.br.luizalabs.api.app.Log;
import com.br.luizalabs.api.exceptions.GenericException;
import com.br.luizalabs.api.jdbi.interfaces.ClientesDAO;
import com.br.luizalabs.api.jdbi.interfaces.ProdutosFavoritosDAO;
import com.br.luizalabs.api.service.interfaces.ProdutosFavoritos;
import com.br.luizalabs.api.to.ClienteRequest;
import com.br.luizalabs.api.to.ProductTO;

public class ProdutosFavoritosImpl implements ProdutosFavoritos {

	@Inject
	private ClientesDAO clientesDAO;
	
	@Inject
	private ProdutosFavoritosDAO produtosFavoritosDAO;

	@Override
	public List<ProductTO> getCollection(Integer clienteId) {
		checkClientExists(clienteId);
		return produtosFavoritosDAO.getList(clienteId);
	}

	@Override
	public Integer createItemOnList(Integer clienteId, Integer productId) {
		checkClientExists(clienteId);
		ProductTO product = ProductClient.getProductDetails(productId);
		Log.info("Detalhes do produto: {}", product);
		Integer retorno = 0;
		try {
			retorno = produtosFavoritosDAO.createItem(clienteId, product);
		} catch (Exception e) {
			throw new GenericException("Erro de Insert");
		}

		// ProdutosFavoritosDAOImpl produtosFavoritos = new ProdutosFavoritosDAOImpl();
		return retorno;
	}

	@Override
	public Integer deleteItemOnList(Integer clienteId, Integer productId) {
		checkClientExists(clienteId);
		Integer value = produtosFavoritosDAO.deleteOneProduct(clienteId, productId);
		Log.info("Valor do retorno do DELETE SINGLE PRODUCT: {}", value);
		return value;
	}

	@Override
	public Integer deleteAllList(Integer clienteId) {
		checkClientExists(clienteId);
		Integer value = produtosFavoritosDAO.deleteAllProducts(clienteId);
		Log.info("Valor do retorno do DELETE ALL PRODUCTS: {}", value);
		return value;
	}
	
	private void checkClientExists(Integer clienteId) {
		ClienteRequest cliente = clientesDAO.getCliente(clienteId);
		if(cliente == null) {
			throw new GenericException(Response.Status.NOT_FOUND, "Cliente não encontrado!");
		}
	}

}
