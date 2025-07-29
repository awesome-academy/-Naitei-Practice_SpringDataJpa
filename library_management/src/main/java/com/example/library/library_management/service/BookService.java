package com.example.library.library_management.service;

import java.util.List;

import com.example.library.library_management.entity.Book;

public interface BookService {
    Book create(Book book);
    Book getById(Long id);
    Book updateTitle(Long id, String newTitle);
    void delete(Long id);
    List<Book> getAll();
}
