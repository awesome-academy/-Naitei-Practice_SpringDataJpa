package org.example.service.author;

import org.example.entity.Author;
import org.example.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AuthorServiceImpl implements AuthorService{
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthor(){
        return authorRepository.findAll();
    }
    @Override
    public Optional<Author> getAuthorByKeyword(String keyword)
    {
        return authorRepository.findByFirstnameContainingIgnoreCase(keyword);
    }
    @Override
    public Optional<Author> getAuthorByFirstname(String firstname)
    {
        return authorRepository.findAuthorByFirstname(firstname);
    }
    public Author saveAuthor(Author author)
    {
        return authorRepository.save(author);
    }
    public Optional<Author> deleteAuthorById(Long id)
    {
        return authorRepository.deleteInBulkById(id);
    }
}
