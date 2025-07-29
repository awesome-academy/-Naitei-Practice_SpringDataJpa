package org.example;

import org.example.config.AppConfig;
import org.example.models.User;
import org.example.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Khởi tạo context từ AppConfig
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Lấy bean UserService
        UserService userService = context.getBean(UserService.class);

        // Tạo user mới
        User user = new User();
        user.setUsername("Nguyen Van A");
        user.setRole("admin");

        // Lưu user
        userService.add(user);

        // Xóa user
        userService.delete(1L);

        // Get user
        List<User> users = userService.getAllUser();
        users.forEach(System.out::println);

        // Update user đầu tiên
        if (!users.isEmpty()) {
            User user1 = users.get(0);
            System.out.println("\n== Trước khi cập nhật ==");
            System.out.println(user1);

            // Cập nhật role và username
            user1.setUsername("updated_" + user1.getUsername());
            user1.setRole("admin");

            User updated = userService.update(user.getId(), user1);

            System.out.println("\n== Sau khi cập nhật ==");
            System.out.println(updated);
        } else {
            System.out.println("Không có người dùng để cập nhật.");
        }

        // In ra user vừa lưu
        System.out.println("User saved: " + user);

        // Đóng context
        context.close();
    }
}
