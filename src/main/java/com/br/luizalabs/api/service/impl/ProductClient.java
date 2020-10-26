package com.br.luizalabs.api.service.impl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.br.luizalabs.api.to.ProductTO;

public class ProductClient {

	private static String URL = "https://5f3589525b91f60016ca4ee6.mockapi.io";
	private static String PATH = "/api/product/";

	static Logger LOG = LoggerFactory.getLogger(ProductClient.class);
	
	public static ProductTO getProductDetails(Integer productId) {
		ProductTO product = getProductFromCache(productId);
		if(product == null) {
			product = getProductFromAPI(productId);
			LOG.info("Produto recuperado pela API: {}", product);
			insertProductOnCache(productId, product);
			return product;
		}
		LOG.info("Produto recuperado pelo CACHE: {}", product);
		return product;
	}
	
	private static ProductTO getProductFromCache(Integer productId) {
		return CachedProduct.instance.get(productId);
	}

	private static ProductTO getProductFromAPI(Integer productId) {
		String productPath = PATH + productId;
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL).path(productPath);
		ProductTO product = target.request(MediaType.APPLICATION_JSON).get(ProductTO.class);
		return product;		
	}
	
	private static void insertProductOnCache(Integer productId, ProductTO product) {
		CachedProduct.instance.put(productId, product);
	}
	
}
