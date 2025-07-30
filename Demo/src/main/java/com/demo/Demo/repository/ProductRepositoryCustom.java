package com.demo.Demo.repository;

import java.util.List;

import com.demo.Demo.entity.Product;

public interface ProductRepositoryCustom{
	// san pham cua nha san xuat bat ky co gia tu minPrice -> minPrice
	List<Product> findByPriceAndSupplier(Long supplierId, Double minPrice, Double maxPrice);
}