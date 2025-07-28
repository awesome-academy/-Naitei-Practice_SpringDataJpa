package com.example.springdatajpa.service;
import com.example.springdatajpa.model.Product;
import java.util.List;
import java.util.Set;

public interface ProductService {

    Product createProduct(String name, double price, String description, Long categoryId, Set<String> tagNames);

    List<Product> getAllProducts();

    Product updateProduct(Long productId, String newName, double newPrice);

    void deleteProduct(Long id);

    List<Product> findProductsByName(String name);

}