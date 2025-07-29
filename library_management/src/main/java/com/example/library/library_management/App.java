package com.example.library.library_management;

import java.time.LocalDate;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.library.library_management.config.AppConfig;
import com.example.library.library_management.entity.Author;
import com.example.library.library_management.entity.Book;
import com.example.library.library_management.entity.Genre;
import com.example.library.library_management.entity.Publisher;
import com.example.library.library_management.service.BookService;

public class App {
	public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        BookService bookService = context.getBean(BookService.class);

        Publisher publisher = new Publisher("O'Reilly", "USA", "contact@oreilly.com");
        Author author = new Author("Robert Martin", "Author of Clean Code");
        Genre genre = new Genre("Programming", "Software development");

        Book book = new Book("Clean Code", "A handbook of agile software craftsmanship", LocalDate.of(2008, 8, 1), 10);
        book.setPublisher(publisher);
        book.getAuthors().add(author);
        book.getGenres().add(genre);

        // CREATE
        bookService.create(book);

        // READ
        System.out.println("All books: " + bookService.getAll());

        // UPDATE
        bookService.updateTitle(book.getId(), "Clean Code - Updated");

        // DELETE
        // bookService.delete(book.getId());

        context.close();
    }
}
