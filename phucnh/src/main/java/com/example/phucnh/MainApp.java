package com.example.phucnh;

import com.example.phucnh.cli.BookCLI;
import com.example.phucnh.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        BookService bookService = context.getBean(BookService.class);
        BookCLI cli = new BookCLI(bookService);
        cli.run();
    }
}
