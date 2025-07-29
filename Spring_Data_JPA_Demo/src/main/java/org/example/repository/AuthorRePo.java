
package org.example.repository;

import java.util.Optional;
import org.example.entity.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRePo extends CrudRepository<Author, Long> {
    Optional<Author> findByEmail(String email);
}
