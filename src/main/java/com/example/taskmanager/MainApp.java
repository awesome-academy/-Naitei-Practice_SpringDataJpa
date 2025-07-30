package com.example.taskmanager;

import com.example.taskmanager.config.AppConfig;
import com.example.taskmanager.model.Category;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.CategoryService;
import com.example.taskmanager.service.TaskService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        TaskService taskService = context.getBean(TaskService.class);
        CategoryService categoryService = context.getBean(CategoryService.class);

        // Tạo dữ liệu mẫu nếu chưa có
        if (categoryService.getAllCategories().isEmpty()) {
            categoryService.createCategory(new Category("Trường học"));
            categoryService.createCategory(new Category("Công việc"));
            categoryService.createCategory(new Category("Tình nguyện"));
        }

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n===== QUẢN LÝ TASK =====");
            System.out.println("1. Xem tất cả Task");
            System.out.println("2. Tạo Task mới");
            System.out.println("3. Xoá Task theo ID");
            System.out.println("4. Xem danh sách Category");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    List<Task> tasks = taskService.getAllTasks();
                    if (tasks.isEmpty()) {
                        System.out.println("Chưa có task nào.");
                    } else {
                        tasks.forEach(System.out::println);
                    }
                    break;

                case "2":
                    System.out.print("Tiêu đề: ");
                    String title = scanner.nextLine();

                    System.out.print("Mô tả: ");
                    String description = scanner.nextLine();

                    System.out.print("Trạng thái (NEW, IN_PROGRESS, PENDING): ");
                    String status = scanner.nextLine();

                    System.out.print("Hạn chót (YYYY-MM-DD): ");
                    String dueDateStr = scanner.nextLine();
                    LocalDate dueDate = LocalDate.parse(dueDateStr);

                    System.out.println("Chọn Category:");
                    List<Category> categories = categoryService.getAllCategories();
                    for (int i = 0; i < categories.size(); i++) {
                        System.out.println((i + 1) + ". " + categories.get(i).getName());
                    }
                    System.out.print("Số thứ tự: ");
                    int catIndex = Integer.parseInt(scanner.nextLine()) - 1;
                    Category selectedCategory = categories.get(catIndex);

                    Task newTask = new Task(title, description, status, dueDate);
                    newTask.setCategory(selectedCategory);
                    taskService.createTask(newTask);

                    System.out.println("Đã tạo Task.");
                    break;

                case "3":
                    System.out.print("Nhập ID task muốn xoá: ");
                    Long idToDelete = Long.parseLong(scanner.nextLine());
                    taskService.deleteTask(idToDelete);
                    System.out.println("Đã xoá Task (nếu tồn tại).");
                    break;

                case "4":
                    List<Category> allCategories = categoryService.getAllCategories();
                    allCategories.forEach(System.out::println);
                    break;

                case "0":
                    running = false;
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }

        context.close();
        System.out.println("Chương trình đã thoát.");
    }
}
