package com.br.luizalabs.api.jdbi.interfaces;

import java.math.BigDecimal;
import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import com.br.luizalabs.api.to.ProductTO;

@Contract
public interface ProdutosFavoritosDAO {
	
	public List<ProductTO> getList(Integer clientId);
	
	public Integer deleteOneProduct(Integer clienteId, Integer productId);
	
	public Integer deleteAllProducts(Integer clienteId);
	
	public Integer createItem(Integer clienteId, Integer productId, String name, BigDecimal price, String image);
	
	public Integer createItem(Integer clienteId, ProductTO product);
	
}
