package org.example.repository;



import org.example.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {


    Optional<Author> findByFirstnameContainingIgnoreCase(String name);

    Optional<Author> findAuthorByFirstname(String firstname);

    @Modifying
    @Query("delete from Author a where a.id =:id")
    Optional<Author> deleteInBulkById(Long id);
}
