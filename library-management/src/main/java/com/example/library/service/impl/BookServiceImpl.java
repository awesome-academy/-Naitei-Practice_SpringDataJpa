package com.example.library.service.impl;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

  @Autowired
  private BookRepository bookRepository;

  @Override
  @Transactional 
  public Book createBook(Book book) {
      return bookRepository.save(book);
  }

  @Override
  @Transactional
  public Book updateBook(Book book) {
      return bookRepository.save(book);
  }

  @Override
  @Transactional
  public void deleteBook(Long id) {
      bookRepository.deleteById(id);
  }

  @Override
  public Optional<Book> getBookById(Long id) {
      return bookRepository.findById(id);
  }

  @Override
  public Page<Book> getAllBooks(Pageable pageable) {
      return bookRepository.findAll(pageable);
  }
}
