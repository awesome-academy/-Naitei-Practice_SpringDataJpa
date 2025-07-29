package com.example.library.repository;

import com.example.library.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    // Additional query methods can be defined here if needed
  
}

