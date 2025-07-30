package com.naiteipractice.springdatajpa.repository;

import com.naiteipractice.springdatajpa.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    // Additional custom methods beyond CRUD
    List<Book> findByAuthorContainingIgnoreCase(String author);
    
    List<Book> findByTitleContainingIgnoreCase(String title);
    
    @Query("SELECT b FROM Book b WHERE b.publicationYear >= :year")
    List<Book> findBooksPublishedAfterYear(@Param("year") Integer year);
    
    List<Book> findByPriceBetween(Double minPrice, Double maxPrice);
}
