
package org.example.service.impl;

import java.util.Optional;
import org.example.entity.Author;
import org.example.repository.AuthorRePo;
import org.example.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRePo authorRePo;

    public AuthorServiceImpl() {
    }

    @Transactional
    public Author saveAuthor(Author author) {
        return (Author)this.authorRePo.save(author);
    }

    public Author getAuthorById(Long id) {
        return (Author)this.authorRePo.findById(id).orElse(null);
    }

    @Transactional(
            readOnly = true
    )
    public Iterable<Author> getAllAuthors() {
        return this.authorRePo.findAll();
    }

    public Optional<Author> getByEmail(String email) {
        return this.authorRePo.findByEmail(email);
    }

    @Transactional
    public void deleteAuthor(Long id) {
        this.authorRePo.deleteById(id);
    }
}
