package com.practice.jpa.service;

import com.practice.jpa.entity.ProductEntity;
import com.practice.jpa.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;

    public ProductEntity create(ProductEntity p) { return repo.save(p); }
    public List<ProductEntity> getAll() { return repo.findAll(); }
    public ProductEntity update(ProductEntity p) { return repo.save(p); }
    public void delete(int id) { repo.deleteById(id); }
}