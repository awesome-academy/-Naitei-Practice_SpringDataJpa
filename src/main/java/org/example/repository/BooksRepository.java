package org.example.repository;

import org.example.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {
    List<Books> findAllByPublisher(String publisher);

    List<Books> findAllByTitle(String title);
}
