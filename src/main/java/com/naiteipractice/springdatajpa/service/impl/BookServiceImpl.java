package com.naiteipractice.springdatajpa.service.impl;

import com.naiteipractice.springdatajpa.entity.Book;
import com.naiteipractice.springdatajpa.repository.BookRepository;
import com.naiteipractice.springdatajpa.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    
    private final BookRepository bookRepository;
    
    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }
    
    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Book> findBooksByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Book> findBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Book> findBooksByPublicationYearAfter(Integer year) {
        return bookRepository.findBooksPublishedAfterYear(year);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Book> findBooksByPriceRange(Double minPrice, Double maxPrice) {
        return bookRepository.findByPriceBetween(minPrice, maxPrice);
    }
}
