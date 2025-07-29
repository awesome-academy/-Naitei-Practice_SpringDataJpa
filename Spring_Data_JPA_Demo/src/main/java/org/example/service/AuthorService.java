
package org.example.service;

import java.util.Optional;
import org.example.entity.Author;

public interface AuthorService {
    Author saveAuthor(Author author);

    Author getAuthorById(Long id);

    Iterable<Author> getAllAuthors();

    Optional<Author> getByEmail(String email);

    void deleteAuthor(Long id);
}
