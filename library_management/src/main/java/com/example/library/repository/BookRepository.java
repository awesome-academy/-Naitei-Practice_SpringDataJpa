package com.example.library.repository;

import com.example.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByPublishedDay(LocalDate date);
    //Optional<Book> getBookById(Long id);
    List<Book> findByTitleContaining(String keyword);
    Optional<Book> findFirstByTitle(String title);
}
