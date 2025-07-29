package com.demo.Demo.service;

import java.util.List;
import java.util.Optional;

import com.demo.Demo.entity.Product;

public interface ProductService{
	
	Product save(Product product);
	List<Product> findAll();
	Optional<Product> findById(Long id);
	void deleteById(Long id);
	void deleteAll();
	void deleteInBulkBySupplierId(Long supplierId);
	List<Product> findByPriceAndSupplier(Long supplierId, Double minPrice, Double maxPrice);
}