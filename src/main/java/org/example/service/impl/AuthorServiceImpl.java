package org.example.service.impl;

import jakarta.transaction.Transactional;
import org.example.entity.Author;
import org.example.repository.AuthorRepository;
import org.example.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthor(){
        return authorRepository.findAll();
    }

    @Override
    public List<Author> getAuthorByKeyword(String keyword)
    {
        return authorRepository.findByFirstnameContainingIgnoreCase(keyword);
    }

    @Override
    public List<Author> findByFirstname(String firstname)
    {
        return authorRepository.findByFirstname(firstname);
    }

    @Override
    @Transactional
    public Author saveAuthor(Author author)
    {
        return authorRepository.save(author);
    }

    @Override
    @Transactional
    public void deleteAuthorById(Long id)
    {
         authorRepository.deleteInBulkById(id);
    }

    @Override
    public List<Object[]>findAuthorByBook(String title)
    {
        return authorRepository.findAuthorByBook(title);
    }

    @Override
    public List<Object[]> findAllAuthorsWithBooks()
    {
        return authorRepository.findAllAuthorsWithBooks();
    }
}
