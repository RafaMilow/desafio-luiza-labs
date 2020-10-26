package com.br.luizalabs.api.resources;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.br.luizalabs.api.app.Log;
import com.br.luizalabs.api.commons.Authentication;
import com.br.luizalabs.api.service.interfaces.Cliente;
import com.br.luizalabs.api.to.ClienteRequest;
import com.google.common.base.Stopwatch;

@Path("/")
public class ClienteResource {

	@Inject
	private Cliente cliente;

	public ClienteResource() {
		Thread.currentThread().setName("desafio-luiza-labs-api");
	}

	@GET
	@Authentication
	@Path("/ping")
	public Response getPong(@Context HttpServletRequest req) {
		return Response.status(Response.Status.OK).entity("pong").build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/clientes")
	public Response getClientes() {
		List<ClienteRequest> listagem = cliente.getAll();
		return Response.status(Response.Status.OK).entity(listagem).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/clientes/{clienteId}")
	public Response getCliente(@PathParam("clienteId") Integer clienteId) {

		UUID uuid = UUID.randomUUID();
		Stopwatch timer = Stopwatch.createStarted();
		Log.info("{} | START --> Buscando cliente. Parametro recebido: {}", uuid, clienteId);

		ClienteRequest clienteTO = cliente.retrieve(clienteId);
		if (clienteTO == null) {
			Log.info("{} | END --> Cliente nao encontrado! | Tempo Gasto: {}ms", uuid,
					timer.elapsed(TimeUnit.MILLISECONDS));
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		Log.info("{} | END --> Buscando cliente. Response: {} | Tempo Gasto: {}ms", uuid, clienteTO,
				timer.elapsed(TimeUnit.MILLISECONDS));
		return Response.status(Response.Status.OK).entity(clienteTO).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/clientes/")
	public Response createCliente(ClienteRequest request) {
		Integer clientId = cliente.create(request.getEmail(), request.getNome());
		return Response.status(Response.Status.CREATED).entity("POST: " + clientId).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/clientes/{clienteId}")
	public Response updateCliente(@PathParam("clienteId") Integer clienteId, ClienteRequest request) {
		cliente.update(clienteId, request.getNome(), request.getEmail());
		return Response.status(Response.Status.OK).entity("PUT: " + clienteId).build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/clientes/{clienteId}")
	public Response deleteCliente(@PathParam("clienteId") Integer clienteId) {
		cliente.delete(clienteId);
		return Response.status(Response.Status.OK).entity("DELETE: " + clienteId).build();
	}

	@Path("/clientes/{clienteId}/produtos-favoritos")
	public ProdutosFavoritosResource getFavoritosResource() {
		return new ProdutosFavoritosResource();
	}
}
