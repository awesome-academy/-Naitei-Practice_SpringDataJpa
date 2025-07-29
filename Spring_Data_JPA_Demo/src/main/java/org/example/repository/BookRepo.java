
package org.example.repository;

import java.util.Optional;
import org.example.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepo extends CrudRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
}
