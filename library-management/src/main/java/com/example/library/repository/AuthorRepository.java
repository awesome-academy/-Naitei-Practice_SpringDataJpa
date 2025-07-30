package com.example.library.repository;

import com.example.library.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    // Additional query methods can be defined here if needed

}
