package org.example.controller;

import org.example.entity.Author;
import org.example.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.util.List;

import java.util.Scanner;
@Component
public class AuthorController {

    private final AuthorService authorService;
    private final Scanner scanner;

    @Autowired AuthorController(AuthorService authorService,Scanner scanner)
    {
        this.authorService = authorService;
        this.scanner = scanner;
    }

    public void showMenu()
    {
      int choice ;
      do{
          System.out.println("\n=== AUTHOR MANAGEMENT MENU ===");
          System.out.println("1. Hiển thị tất cả tác giả");
          System.out.println("2. Tìm tác giả theo tên");
          System.out.println("3. Xóa tác giả theo ID");
          System.out.println("4. Thêm tác giả mới");
          System.out.println("5. Tìm tác giả theo tên sách");
          System.out.println("6. Hiển thị tất cả tác giả cùng với sách mà họ viết");
          System.out.println("0. Thoát");
          System.out.print("Chọn chức năng: ");
          choice = scanner.nextInt();
          scanner.nextLine(); // Consume newline

          switch (choice)
          {
              case 1 -> displayAllAuthors();
              case 2 -> searchAuthorByFirstname();
              case 3 -> deleteAuthorById();
              case 4 -> addNewAuthor();
              case 5 -> findAuthorByBook();
              case 6 -> findAllAuthorsWithBooks();
              case 0 -> System.out.println("Thoát chương trình.");
              default -> System.out.println("Chức năng không hợp lệ!");
          }

      }while (choice!=0);


    }
    public void displayAllAuthors()
    {
        List<Author> author = authorService.getAllAuthor();

        if(author.isEmpty())
        {
            System.out.println("Không có tác giả nào.");
        }else
        {
            author.forEach(System.out::println);
        }
    }
    public void searchAuthorByFirstname()
    {
        System.out.println("Xin mời nhập tác giả muốn tìm.");
        String firstname = scanner.nextLine();
        List<Author> author = authorService.findByFirstname(firstname);
        if(author.isEmpty())
        {
            System.out.println("Không có tác giả nào.");
        }else
        {
            System.out.println("Tim thay tac gia:");
            author.forEach(System.out::println);
        }
    }
    public void deleteAuthorById()
    {
        List<Author> author = authorService.getAllAuthor();
        if(author.isEmpty())
        {
            System.out.println("Không thê xóa vì danh sách rỗng.");
        }else
        {
            author.forEach(System.out::println);
            System.out.println("Xin mời chọn Id tác giả muốn xóa");
            Long id = scanner.nextLong();
            authorService.deleteAuthorById(id);
            System.out.println("Xóa thành công!");

        }
    }
    public void addNewAuthor()
    {
        System.out.println("Nhập tên tác giả");
        String firstname = scanner.nextLine();
        System.out.println("Nhập họ tác giả");
        String lastname = scanner.nextLine();
        Author newAuthor = new Author();
        newAuthor.setFirstname(firstname);
        newAuthor.setLastname(lastname);
        authorService.saveAuthor(newAuthor);
        System.out.println("Them tac gia thanh cong!");

    }

    public void findAuthorByBook()
    {
        System.out.println(("Hãy nhập tên cuốn sách"));
        String title = scanner.nextLine();
        List<Object[]> authors = authorService.findAuthorByBook(title);
        if(authors.isEmpty())
        {
            System.out.println(("Khong co tac gia nao cua cuon sach nay"));
        }
        else {
            System.out.println(("Tác giả của cuốn sách ") + title+ "là:");
            for (Object[] row : authors) {
                String firstname = (String) row[0];
                String lastname = (String) row[1];
                String bookTitle = (String) row[2];
                System.out.println(firstname + " " + lastname + " - " + bookTitle);
            }
        }
    }

    public void findAllAuthorsWithBooks()
    {
        List<Object[]> author = authorService.findAllAuthorsWithBooks();

        if(author.isEmpty())
        {
            System.out.println("Không có tác giả nào.");
        }else
        {
            for (Object[] row : author) {
                //Long id = (Long) row[0];
                String firstname = (String) row[1];
                String lastname = (String) row[2];
                String bookTitle = (String) row[3];
                System.out.println( firstname + " " + lastname + " - " + bookTitle);
            }
        }
    }
}
