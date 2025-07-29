
package org.example.service.impl;

import java.util.Optional;
import org.example.entity.Book;
import org.example.repository.BookRepo;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepo bookRepo;

    public BookServiceImpl() {
    }

    @Transactional
    public Book saveBook(Book book) {
        return (Book)this.bookRepo.save(book);
    }

    public Book getBookById(Long id) {
        return (Book)this.bookRepo.findById(id).orElse(null);
    }

    @Transactional(
            readOnly = true
    )
    public Iterable<Book> getAllBooks() {
        return this.bookRepo.findAll();
    }

    public Optional<Book> findByIsbn(String isbn) {
        return this.bookRepo.findByIsbn(isbn);
    }

    @Transactional
    public void deleteBook(Long id) {
        this.bookRepo.deleteById(id);
    }

    @Transactional(
            readOnly = true
    )
    public void printAllBookTitles() {
        this.bookRepo.findAll().forEach((b) -> {
            System.out.println(b.getTitle());
        });
    }
}
