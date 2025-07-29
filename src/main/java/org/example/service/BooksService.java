package org.example.service;

import org.example.model.Books;

import java.util.List;

public interface BooksService {
    public List<Books> findAllBooks();

    public Books findBookById(int id);

    public void saveBook(Books book);

    public List<Books> findAllBooksByTitle(String title);

    public List<Books> findAllBooksByPublisher(String publisher);

    public void deleteBook(Books book);

    public void updateBook(Books book);
}
