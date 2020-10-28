package br.com.luizalabs.api.service.interfaces;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import br.com.luizalabs.api.to.ClienteRequest;

@Contract
public interface Cliente {

	public int create(String nome, String email);
	
	public List<ClienteRequest> getAll();
	
	public ClienteRequest retrieve(Integer clienteId);
	
	public boolean update(Integer clienteId, String nome, String email);
	
	public boolean delete(Integer clienteId);
	
}
