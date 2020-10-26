package com.br.luizalabs.api.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.br.luizalabs.api.service.interfaces.ProdutosFavoritos;
import com.br.luizalabs.api.to.ProductTO;
import com.br.luizalabs.api.to.ProdutosFavoritosTO;

public class ProdutosFavoritosResource {

	@Inject
	private ProdutosFavoritos produtosFavoritos;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListaFavoritosCliente(@PathParam("clienteId") Integer clienteId) {
		List<ProductTO> listagem = produtosFavoritos.getCollection(clienteId);
		return Response.status(Response.Status.OK).entity(listagem).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createListaFavoritosCliente(@PathParam("clienteId") Integer clienteId,
			ProdutosFavoritosTO request) {
		Integer value = produtosFavoritos.createItemOnList(clienteId, request.getProductId());
		return Response.status(Response.Status.CREATED).entity("POST: " + clienteId + ", " + request.getProductId())
				.build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteListaFavoritosCliente(@PathParam("clienteId") Integer clienteId) {
		Integer value = produtosFavoritos.deleteAllList(clienteId);
		return Response.status(Response.Status.OK).entity("DELETE ALL: " + clienteId).build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{productId}")
	public Response deleteOneItem(@PathParam("clienteId") Integer clienteId,
			@PathParam("productId") Integer productId) {
		Integer value = produtosFavoritos.deleteItemOnList(clienteId, productId);
		return Response.status(Response.Status.OK).entity("DELETE ONE: " + clienteId + ", " + productId).build();
	}

}
