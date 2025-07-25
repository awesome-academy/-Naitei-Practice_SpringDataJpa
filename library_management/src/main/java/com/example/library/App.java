package com.example.library;

import com.example.library.config.LibConfig;
import com.example.library.controller.BookConsoleController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App {
    public static void main (String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LibConfig.class);

        BookConsoleController controller = context.getBean(BookConsoleController.class);
        controller.start();

        context.close();
    }
}
