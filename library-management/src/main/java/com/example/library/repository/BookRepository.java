package com.example.library.repository;

import java.util.List;
import com.example.library.model.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    // Derived query 
    List<Book> findByTitleContaining(String title);
    List<Book> findByCategoryName(String categoryName);

    // Custom query using JPQL
    @Query("SELECT b FROM Book b JOIN b.authors a WHERE a.name = :authorName")
    List<Book> findByAuthorName(@Param("authorName") String authorName);
}
