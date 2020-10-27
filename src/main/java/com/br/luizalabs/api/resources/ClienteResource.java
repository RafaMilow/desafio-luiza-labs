package com.br.luizalabs.api.resources;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.br.luizalabs.api.app.Log;
import com.br.luizalabs.api.commons.Authentication;
import com.br.luizalabs.api.constants.Constants;
import com.br.luizalabs.api.exceptions.GenericException;
import com.br.luizalabs.api.service.interfaces.Cliente;
import com.br.luizalabs.api.to.ClienteRequest;
import com.google.common.base.Stopwatch;

@Path("/")
public class ClienteResource {

	@Inject
	private Cliente cliente;

	@GET
	@Path("/ping")
	public Response getPong(@Context HttpServletRequest req) {
		return Response.status(Response.Status.OK).entity("pong").build();
	}

	@GET
	@Authentication
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/clientes")
	public Response getClientes() {
		UUID uuid = UUID.randomUUID();
		Stopwatch timer = Stopwatch.createStarted();
		Log.info("{} | START --> Buscando lista de clientes.", uuid);

		List<ClienteRequest> listagem = cliente.getAll();

		Log.info("{} | END --> Buscando lista de clientes. | Tempo Gasto: {}ms", uuid,
				timer.elapsed(TimeUnit.MILLISECONDS));

		if (listagem.size() > Constants.EMPTY_LIST_SIZE) {
			return Response.status(Response.Status.OK).entity(listagem).build();
		}
		return Response.status(Response.Status.OK).entity("Nao há clientes cadastrados").build();
	}

	@GET
	@Authentication
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/clientes/{clienteId: [0-9]*}")
	public Response getCliente(@PathParam("clienteId") Integer clienteId) {

		UUID uuid = UUID.randomUUID();
		Stopwatch timer = Stopwatch.createStarted();
		Log.info("{} | START --> Buscando cliente. Parametro recebido: {}", uuid, clienteId);

		ClienteRequest clienteTO = cliente.retrieve(clienteId);
		if (clienteTO == null) {
			Log.info("{} | END --> Cliente nao encontrado! | Tempo Gasto: {}ms", uuid,
					timer.elapsed(TimeUnit.MILLISECONDS));
			throw new GenericException(Response.Status.NOT_FOUND, "Cliente não encontrado!");
		}
		Log.info("{} | END --> Buscando cliente. Response: {} | Tempo Gasto: {}ms", uuid, clienteTO,
				timer.elapsed(TimeUnit.MILLISECONDS));
		return Response.status(Response.Status.OK).entity(clienteTO).build();
	}

	@POST
	@Authentication
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/clientes/")
	public Response createCliente(@Valid ClienteRequest request, @Context UriInfo uriInfo) {
		UUID uuid = UUID.randomUUID();
		Stopwatch timer = Stopwatch.createStarted();

		Log.info("{} | START --> Criando um cliente. Request recebido: {}", uuid, request);
		Integer clientId = cliente.create(request.getNome(), request.getEmail());
		UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
		uriBuilder.path(Integer.toString(clientId));
		Log.info("{} | END --> Buscando cliente. ClientId Criado: {} | Tempo Gasto: {}ms", uuid, clientId,
				timer.elapsed(TimeUnit.MILLISECONDS));
		return Response.created(uriBuilder.build()).build();
	}

	@PUT
	@Authentication
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/clientes/{clienteId: [0-9]*}")
	public Response updateCliente(@PathParam("clienteId") Integer clienteId, @Valid ClienteRequest request) {
		UUID uuid = UUID.randomUUID();
		Stopwatch timer = Stopwatch.createStarted();

		Log.info("{} | START --> Atualizando um cliente. Request recebido: {}", uuid, request);
		Boolean wasUpdated = cliente.update(clienteId, request.getNome(), request.getEmail());
		request.setId(clienteId);
		if (!wasUpdated) {
			Log.info("{} | END --> Cliente nao encontrado! | Tempo Gasto: {}ms", uuid,
					timer.elapsed(TimeUnit.MILLISECONDS));
			throw new GenericException(Response.Status.NOT_FOUND, "Cliente não encontrado!");
		}
		Log.info("{} | END --> Apagando cliente. | Tempo Gasto: {}ms", uuid, timer.elapsed(TimeUnit.MILLISECONDS));
		return Response.status(Response.Status.OK).entity(request).build();
	}

	@DELETE
	@Authentication
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/clientes/{clienteId: [0-9]*}")
	public Response deleteCliente(@PathParam("clienteId") Integer clienteId) {
		UUID uuid = UUID.randomUUID();
		Stopwatch timer = Stopwatch.createStarted();
		Log.info("{} | START --> Apagando um cliente.", uuid);

		Boolean wasDeleted = cliente.delete(clienteId);
		if (!wasDeleted) {
			Log.info("{} | END --> Cliente nao encontrado! | Tempo Gasto: {}ms", uuid,
					timer.elapsed(TimeUnit.MILLISECONDS));
			throw new GenericException(Response.Status.NOT_FOUND, "Cliente não encontrado!");
		}
		Log.info("{} | END --> Apagando cliente. | Tempo Gasto: {}ms", uuid, timer.elapsed(TimeUnit.MILLISECONDS));
		return Response.status(Response.Status.NO_CONTENT).build();
	}

}
