package br.com.luizalabs.api.jdbi.impl;

import java.math.BigDecimal;
import java.util.List;

import br.com.luizalabs.api.jdbi.BaseJDBI;
import br.com.luizalabs.api.jdbi.binder.IProdutosFavoritosBinder;
import br.com.luizalabs.api.jdbi.interfaces.ProdutosFavoritosDAO;
import br.com.luizalabs.api.to.ProductTO;

public class ProdutosFavoritosDAOImpl extends BaseJDBI<IProdutosFavoritosBinder> implements ProdutosFavoritosDAO {

	public ProdutosFavoritosDAOImpl() {
		super(IProdutosFavoritosBinder.class);
	}

	@Override
	public List<ProductTO> getList(Integer clientId) {
		return handler(handle -> {
			return getBinder(handle).getListaDoCliente(clientId);
		});
	}

	@Override
	public Integer deleteOneProduct(Integer clienteId, Integer productId) {
		return handler(handle -> {
			return getBinder(handle).deleteOneProduct(clienteId, productId);
		});
	}
	
	@Override
	public Integer deleteAllProducts(Integer clienteId) {
		return handler(handle -> {
			return getBinder(handle).deleteAllProducts(clienteId);
		});
	}

	@Override
	public Integer createItem(Integer clienteId, Integer productId, String name, BigDecimal price, String image) {
		return handler(handle -> {
			return getBinder(handle).insertOnList(clienteId, productId, name, price, image);
		});
	}

	@Override
	public Integer createItem(Integer clienteId, ProductTO product) {
		return handler(handle -> {
			return getBinder(handle).insertBeanOnList(clienteId, product);
		});
	}
}
