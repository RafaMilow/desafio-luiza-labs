package com.br.luizalabs.api.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import com.br.luizalabs.api.jdbi.interfaces.ClientesDAO;
import com.br.luizalabs.api.jdbi.interfaces.ProdutosFavoritosDAO;
import com.br.luizalabs.api.service.interfaces.Cliente;
import com.br.luizalabs.api.to.ClienteRequest;

@Service
public class ClienteImpl implements Cliente {

	@Inject
	private ClientesDAO clientesDAO;

	@Inject
	private ProdutosFavoritosDAO produtosFavoritosDAO;
	
	@Override
	public ClienteRequest retrieve(Integer clienteId) {
		ClienteRequest cliente = clientesDAO.getCliente(clienteId);
		return cliente;
	}

	@Override
	public int delete(Integer clienteId) {
		Integer value = clientesDAO.softDelete(clienteId);
		if(value > 0) {
			produtosFavoritosDAO.deleteAllProducts(clienteId);
		}
		return value;
	}

	@Override
	public int create(String nome, String email) {
		Integer clienteId = clientesDAO.createCliente(nome, email);
		return clienteId;
	}

	@Override
	public int update(Integer clienteId, String nome, String email) {
		Integer value = clientesDAO.updateCliente(clienteId, nome, email);
		return value;
	}

	@Override
	public List<ClienteRequest> getAll() {
		return clientesDAO.getList();
	}

}
