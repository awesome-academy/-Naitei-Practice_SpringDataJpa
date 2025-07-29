package com.example.Product.service.impl;

import com.example.Product.entity.Product;
import com.example.Product.repository.ProductRepository;
import com.example.Product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product price must be greater than zero");
        }
        if (product.getStockQuantity() == null || product.getStockQuantity() < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }

        return productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> getProductById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Product ID must be a positive number");
        }
        return productRepository.findById(id);
    }

    @Override
    public Product updateProduct(Long id, Product productDetails) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Product ID must be a positive number");
        }

        return productRepository.findById(id)
                .map(existingProduct -> {
                    if (productDetails.getName() != null && !productDetails.getName().trim().isEmpty()) {
                        existingProduct.setName(productDetails.getName());
                    }
                    if (productDetails.getDescription() != null) {
                        existingProduct.setDescription(productDetails.getDescription());
                    }
                    if (productDetails.getPrice() != null && productDetails.getPrice().compareTo(BigDecimal.ZERO) > 0) {
                        existingProduct.setPrice(productDetails.getPrice());
                    }
                    if (productDetails.getCategory() != null) {
                        existingProduct.setCategory(productDetails.getCategory());
                    }
                    if (productDetails.getStockQuantity() != null && productDetails.getStockQuantity() >= 0) {
                        existingProduct.setStockQuantity(productDetails.getStockQuantity());
                    }

                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Override
    public void deleteProduct(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Product ID must be a positive number");
        }

        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductsByCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }
        return productRepository.findByCategory(category.trim());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> searchProductsByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Search name cannot be null or empty");
        }
        return productRepository.findByNameContainingIgnoreCase(name.trim());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        if (minPrice == null || maxPrice == null) {
            throw new IllegalArgumentException("Price range values cannot be null");
        }
        if (minPrice.compareTo(maxPrice) > 0) {
            throw new IllegalArgumentException("Min price cannot be greater than max price");
        }
        if (minPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price values cannot be negative");
        }

        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getLowStockProducts(Integer threshold) {
        if (threshold == null || threshold < 0) {
            throw new IllegalArgumentException("Stock threshold must be a non-negative number");
        }
        return productRepository.findLowStockProducts(threshold);
    }
}
