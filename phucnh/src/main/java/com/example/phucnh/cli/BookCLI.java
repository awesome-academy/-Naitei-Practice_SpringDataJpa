package com.example.phucnh.cli;

import com.example.phucnh.model.Book;
import com.example.phucnh.service.BookService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class BookCLI {
    private final BookService bookService;

    public BookCLI(BookService bookService) {
        this.bookService = bookService;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== BOOK MANAGEMENT MENU =====");
            System.out.println("1. Add new book");
            System.out.println("2. View all books");
            System.out.println("3. View book by ID");
            System.out.println("4. Delete book by ID");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter price: ");
                    double price = Double.parseDouble(scanner.nextLine());
                    Book newBook = new Book(title, author, price);
                    bookService.createBook(newBook);
                    System.out.println("Book added!");
                    break;
                case "2":
                    List<Book> books = bookService.getAllBooks();
                    books.forEach(book -> System.out.println(bookInfo(book)));
                    break;
                case "3":
                    System.out.print("Enter ID: ");
                    Long id = Long.parseLong(scanner.nextLine());
                    Optional<Book> book = bookService.getBookById(id);
                    book.ifPresentOrElse(
                            b -> System.out.println(bookInfo(b)),
                            () -> System.out.println("Book not found!")
                    );
                    break;
                case "4":
                    System.out.print("Enter ID to delete: ");
                    Long delId = Long.parseLong(scanner.nextLine());
                    Optional<Book> bookToDelete = bookService.getBookById(delId);
                    if (bookToDelete.isPresent()) {
                        bookService.deleteBook(delId);
                        System.out.println("Book deleted successfully.");
                    } else {
                        System.out.println("Book with ID " + delId + " does not exist.");
                    }
                    break;
                case "0":
                    System.out.println("Bye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private String bookInfo(Book b) {
        return String.format(" ID: %d | Title: %s | Author: %s | Price: %.2f",
                b.getId(), b.getTitle(), b.getAuthor(), b.getPrice());
    }
}
