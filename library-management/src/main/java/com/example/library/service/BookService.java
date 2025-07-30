package com.example.library.service;

import com.example.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface BookService {
  Book createBook(Book book);
  Book updateBook(Book book);
  void deleteBook(Long id);
  Optional<Book> getBookById(Long id);
  Page<Book> getAllBooks(Pageable pageable);
}