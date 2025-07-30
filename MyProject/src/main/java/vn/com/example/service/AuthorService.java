package vn.com.example.service;

import vn.com.example.entity.Author;
import java.util.List;
import java.util.Optional;

public interface AuthorService {

    // 1. CREATE
    Author createAuthor(Author author);

    // 2. READ by ID
    Optional<Author> findAuthorById(Long id);

    // 3. READ All
    List<Author> findAllAuthors();

    // 4. READ by Name (Custom)
    List<Author> findAuthorsByName(String name);

    // 5. UPDATE (General)
    Author updateAuthor(Long id, Author authorDetails);

    // 6. DELETE
    void deleteAuthor(Long id);
}