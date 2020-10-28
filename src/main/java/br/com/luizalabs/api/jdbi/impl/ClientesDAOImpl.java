package br.com.luizalabs.api.jdbi.impl;

import java.util.List;

import br.com.luizalabs.api.jdbi.BaseJDBI;
import br.com.luizalabs.api.jdbi.binder.IClienteBinder;
import br.com.luizalabs.api.jdbi.interfaces.ClientesDAO;
import br.com.luizalabs.api.to.ClienteRequest;

public class ClientesDAOImpl extends BaseJDBI<IClienteBinder> implements ClientesDAO {

	public ClientesDAOImpl() {
		super(IClienteBinder.class);
	}

	@Override
	public ClienteRequest getCliente(Integer clientId) {
		return handler(handle -> {
			return getBinder(handle).getCliente(clientId);
		});
	}

	@Override
	public Integer updateCliente(Integer clientId, String nome, String email) {
		return handler(handle -> {
			return getBinder(handle).update(clientId, nome, email);
		});
	}

	@Override
	public Integer createCliente(String nome, String email) {
		return handler(handle -> {
			return getBinder(handle).insertClient(nome, email);
		});
	}

	@Override
	public Integer softDelete(Integer clienteId) {
		return handler(handle -> {
			return getBinder(handle).softDelete(clienteId);
		});
	}

	@Override
	public List<ClienteRequest> getList() {
		return handler(handle -> {
			return getBinder(handle).getClientesColletcion();
		});
	}

}
