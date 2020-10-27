package com.br.luizalabs.api.service.impl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import com.br.luizalabs.api.app.Log;
import com.br.luizalabs.api.constants.Constants;
import com.br.luizalabs.api.exceptions.GenericException;
import com.br.luizalabs.api.to.ProductTO;

public class ProductClient {

	public static ProductTO getProductDetails(Integer productId) {
		ProductTO product = getProductFromCache(productId);
		if (product == null) {
			product = getProductFromAPI(productId);
			Log.info("Produto recuperado pela API: {}", product);
			insertProductOnCache(productId, product);
			return product;
		}
		Log.info("Produto recuperado pelo CACHE: {}", product);
		return product;
	}

	private static ProductTO getProductFromCache(Integer productId) {
		return CachedProduct.instance.get(productId);
	}

	private static ProductTO getProductFromAPI(Integer productId) {
		String productPath = Constants.PRODUCT_API_PATH + productId;
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(Constants.PRODUCT_API_URL).path(productPath);
		ProductTO product;
		try {
			product = target.request(MediaType.APPLICATION_JSON).get(ProductTO.class);
		} catch (javax.ws.rs.NotFoundException e) {
			throw new GenericException(Status.BAD_REQUEST,
					"Você esta tentando adicionar um produto aos seus favoritos que não existe.");
		} catch (javax.ws.rs.ProcessingException pe) {
			throw new GenericException(Status.GATEWAY_TIMEOUT,
					"Tivemos um problema ao buscar os detalhes do produto, tenta novamente mais tarde.");
		}
		return product;
	}

	private static void insertProductOnCache(Integer productId, ProductTO product) {
		CachedProduct.instance.put(productId, product);
	}

}
