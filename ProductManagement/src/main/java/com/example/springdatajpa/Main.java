package com.example.springdatajpa;

import com.example.springdatajpa.config.AppConfig;
import com.example.springdatajpa.controller.ProductConsoleController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        ProductConsoleController controller = context.getBean(ProductConsoleController.class);
        controller.run();

        context.close();
    }
}