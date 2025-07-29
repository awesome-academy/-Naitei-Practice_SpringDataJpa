package com.example.Product.service;

import com.example.Product.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product createProduct(Product product);

    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    Product updateProduct(Long id, Product productDetails);

    void deleteProduct(Long id);

    List<Product> getProductsByCategory(String category);

    List<Product> searchProductsByName(String name);

    List<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

    List<Product> getLowStockProducts(Integer threshold);
}
