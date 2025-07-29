package vn.com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.example.entity.Author;
import vn.com.example.entity.Book;
import vn.com.example.repository.AuthorRepository;
import vn.com.example.repository.BookRepository;
import vn.com.example.service.BookService;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    // --- CREATE ---
    @Override
    @Transactional
    public Book createBook(Book book) {
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Tiêu đề sách không được để trống.");
        }
        return bookRepository.save(book);
    }

    // --- READ ---
    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findBookByIdWithAuthors(Long id) {
        return bookRepository.findByIdWithAuthors(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findBooksByAuthorId(Long authorId) {
        if (!authorRepository.existsById(authorId)) {
            throw new EntityNotFoundException("Không tìm thấy tác giả với ID: " + authorId);
        }
        return bookRepository.findByAuthorId(authorId);
    }

    // --- UPDATE ---
    @Override
    @Transactional
    public Book updateBookTitle(Long id, String newTitle) {
        if (newTitle == null || newTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Tiêu đề mới không được để trống.");
        }
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy sách để cập nhật với ID: " + id));
        book.setTitle(newTitle);
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book addBookAuthor(Long bookId, Long authorId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy sách với ID: " + bookId));
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy tác giả với ID: " + authorId));

        book.addAuthor(author);
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book removeBookAuthor(Long bookId, Long authorId) {
        Book book = bookRepository.findByIdWithAuthors(bookId) // Lấy kèm tác giả để thao tác
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy sách với ID: " + bookId));
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy tác giả với ID: " + authorId));

        if(book.getAuthors().contains(author)) {
            book.removeAuthor(author);
        } else {
            throw new IllegalStateException("Tác giả không thuộc về cuốn sách này.");
        }
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book replaceBookAuthors(Long bookId, Set<Long> authorIds) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy sách với ID: " + bookId));

        List<Author> newAuthors = authorRepository.findAllById(authorIds);
        if (newAuthors.size() != authorIds.size()) {
            throw new EntityNotFoundException("Một hoặc nhiều tác giả không tồn tại.");
        }

        // Xóa các author cũ và thêm các author mới
        book.getAuthors().clear();
        newAuthors.forEach(book::addAuthor);

        return bookRepository.save(book);
    }

    // --- DELETE ---
    @Override
    @Transactional
    public void deleteBookById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Không thể xóa. Sách với ID " + id + " không tồn tại.");
        }
        bookRepository.deleteById(id);
    }
}