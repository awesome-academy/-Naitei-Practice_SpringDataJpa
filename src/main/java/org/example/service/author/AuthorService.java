package org.example.service.author;

import org.example.entity.Author;
import org.example.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface AuthorService {
    List<Author> getAllAuthor();
    Optional<Author> getAuthorByKeyword(String keyword);
    Optional<Author> getAuthorByFirstname(String firstname);

    Author saveAuthor(Author author);
    Optional<Author> deleteAuthorById(Long id);
}
