package br.com.luizalabs.api.service.interfaces;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import br.com.luizalabs.api.to.ProductTO;

@Contract
public interface ProdutosFavoritos {

	public List<ProductTO> getCollection(Integer clienteId);

	public Integer createItemOnList(Integer clienteId, Integer productId);
	
	public boolean deleteItemOnList(Integer clienteId, Integer productId);
	
	public Integer deleteAllList(Integer clienteId);
	
}
