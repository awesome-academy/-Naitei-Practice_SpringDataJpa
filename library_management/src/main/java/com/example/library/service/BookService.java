package com.example.library.service;

import com.example.library.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book saveBook(Book book);
    Book getBookByTitle(String title);
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);
    Book findBookById(Long id);
}
