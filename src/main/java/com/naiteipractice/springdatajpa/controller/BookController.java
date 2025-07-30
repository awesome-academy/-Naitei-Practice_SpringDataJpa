package com.naiteipractice.springdatajpa.controller;

import com.naiteipractice.springdatajpa.entity.Book;
import com.naiteipractice.springdatajpa.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    
    private final BookService bookService;
    
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        logger.info("REST request to create a new book: {}", book.getTitle());
        Book savedBook = bookService.saveBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        logger.info("REST request to get book with ID: {}", id);
        Optional<Book> bookOptional = bookService.getBookById(id);
        return bookOptional
                .map(book -> new ResponseEntity<>(book, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        logger.info("REST request to get all books");
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        logger.info("REST request to update book with ID: {}", id);
        if (!bookService.getBookById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        book.setId(id);
        Book updatedBook = bookService.updateBook(book);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        logger.info("REST request to delete book with ID: {}", id);
        if (!bookService.getBookById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    

    @GetMapping("/by-author")
    public ResponseEntity<List<Book>> findBooksByAuthor(@RequestParam String author) {
        logger.info("REST request to search books by author: {}", author);
        List<Book> books = bookService.findBooksByAuthor(author);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    

    @GetMapping("/by-title")
    public ResponseEntity<List<Book>> findBooksByTitle(@RequestParam String title) {
        logger.info("REST request to search books by title: {}", title);
        List<Book> books = bookService.findBooksByTitle(title);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    

    @GetMapping("/by-year")
    public ResponseEntity<List<Book>> findBooksByPublicationYearAfter(@RequestParam Integer year) {
        logger.info("REST request to search books published after year: {}", year);
        List<Book> books = bookService.findBooksByPublicationYearAfter(year);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/by-price-range")
    public ResponseEntity<List<Book>> findBooksByPriceRange(
            @RequestParam Double minPrice, 
            @RequestParam Double maxPrice) {
        logger.info("REST request to search books in price range: {} - {}", minPrice, maxPrice);
        List<Book> books = bookService.findBooksByPriceRange(minPrice, maxPrice);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
