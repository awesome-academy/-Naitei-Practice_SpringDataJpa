package com.example.library.library_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.library_management.entity.Book;
import com.example.library.library_management.repository.BookRepository;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book create(Book book) { return bookRepository.save(book); }

    @Override
    public Book getById(Long id) { return bookRepository.findById(id).orElse(null); }

    @Override
    public Book updateTitle(Long id, String newTitle) {
        Book book = bookRepository.findById(id).orElseThrow(null);
        book.setTitle(newTitle);
        return bookRepository.save(book);
    }

    @Override
    public void delete(Long id) { bookRepository.deleteById(id); }

    @Override
    public List<Book> getAll() { return bookRepository.findAll(); }
}
