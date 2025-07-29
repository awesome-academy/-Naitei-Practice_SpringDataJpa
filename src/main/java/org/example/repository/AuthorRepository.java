package org.example.repository;



import org.example.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {


    List<Author> findByFirstnameContainingIgnoreCase(String name);



    List<Author> findByFirstname(String firstname);

    @Modifying
    @Query("delete from Author a where a.id =:id")
    void deleteInBulkById(@Param("id") Long id);


    @Query(value="SELECT a.firstname, a.lastname, b.title FROM authors a " +
            "JOIN book_author ba ON a.id = ba.author_id " +
            "JOIN books b ON b.id = ba.book_id " +
            "WHERE b.title = :title", nativeQuery = true)
    List<Object[]> findAuthorByBook(@Param("title") String title);

    @Query(value = "SELECT a.*, b.title FROM authors a " +
            "JOIN book_author ba ON a.id = ba.author_id " +
            "JOIN books b ON ba.book_id = b.id", nativeQuery = true)
    List<Object[]> findAllAuthorsWithBooks();
}
