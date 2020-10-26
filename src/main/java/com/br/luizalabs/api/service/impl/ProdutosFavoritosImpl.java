package com.br.luizalabs.api.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.br.luizalabs.api.exceptions.GenericException;
import com.br.luizalabs.api.jdbi.interfaces.ProdutosFavoritosDAO;
import com.br.luizalabs.api.service.interfaces.ProdutosFavoritos;
import com.br.luizalabs.api.to.ProductTO;

public class ProdutosFavoritosImpl implements ProdutosFavoritos {

	@Inject
	private ProdutosFavoritosDAO produtosFavoritosDAO;

	static Logger LOG = LoggerFactory.getLogger(ProdutosFavoritosImpl.class);

	@Override
	public List<ProductTO> getCollection(Integer clienteId) {
		return produtosFavoritosDAO.getList(clienteId);
	}

	@Override
	public Integer createItemOnList(Integer clienteId, Integer productId) {
		// GET PRODUCT DETAILS
		ProductTO product = ProductClient.getProductDetails(productId);
		LOG.info("Detalhes do produto: {}", product);
		Integer retorno = 0;
		try {
//			retorno = produtosFavoritosDAO.createItem(clienteId, product.getId(), product.getName(), product.getPrice(),
//					product.getImage());
			retorno = produtosFavoritosDAO.createItem(clienteId, product);
		} catch (Exception e) {
			// LOG.error("Erro de insert", e);
			LOG.error("Erro de insert");
			throw new GenericException("Erro de Insert");
		}

		// ProdutosFavoritosDAOImpl produtosFavoritos = new ProdutosFavoritosDAOImpl();
		return retorno;
	}

	@Override
	public Integer deleteItemOnList(Integer clienteId, Integer productId) {
		Integer value = produtosFavoritosDAO.deleteOneProduct(clienteId, productId);
		LOG.info("Valor do retorno do DELETE SINGLE PRODUCT: {}", value);
		return value;
	}

	@Override
	public Integer deleteAllList(Integer clienteId) {
		Integer value = produtosFavoritosDAO.deleteAllProducts(clienteId);
		LOG.info("Valor do retorno do DELETE ALL PRODUCTS: {}", value);
		return value;
	}

}
