package com.example.library.library_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.library.library_management.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {}
