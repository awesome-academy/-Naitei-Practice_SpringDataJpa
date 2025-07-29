package vn.com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.example.entity.Author;
import vn.com.example.repository.AuthorRepository;
import vn.com.example.service.AuthorService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public Author createAuthor(Author author) {
        if (author.getName() == null || author.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên tác giả không được để trống.");
        }
        return authorRepository.save(author);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Author> findAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> findAuthorsByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return List.of(); // Trả về danh sách rỗng nếu tên tìm kiếm trống
        }
        return authorRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    @Transactional
    public Author updateAuthor(Long id, Author authorDetails) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy tác giả với ID: " + id));

        if (authorDetails.getName() != null && !authorDetails.getName().trim().isEmpty()) {
            existingAuthor.setName(authorDetails.getName());
        }
        // Sau này nếu Author có thêm trường 'biography', 'birthDate',...
        // thì có thể cập nhật chúng ở đây.
        // existingAuthor.setBiography(authorDetails.getBiography());

        return authorRepository.save(existingAuthor);
    }

    @Override
    @Transactional
    public void deleteAuthor(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không thể xóa. Không tìm thấy tác giả với ID: " + id));

        if (!author.getBooks().isEmpty()) {
            throw new IllegalStateException("Không thể xóa tác giả '" + author.getName() + "' vì vẫn còn sách liên quan.");
        }
        authorRepository.deleteById(id);
    }
}