package com.practice.jpa.service;

import com.practice.jpa.entity.CategoryEntity;
import com.practice.jpa.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repo;

    public CategoryEntity create(CategoryEntity c) { return repo.save(c); }
    public List<CategoryEntity> getAll() { return repo.findAll(); }
    public CategoryEntity update(CategoryEntity c) { return repo.save(c); }
    public void delete(int id) { repo.deleteById(id); }
}
