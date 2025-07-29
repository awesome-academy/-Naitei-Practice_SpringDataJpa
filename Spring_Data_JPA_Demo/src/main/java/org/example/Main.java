package org.example;

import org.example.config.AppConfig;
import org.example.entity.Author;
import org.example.entity.Book;
import org.example.service.AuthorService;
import org.example.service.BookService;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BookService bookService = context.getBean(BookService.class);
        AuthorService authorService = context.getBean(AuthorService.class);

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Thêm Book");
            System.out.println("2. Hiển thị danh sách Book");
            System.out.println("3. Tìm Book theo ISBN");
            System.out.println("4. Xóa Book theo ID");
            System.out.println("5. Thêm Author");
            System.out.println("6. Hiển thị danh sách Author");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            switch (choice) {
                case 1 -> {
                    Iterable<Author> authors = authorService.getAllAuthors();
                    if (!authors.iterator().hasNext()) {
                        System.out.println("Chưa có Author nào. Vui lòng thêm Author trước.");
                        break;
                    }

                    System.out.print("Nhập Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Nhập ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Nhập Năm Xuất Bản: ");
                    int year = scanner.nextInt();
                    scanner.nextLine(); // clear buffer

                    System.out.println("Danh sách Author:");
                    authors.forEach(a -> System.out.println("ID: " + a.getId() + " - " + a.getName()));

                    System.out.print("Nhập ID Author: ");
                    Long authorId = scanner.nextLong();
                    scanner.nextLine(); // clear buffer

                    Author selectedAuthor = authorService.getAuthorById(authorId);
                    if (selectedAuthor == null) {
                        System.out.println("Author không tồn tại.");
                        break;
                    }

                    Book book = new Book();
                    book.setTitle(title);
                    book.setIsbn(isbn);
                    book.setPublishedYear(year);
                    book.setAuthors(Arrays.asList(selectedAuthor));

                    bookService.saveBook(book);
                    System.out.println("==> Đã thêm Book.");
                }

                case 2 -> {
                    System.out.println("==> Danh sách Book:");
                    bookService.getAllBooks().forEach(b -> System.out.println("ID: " + b.getId() + " - " + b.getTitle()));
                }
                case 3 -> {
                    System.out.print("Nhập ISBN cần tìm: ");
                    String searchIsbn = scanner.nextLine();
                    Optional<Book> foundBook = bookService.findByIsbn(searchIsbn);
                    if (foundBook.isPresent()) {
                        Book b = foundBook.get();
                        System.out.println("Tìm thấy Book: " + b.getTitle());
                    } else {
                        System.out.println("Không tìm thấy Book.");
                    }
                }
                case 4 -> {
                    System.out.print("Nhập ID Book cần xóa: ");
                    Long id = scanner.nextLong();
                    scanner.nextLine();
                    bookService.deleteBook(id);
                    System.out.println("==> Đã xóa Book.");
                }
                case 5 -> {
                    System.out.print("Nhập Tên Author: ");
                    String name = scanner.nextLine();
                    System.out.print("Nhập Email: ");
                    String email = scanner.nextLine();
                    Author author = new Author();
                    author.setName(name);
                    author.setEmail(email);
                    authorService.saveAuthor(author);
                    System.out.println("==> Đã thêm Author.");
                }
                case 6 -> {
                    System.out.println("==> Danh sách Author:");
                    authorService.getAllAuthors().forEach(a -> System.out.println("ID: " + a.getId() + " - " + a.getName() + " (" + a.getEmail() + ")"));
                }
                case 0 -> System.out.println("==> Thoát chương trình.");
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 0);

        context.close();
        scanner.close();
    }
}
