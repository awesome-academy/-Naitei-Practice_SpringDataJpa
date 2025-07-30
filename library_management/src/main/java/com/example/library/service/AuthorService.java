package com.example.library.service;

import com.example.library.entity.Author;

public interface AuthorService {
    Author saveAuthor(Author author);
    Author findByName(String name);
}
