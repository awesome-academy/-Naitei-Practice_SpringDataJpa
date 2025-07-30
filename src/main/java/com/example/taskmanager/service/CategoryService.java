package com.example.taskmanager.service;

import com.example.taskmanager.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    void createCategory(Category category);
    List<Category> getAllCategories();
    Optional<Category> getCategoryById(Long id);
    void updateCategory(Category category);
    void deleteCategory(Long id);
}
