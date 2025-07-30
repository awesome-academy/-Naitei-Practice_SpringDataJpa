package vn.com.example.service;

import vn.com.example.entity.Book;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookService {
    // 1. CREATE
    Book createBook(Book book);

    // 2. READ
    Optional<Book> findBookById(Long id);
    Optional<Book> findBookByIdWithAuthors(Long id); // Tìm sách kèm tác giả
    List<Book> findAllBooks();
    List<Book> findBooksByAuthorId(Long authorId);

    // 3. UPDATE
    Book updateBookTitle(Long id, String newTitle); // Chỉ cập nhật tiêu đề
    Book addBookAuthor(Long bookId, Long authorId);
    Book removeBookAuthor(Long bookId, Long authorId);
    Book replaceBookAuthors(Long bookId, Set<Long> authorIds);

    // 4. DELETE
    void deleteBookById(Long id);
}