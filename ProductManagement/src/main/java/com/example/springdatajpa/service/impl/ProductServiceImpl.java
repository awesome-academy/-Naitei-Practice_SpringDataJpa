package com.example.springdatajpa.service.impl;

import com.example.springdatajpa.model.Category;
import com.example.springdatajpa.model.Product;
import com.example.springdatajpa.model.ProductDetails;
import com.example.springdatajpa.model.Tag;
import com.example.springdatajpa.repository.CategoryRepository;
import com.example.springdatajpa.repository.ProductRepository;
import com.example.springdatajpa.repository.TagRepository;
import com.example.springdatajpa.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;

    // Constructor Injection
    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              TagRepository tagRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    @Transactional
    public Product createProduct(String name, double price, String description, Long categoryId, Set<String> tagNames) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));

        Set<Tag> tags = tagNames.stream().map(tagName -> {
            return tagRepository.findByName(tagName)
                    .orElseGet(() -> {
                        Tag newTag = new Tag();
                        newTag.setName(tagName);
                        return tagRepository.save(newTag);
                    });
        }).collect(Collectors.toSet());

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);

        ProductDetails details = new ProductDetails();
        details.setDescription(description);
        product.setDetails(details);

        product.setTags(tags);

        return productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Product updateProduct(Long productId, String newName, double newPrice) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        product.setName(newName);
        product.setPrice(newPrice);

        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
}