package com.example.library.service.impl;

import com.example.library.entity.Author;
import com.example.library.repository.AuthorRepository;
import com.example.library.service.AuthorService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorrepository){
        this.authorRepository = authorrepository;
    }

    @Override
    public Author saveAuthor(Author author){
        return authorRepository.save(author);
    };

    @Override
    public Author findByName(String name) {
        return authorRepository.findByName(name).orElse(null);
    }
}
