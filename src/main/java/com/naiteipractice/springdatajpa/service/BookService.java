package com.naiteipractice.springdatajpa.service;

import com.naiteipractice.springdatajpa.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    // CRUD operations
    Book saveBook(Book book);
    
    Optional<Book> getBookById(Long id);
    
    List<Book> getAllBooks();
    
    Book updateBook(Book book);
    
    void deleteBook(Long id);
    
    // Additional methods
    List<Book> findBooksByAuthor(String author);
    
    List<Book> findBooksByTitle(String title);
    
    List<Book> findBooksByPublicationYearAfter(Integer year);
    
    List<Book> findBooksByPriceRange(Double minPrice, Double maxPrice);
}
