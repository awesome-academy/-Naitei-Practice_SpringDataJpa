
package org.example.service;

import java.util.Optional;
import org.example.entity.Book;

public interface BookService {
    Book saveBook(Book book);

    Book getBookById(Long id);

    Iterable<Book> getAllBooks();

    Optional<Book> findByIsbn(String isbn);

    void deleteBook(Long id);

    void printAllBookTitles();
}
