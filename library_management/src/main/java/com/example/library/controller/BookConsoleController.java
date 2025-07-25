package com.example.library.controller;

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.entity.BookDetail;
import com.example.library.service.AuthorService;
import com.example.library.service.BookService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Component
public class BookConsoleController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final Scanner scanner = new Scanner(System.in);

    public BookConsoleController(BookService bookService, AuthorService authorService){
        this.bookService = bookService;
        this.authorService = authorService;
    }

    public void start(){
        while(true) {
            System.out.println("\n=====BOOK MANAGEMENT=====");
            System.out.println("1. Show all books");
            System.out.println("2. Add new book");
            System.out.println("3. Update book");
            System.out.println("4. Find book by name");
            System.out.println("5. Delete book");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1: getAllBooks(); break;
                case 2: saveBook(); break;
                case 3: updateBook(); break;
                case 4: findBookByName(); break;
                case 5: deleteBook(); break;
                case 0: {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default: System.out.println("Invalid option!");
            }
        }
    }

    private void getAllBooks(){
        List<Book> books = bookService.getAllBooks();
        if(books.isEmpty()){
            System.out.println("No book found");
        }
        else {
            books.forEach(System.out::println);
        }
    }

    private void saveBook(){
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String authorName = scanner.nextLine();
        Author author = authorService.findByName(authorName);
        if (author == null) {
            author = new Author();
            authorService.saveAuthor(author);
        }

        System.out.print("Enter language: ");
        String language = scanner.nextLine();

        System.out.print("Enter published date (yyyy-mm-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter book description: ");
        String description = scanner.nextLine();

        BookDetail detail = new BookDetail();
        detail.setDescription(description);
        detail.setLanguage(language);

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublishedDay(date);
        book.setBookDetail(detail);

        bookService.saveBook(book);
        System.out.println("New book added!");
    }

    private void findBookByName() {
        System.out.print("Enter book title to search: ");
        String title = scanner.nextLine();

        Book book = bookService.getBookByTitle(title);
        if (book == null) {
            System.out.println("No book found with title: " + title);
        } else {
            System.out.println("Found: " + book.getTitle() +
                    " | Author: " + book.getAuthor().getName() +
                    " | Description: " + book.getBookDetail().getDescription());
        }
    }

    private void updateBook() {
        System.out.print("Enter ID of book to update: ");
        Long id = Long.parseLong(scanner.nextLine());

        Book existingBook = bookService.findBookById(id);
        if (existingBook == null) {
            System.out.println("Book not found!");
            return;
        }

        System.out.print("Enter new title (leave blank to keep current): ");
        String newTitle = scanner.nextLine();
        if (!newTitle.isEmpty()) existingBook.setTitle(newTitle);

        System.out.print("Enter new author name (leave blank to keep current): ");
        String authorName = scanner.nextLine();
        if (!authorName.isEmpty()) {
            Author author = authorService.findByName(authorName);
            if (author == null) {
                author = new Author();
                authorService.saveAuthor(author);
            }
            existingBook.setAuthor(author);
        }

        System.out.print("Enter new description (leave blank to keep current): ");
        String newDesc = scanner.nextLine();
        if (!newDesc.isEmpty()) {
            if (existingBook.getBookDetail() == null) {
                existingBook.setBookDetail(new BookDetail());
            }
            existingBook.getBookDetail().setDescription(newDesc);
        }

        bookService.updateBook(id, existingBook);
        System.out.println("Book updated successfully!");
    }

    private void deleteBook() {
        System.out.print("Enter ID of book to delete: ");
        Long id = Long.parseLong(scanner.nextLine());

        Book existingBook = bookService.findBookById(id);
        if (existingBook == null) {
            System.out.println("Book not found, cannot delete.");
            return;
        }

        try {
            bookService.deleteBook(id);
            System.out.println("Book deleted successfully!");
        } catch (Exception e) {
            System.out.println("Failed to delete book: " + e.getMessage());
        }
    }

}
