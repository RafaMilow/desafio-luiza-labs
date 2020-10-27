package com.br.luizalabs.api.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.br.luizalabs.api.app.Log;
import com.br.luizalabs.api.constants.Constants;
import com.br.luizalabs.api.exceptions.GenericException;
import com.br.luizalabs.api.jdbi.interfaces.ClientesDAO;
import com.br.luizalabs.api.jdbi.interfaces.ProdutosFavoritosDAO;
import com.br.luizalabs.api.service.interfaces.ProdutosFavoritos;
import com.br.luizalabs.api.to.ClienteRequest;
import com.br.luizalabs.api.to.ProductTO;
import com.sun.xml.bind.v2.runtime.reflect.opt.Const;

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
		Integer retorno = Constants.EMPTY_PRODUCT_ID;
		try {
			retorno = produtosFavoritosDAO.createItem(clienteId, product);
		} catch (Exception e) {
			Log.error("Erro de cadastro de produto!", e);
			handleException(e);
		}
		return retorno;
	}

	@Override
	public boolean deleteItemOnList(Integer clienteId, Integer productId) {
		checkClientExists(clienteId);
		Integer affectedRows = produtosFavoritosDAO.deleteOneProduct(clienteId, productId);
		if(affectedRows > Constants.ZERO_AFFECTED_ROWS) {
			return true;
		}
		Log.info("Valor do retorno do DELETE SINGLE PRODUCT: {}", affectedRows);
		return false;
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

	private void handleException(Exception e) {
		if (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException.class
				.equals(e.getCause().getCause().getClass())) {
			throw new GenericException(Response.Status.BAD_REQUEST, "Produto já cadastrado na sua lista");
		}
		throw new GenericException(Response.Status.BAD_REQUEST, Response.Status.BAD_REQUEST.toString());
	}

}
