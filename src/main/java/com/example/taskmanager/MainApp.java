package com.example.taskmanager;

import com.example.taskmanager.config.AppConfig;
import com.example.taskmanager.model.Category;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.CategoryService;
import com.example.taskmanager.service.TaskService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        TaskService taskService = context.getBean(TaskService.class);
        CategoryService categoryService = context.getBean(CategoryService.class);

        // Tạo dữ liệu mẫu cho Category
        Category school = new Category("Trường học");
        Category work = new Category("Công việc");
        Category volunteer = new Category("Tình nguyện");

        categoryService.createCategory(school);
        categoryService.createCategory(work);
        categoryService.createCategory(volunteer);

        // Tạo Task và gán Category
        Task task1 = new Task("Học Spring", "Đọc tài liệu Spring JPA", "NEW", LocalDate.now().plusDays(3));
        task1.setCategory(school);

        Task task2 = new Task("Viết báo cáo", "Tổng hợp kết quả project", "IN_PROGRESS", LocalDate.now().plusDays(5));
        task2.setCategory(work);

        Task task3 = new Task("Kiểm tra code", "Code review cho bạn cùng nhóm", "PENDING", LocalDate.now().plusDays(2));
        task3.setCategory(work);

        Task task4 = new Task("Tham gia chiến dịch", "Phát quà cho trẻ em vùng cao", "NEW", LocalDate.now().plusDays(7));
        task4.setCategory(volunteer);

        // Lưu các task vào cơ sở dữ liệu
        taskService.createTask(task1);
        taskService.createTask(task2);
        taskService.createTask(task3);
        taskService.createTask(task4);

        // Hiển thị danh sách task
        System.out.println("Danh sách Task:");
        List<Task> tasks = taskService.getAllTasks();
        tasks.forEach(System.out::println);

        // Hiển thị danh sách category
        System.out.println("\nDanh sách Category:");
        List<Category> categories = categoryService.getAllCategories();
        categories.forEach(System.out::println);

        context.close();
    }
}
