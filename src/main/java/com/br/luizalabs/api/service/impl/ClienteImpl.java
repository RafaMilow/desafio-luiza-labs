package com.br.luizalabs.api.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.jvnet.hk2.annotations.Service;

import com.br.luizalabs.api.exceptions.GenericException;
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
		if (value > 0) {
			produtosFavoritosDAO.deleteAllProducts(clienteId);
		}
		return value;
	}

	@Override
	public int create(String nome, String email) {
		Integer clienteId = 0;
		try {
			return clientesDAO.createCliente(nome, email);
		} catch (Exception e) {
			handleException(e);
		}
		return clienteId;
	}

	@Override
	public int update(Integer clienteId, String nome, String email) {
		Integer affected = 0;
		try {
			affected = clientesDAO.updateCliente(clienteId, nome, email);
		} catch (Exception e) {
			handleException(e);
		}
		return affected;
	}

	@Override
	public List<ClienteRequest> getAll() {
		return clientesDAO.getList();
	}

	private void handleException(Exception e) {
		if (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException.class
				.equals(e.getCause().getCause().getClass())) {
			throw new GenericException(Response.Status.BAD_REQUEST, "E-mail já cadastrado");
		}
		throw new GenericException(Response.Status.BAD_REQUEST, Response.Status.BAD_REQUEST.toString());
	}

}
