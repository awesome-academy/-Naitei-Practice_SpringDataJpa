package org.example.service;

import org.example.entity.Author;

import java.util.List;
import java.util.Objects;


public interface AuthorService {
    List<Author> getAllAuthor();
    List<Author> getAuthorByKeyword(String keyword);
    List<Author> findByFirstname(String firstname);

    Author saveAuthor(Author author);
    void deleteAuthorById(Long id);
    List<Object[]> findAuthorByBook(String title);
    List<Object[]> findAllAuthorsWithBooks();
}
