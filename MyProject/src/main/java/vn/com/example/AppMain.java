package vn.com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vn.com.example.config.JpaConfig;
import vn.com.example.entity.Author;
import vn.com.example.entity.Book;
import vn.com.example.service.AuthorService;
import vn.com.example.service.BookService;

import java.util.Set;
import java.util.stream.Collectors;

public class AppMain {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class)) {
            BookService bookService = context.getBean(BookService.class);
            AuthorService authorService = context.getBean(AuthorService.class);

            System.out.println("\n=== Bắt đầu Demo với 6 phương thức của AuthorService ===\n");

            // 1. CREATE AUTHOR
            System.out.println("--- 1. createAuthor ---");
            Author author1 = authorService.createAuthor(new Author("J.K. Rowling"));
            Author author2 = authorService.createAuthor(new Author("J.R.R. Tolkien"));
            Author author3 = authorService.createAuthor(new Author("George R.R. Martin"));
            System.out.println("Đã tạo 3 tác giả.");

            // 2. FIND ALL AUTHORS
            System.out.println("\n--- 2. findAllAuthors ---");
            System.out.println("Danh sách tất cả tác giả: " + authorService.findAllAuthors());

            // 3. FIND AUTHOR BY ID
            System.out.println("\n--- 3. findAuthorById ---");
            authorService.findAuthorById(author2.getId()).ifPresent(author -> System.out.println("Tìm thấy tác giả với ID " + author2.getId() + ": " + author));

            // 4. FIND AUTHORS BY NAME
            System.out.println("\n--- 4. findAuthorsByName (tìm kiếm với 'rowling') ---");
            System.out.println("Kết quả tìm kiếm: " + authorService.findAuthorsByName("rowling"));

            // 5. UPDATE AUTHOR
            System.out.println("\n--- 5. updateAuthor (đổi tên tác giả ID " + author3.getId() + ") ---");
            Author updatedDetails = new Author("George Raymond Richard Martin");
            authorService.updateAuthor(author3.getId(), updatedDetails);
            authorService.findAuthorById(author3.getId()).ifPresent(author -> System.out.println("Tên mới: " + author.getName()));

            // 6. DELETE AUTHOR (với kiểm tra logic)
            System.out.println("\n--- 6. deleteAuthor (thử xóa tác giả đang có sách) ---");
            // Tạo sách và gán cho J.K. Rowling
            Book bookForDeleteTest = bookService.createBook(new Book("Harry Potter"));
            bookService.addBookAuthor(bookForDeleteTest.getId(), author1.getId());
            System.out.println("Đã tạo sách '" + bookForDeleteTest.getTitle() + "' cho tác giả '" + author1.getName() + "'");

            try {
                // Lệnh này sẽ thất bại và văng ra exception như mong đợi
                authorService.deleteAuthor(author1.getId());
            } catch (IllegalStateException e) {
                System.out.println("=> BẮT LỖI THÀNH CÔNG: " + e.getMessage());
            }

            // Xóa sách trước, sau đó xóa tác giả
            bookService.deleteBookById(bookForDeleteTest.getId());
            System.out.println("Đã xóa sách '" + bookForDeleteTest.getTitle() + "', bây giờ có thể xóa tác giả.");
            authorService.deleteAuthor(author1.getId());
            System.out.println("Đã xóa thành công tác giả '" + author1.getName() + "'.");
            System.out.println("Danh sách tác giả còn lại: " + authorService.findAllAuthors());


            System.out.println("\n\n=== Bắt đầu Demo với 7 phương thức của BookService ===\n");

            // --- Setup cho BookService demo ---
            System.out.println("--- Setup: Tạo sách mới cho demo ---");
            Book lotrBook = bookService.createBook(new Book("The Lord of the Rings"));
            bookService.addBookAuthor(lotrBook.getId(), author2.getId()); // Gán cho Tolkien
            System.out.println("Đã tạo sách '" + lotrBook.getTitle() + "' của tác giả '" + author2.getName() + "'");

            // 1. UPDATE BOOK TITLE
            System.out.println("\n--- 1. updateBookTitle ---");
            bookService.updateBookTitle(lotrBook.getId(), "Chúa tể những chiếc nhẫn");
            System.out.println("Tên sách sau khi cập nhật: " + bookService.findBookById(lotrBook.getId()).get().getTitle());

            // 2. ADD BOOK AUTHOR
            System.out.println("\n--- 2. addBookAuthor (thêm Martin vào sách) ---");
            bookService.addBookAuthor(lotrBook.getId(), author3.getId());
            System.out.print("Danh sách tác giả mới: ");
            bookService.findBookByIdWithAuthors(lotrBook.getId()).ifPresent(book ->
                    System.out.println(book.getAuthors().stream().map(Author::getName).collect(Collectors.joining(", ")))
            );

            // 3. REMOVE BOOK AUTHOR
            System.out.println("\n--- 3. removeBookAuthor (xóa Tolkien khỏi sách) ---");
            bookService.removeBookAuthor(lotrBook.getId(), author2.getId());
            System.out.print("Danh sách tác giả còn lại: ");
            bookService.findBookByIdWithAuthors(lotrBook.getId()).ifPresent(book ->
                    System.out.println(book.getAuthors().stream().map(Author::getName).collect(Collectors.joining(", ")))
            );

            // 4. FIND BOOKS BY AUTHOR ID
            // Tạo thêm sách cho Martin để thấy kết quả rõ hơn
            Book gotBook = bookService.createBook(new Book("A Game of Thrones"));
            bookService.addBookAuthor(gotBook.getId(), author3.getId());
            System.out.println("\n--- 4. findBooksByAuthorId (tìm tất cả sách của Martin) ---");
            System.out.println("Sách của tác giả '" + author3.getName() + "': " + bookService.findBooksByAuthorId(author3.getId()));

            // 5. REPLACE BOOK AUTHORS
            System.out.println("\n--- 5. replaceBookAuthors (thay tác giả của 'A Game of Thrones' thành Tolkien) ---");
            bookService.replaceBookAuthors(gotBook.getId(), Set.of(author2.getId()));
            System.out.print("Tác giả mới của sách " + gotBook.getId() + ": ");
            bookService.findBookByIdWithAuthors(gotBook.getId()).ifPresent(book ->
                    System.out.println(book.getAuthors().stream().map(Author::getName).collect(Collectors.joining(", ")))
            );

            // 6. FIND BOOK BY ID WITH AUTHORS
            System.out.println("\n--- 6. findBookByIdWithAuthors (kiểm tra lại sách của Tolkien) ---");
            System.out.println("Sách của tác giả '" + author2.getName() + "': " + bookService.findBooksByAuthorId(author2.getId()));

            // 7. DELETE BOOK BY ID
            System.out.println("\n--- 7. deleteBookById (xóa 2 sách đã tạo) ---");
            bookService.deleteBookById(lotrBook.getId());
            bookService.deleteBookById(gotBook.getId());
            System.out.println("Đã xóa 2 cuốn sách. Danh sách sách còn lại: " + bookService.findAllBooks());

        } catch (Exception e) {
            System.err.println("\n!!! ĐÃ XẢY RA LỖI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}