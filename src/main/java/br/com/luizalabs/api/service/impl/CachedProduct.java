package br.com.luizalabs.api.service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import br.com.luizalabs.api.to.ProductTO;

public class CachedProduct {

	public static final CachedProduct instance = new CachedProduct();

	protected CachedProduct() {
	}

	private Cache<Integer, ProductTO> cache = CacheBuilder.newBuilder().maximumSize(200).build();

	public void put(Integer productId, ProductTO product) {
		cache.put(productId, product);
	}

	public ProductTO get(Integer productId) {
		return cache.getIfPresent(productId);
	}
}
