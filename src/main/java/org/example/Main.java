package org.example;

import org.example.config.AppConfig;
import org.example.model.Books;
import org.example.model.User;
import org.example.service.BooksService;
import org.example.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        BooksService booksService = context.getBean(BooksService.class);
        UserService userService = context.getBean(UserService.class);

        // ----------- DEMO BooksService -----------------
        System.out.println("=== DEMO BooksService ===");

        // 1. Add new book
        Books book = new Books();
        book.setTitle("Spring in Action");
        book.setPublisher("Manning");
        booksService.saveBook(book);

        // 2. Find all books
        List<Books> books = booksService.findAllBooks();
        books.forEach(b -> System.out.println("Book: " + b.getTitle() + ", Publisher: " + b.getPublisher()));

        // 3. Find by ID
        Books foundBook = booksService.findBookById(book.getID());
        System.out.println("Found Book: " + foundBook.getTitle());

        // 4. Find by Title
        List<Books> booksByTitle = booksService.findAllBooksByTitle("Spring in Action");
        booksByTitle.forEach(b -> System.out.println("By Title: " + b.getTitle()));

        // 5. Find by Publisher
        List<Books> booksByPublisher = booksService.findAllBooksByPublisher("Manning");
        booksByPublisher.forEach(b -> System.out.println("By Publisher: " + b.getPublisher()));

        // 6. Update Book
        foundBook.setPublisher("Reil");
        booksService.updateBook(foundBook);

        // 7. Delete Book
        booksService.deleteBook(foundBook);

        // ----------- DEMO UserService -----------------
        System.out.println("\n=== DEMO UserService ===");

        // 1. Add new User
        User user = new User();
        user.setName("Van Ly Tuan");
        userService.addUser(user);

        // 2. Find All Users
        List<User> users = userService.findAll();
        users.forEach(u -> System.out.println("User: " + u.getName()));

        // 3. Find User by ID
        User foundUser = userService.findById(user.getID());
        System.out.println("Found User: " + foundUser.getName());

        // 4. Delete User
        userService.deleteUser(foundUser);

        context.close();
    }
}
