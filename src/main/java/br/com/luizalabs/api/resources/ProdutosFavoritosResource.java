package br.com.luizalabs.api.resources;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.google.common.base.Stopwatch;

import br.com.luizalabs.api.app.Log;
import br.com.luizalabs.api.commons.Authentication;
import br.com.luizalabs.api.constants.Constants;
import br.com.luizalabs.api.exceptions.GenericException;
import br.com.luizalabs.api.service.interfaces.ProdutosFavoritos;
import br.com.luizalabs.api.to.ProductTO;
import br.com.luizalabs.api.to.ProdutosFavoritosTO;

@Path("/clientes/{clienteId: [0-9]*}/produtos-favoritos")
public class ProdutosFavoritosResource {

	@Inject
	private ProdutosFavoritos produtosFavoritos;

	@GET
	@Authentication
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListaFavoritosCliente(@PathParam("clienteId") Integer clienteId) {
		UUID uuid = UUID.randomUUID();
		Stopwatch timer = Stopwatch.createStarted();

		Log.info("{} | START --> Buscando lista de favoritos do cliente.", uuid);
		List<ProductTO> listagem = produtosFavoritos.getCollection(clienteId);
		Log.info("{} | END --> Fim da busca. | Tempo Gasto: {}ms", uuid, timer.elapsed(TimeUnit.MILLISECONDS));

		if (listagem.size() <= Constants.EMPTY_LIST_SIZE) {
			throw new GenericException(Response.Status.NOT_FOUND, "Listagem de favoritos não encontrada!");
		}
		return Response.status(Response.Status.OK).entity(listagem).build();
	}

	@POST
	@Authentication
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createListaFavoritosCliente(@PathParam("clienteId") Integer clienteId,
			@Valid ProdutosFavoritosTO request, @Context UriInfo uriInfo) {
		UUID uuid = UUID.randomUUID();
		Stopwatch timer = Stopwatch.createStarted();

		Log.info("{} | START --> Adicionando productId: {} nos favoritos", uuid, request.getProductId());
		produtosFavoritos.createItemOnList(clienteId, request.getProductId());
		UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
		Log.info("{} | END --> Produto adicionando. | Tempo Gasto: {}ms", uuid, timer.elapsed(TimeUnit.MILLISECONDS));

		return Response.created(uriBuilder.build()).build();
	}

	@DELETE
	@Authentication
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteListaFavoritosCliente(@PathParam("clienteId") Integer clienteId) {
		UUID uuid = UUID.randomUUID();
		Stopwatch timer = Stopwatch.createStarted();

		Log.info("{} | START --> Apagando a lista de favoritos do cliente.", uuid);
		Integer result = produtosFavoritos.deleteAllList(clienteId);
		Log.info("{} | END --> Apagando a lista de favoritos do cliente. Result do delete: {} | Tempo Gasto: {}ms",
				uuid, result, timer.elapsed(TimeUnit.MILLISECONDS));
		return Response.status(Response.Status.NO_CONTENT).build();
	}

	@DELETE
	@Authentication
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{productId: [0-9]*}")
	public Response deleteOneItem(@PathParam("clienteId") Integer clienteId,
			@PathParam("productId") Integer productId) {
		UUID uuid = UUID.randomUUID();
		Stopwatch timer = Stopwatch.createStarted();

		Log.info("{} | START --> Apagando o productId: {} da lista de favoritos do clienteId: {}", uuid, productId,
				clienteId);
		boolean wasDeleted = produtosFavoritos.deleteItemOnList(clienteId, productId);
		if (!wasDeleted) {
			Log.info("{} | END --> Produto nao encontrado! | Tempo Gasto: {}ms", uuid,
					timer.elapsed(TimeUnit.MILLISECONDS));
			throw new GenericException(Response.Status.NOT_FOUND,
					"Produto não encontrado na lista de favoritos do produto!");
		}
		Log.info("{} | END --> Apagado. Result: {}| Tempo Gasto: {}ms", uuid, timer.elapsed(TimeUnit.MILLISECONDS));
		return Response.status(Response.Status.NO_CONTENT).build();
	}

}
