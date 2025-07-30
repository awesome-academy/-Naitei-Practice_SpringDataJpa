package com.example.library;
import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.model.Category;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.Optional;
import java.util.Set;
import java.util.List;

import com.example.library.config.AppConfig;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.CategoryRepository;
import com.example.library.service.BookService;

public class Main {
    public static void main(String[] args) {
        // 1. Khoi tao Spring Context
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // 2. Lay cac bean can thiet tu Context
        BookService bookService = context.getBean(BookService.class);
        BookRepository bookRepository = context.getBean(BookRepository.class);
        AuthorRepository authorRepository = context.getBean(AuthorRepository.class);
        CategoryRepository categoryRepository = context.getBean(CategoryRepository.class);

        System.out.println("==========================================================");
        System.out.println("BAT DAU");
        System.out.println("==========================================================");

        // --- Khoi tao du lieu mau ---
        System.out.println("\n[KHOI TAO] Tao du lieu mau\n");
        Category vanHocCategory = new Category();
        vanHocCategory.setName("Van hoc Viet Nam");
        categoryRepository.save(vanHocCategory);

        Category truyenNganCategory = new Category();
        truyenNganCategory.setName("Truyen Ngan");
        categoryRepository.save(truyenNganCategory);

        Author nguyenNhatAnh = new Author();
        nguyenNhatAnh.setName("Nguyen Nhat Anh");
        authorRepository.save(nguyenNhatAnh);

        Author namCao = new Author();
        namCao.setName("Nam Cao");
        authorRepository.save(namCao);
        System.out.println("=> Da tao xong du lieu mau.");


        // --- 1. THUC HIEN CREATE (Toi thieu 4 method CRUD) ---
        System.out.println("\n[CRUD - 1. CREATE] Tao mot cuon sach moi\n");
        Book matBiec = new Book();
        matBiec.setTitle("Mat Biec");
        matBiec.setCategory(vanHocCategory);
        matBiec.setAuthors(Set.of(nguyenNhatAnh));
        Book savedBook = bookService.createBook(matBiec);
        System.out.println("=> Da tao sach: '" + savedBook.getTitle() + "' (ID: " + savedBook.getId() + ")");


        // --- 2. THUC HIEN READ ---
        System.out.println("\n[CRUD - 2. READ] Tim lai cuon sach vua tao bang ID\n");
        Optional<Book> foundBook = bookService.getBookById(savedBook.getId());
        foundBook.ifPresent(b -> System.out.println("=> Tim thay sach: '" + b.getTitle() + "'"));


        // --- 3. THUC HIEN UPDATE ---
        System.out.println("\n[CRUD - 3. UPDATE] Cap nhat ten sach\n");
        foundBook.ifPresent(b -> {
            b.setTitle("Mat Biec (An ban 2024)");
            Book updatedBook = bookService.updateBook(b);
            System.out.println("=> Da cap nhat ten sach: '" + updatedBook.getTitle() + "', Version: " + updatedBook.getVersion());
        });


        // --- TAO THEM DU LIEU DE DEMO QUERY ---
        System.out.println("\n[KHOI TAO] Tao them sach de demo query\n");
        Book chiPheo = new Book();
        chiPheo.setTitle("Chi Pheo");
        chiPheo.setCategory(truyenNganCategory);
        chiPheo.setAuthors(Set.of(namCao));
        bookService.createBook(chiPheo);

        Book veDiTuoiTho = new Book();
        veDiTuoiTho.setTitle("Cho Toi Xin Mot Ve Di Tuoi Tho");
        veDiTuoiTho.setCategory(vanHocCategory);
        veDiTuoiTho.setAuthors(Set.of(nguyenNhatAnh));
        bookService.createBook(veDiTuoiTho);
        System.out.println("=> Da tao them 2 cuon sach.");


        // --- DEMO CAC TINH NANG KHAC ---
        System.out.println("\n[DEMO - Paging and Sorting] Liet ke sach theo trang (Trang 1, 5 cuon/trang)\n");
        Pageable pageable = PageRequest.of(0, 5, Sort.by("title").ascending());
        Page<Book> bookPage = bookService.getAllBooks(pageable);
        System.out.println("=> Tong so sach: " + bookPage.getTotalElements() + ", Tong so trang: " + bookPage.getTotalPages());
        bookPage.getContent().forEach(b -> System.out.println(" - " + b.getTitle()));

        System.out.println("\n[DEMO - Derived Query] Tim sach theo the loai 'Van hoc Viet Nam'\n");
        List<Book> vhBooks = bookRepository.findByCategoryName("Van hoc Viet Nam");
        System.out.println("=> Tim thay " + vhBooks.size() + " cuon:");
        vhBooks.forEach(b -> System.out.println(" - '" + b.getTitle() + "' trong the loai Van hoc Viet Nam"));

        System.out.println("\n[DEMO - Custom @Query] Tim sach cua tac gia 'Nam Cao'\n");
        List<Book> namCaoBooks = bookRepository.findByAuthorName("Nam Cao");
        System.out.println("=> Tim thay " + namCaoBooks.size() + " cuon:");
        namCaoBooks.forEach(b -> System.out.println(" - '" + b.getTitle() + "' cua tac gia Nam Cao"));


        // --- 4. THUC HIEN DELETE ---
        System.out.println("\n[CRUD - 4. DELETE] Xoa cuon sach vua tao\n");
        Long bookIdToDelete = savedBook.getId();
        bookService.deleteBook(bookIdToDelete);
        System.out.println("=> Da gui yeu cau xoa sach co ID: " + bookIdToDelete);
        boolean isPresentAfterDelete = bookService.getBookById(bookIdToDelete).isPresent();
        System.out.println("=> Kiem tra lai sach co ID " + bookIdToDelete + ": Ton tai? -> " + isPresentAfterDelete);


        System.out.println("\n==========================================================");
        System.out.println("KET THUC");
        System.out.println("==========================================================");

        // Dong context de giai phong tai nguyen
        context.close();
    }
}