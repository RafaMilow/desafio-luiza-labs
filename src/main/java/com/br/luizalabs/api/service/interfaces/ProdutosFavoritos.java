package com.br.luizalabs.api.service.interfaces;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import com.br.luizalabs.api.to.ProductTO;

@Contract
public interface ProdutosFavoritos {

	public List<ProductTO> getCollection(Integer clienteId);

	public Integer createItemOnList(Integer clienteId, Integer productId);
	
	public Integer deleteItemOnList(Integer clienteId, Integer productId);
	
	public Integer deleteAllList(Integer clienteId);
	
}
