package com.example.library.service.impl;

import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookrepository){
        this.bookRepository = bookrepository;
    }

    @Override
    public Book saveBook(Book book){
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @Override
    public Book getBookByTitle(String title) {
        return bookRepository.findFirstByTitle(title).orElse(null);
    }

    @Override
    public Book updateBook(Long id, Book book){ return bookRepository.save(book); }

    @Override
    public void deleteBook(Long id){
        Book book = bookRepository.findById(id).orElseThrow(()->new RuntimeException("No book found with id: " + id));
        System.out.println("Delete book: " +book.getTitle());
        bookRepository.deleteById(id);
    }

    @Override
    public Book findBookById(Long id){
        return bookRepository.findById(id).orElse(null);
    }
}
