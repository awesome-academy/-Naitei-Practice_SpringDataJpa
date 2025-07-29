package com.example.blogapp;

import com.example.blogapp.config.AppConfig;
import com.example.blogapp.service.services.UserService;
import com.example.blogapp.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        
        UserService userService = context.getBean(UserService.class);

        
        List<User> users = userService.findAll();
        for (User user : users) {
            System.out.println(user.getId() + " | " + user.getName() + " | " + user.getEmail());
        }

        
        context.close();
    }
}
