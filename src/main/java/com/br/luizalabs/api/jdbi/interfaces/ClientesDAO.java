package com.br.luizalabs.api.jdbi.interfaces;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import com.br.luizalabs.api.to.ClienteRequest;

@Contract
public interface ClientesDAO {

	public List<ClienteRequest> getList();
	
	public ClienteRequest getCliente(Integer clientId);
	
	public Integer updateCliente(Integer clientId, String nome, String email);
	
	public Integer createCliente(String nome, String email);
	
	public Integer softDelete(Integer clienteId);
	
}
