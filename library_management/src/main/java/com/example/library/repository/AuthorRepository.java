package com.example.library.repository;

import com.example.library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    Optional<Author> findByName(String name);
    Optional<Author> findById(Long id);
}
