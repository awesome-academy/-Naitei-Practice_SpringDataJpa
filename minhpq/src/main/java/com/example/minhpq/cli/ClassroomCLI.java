package com.example.minhpq.cli;

import com.example.minhpq.model.Classroom;
import com.example.minhpq.service.ClassroomService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ClassroomCLI {
    private final ClassroomService classroomService;

    public ClassroomCLI(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== CLASSROOM MANAGEMENT MENU =====");
            System.out.println("1. Add new classroom");
            System.out.println("2. View all classrooms");
            System.out.println("3. View classroom by ID");
            System.out.println("4. Delete classroom by ID");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter location: ");
                    String location = scanner.nextLine();
                    System.out.print("Enter capacity: ");
                    int capacity = Integer.parseInt(scanner.nextLine());
                    Classroom newClassroom = new Classroom(name, location, capacity);
                    classroomService.createClassroom(newClassroom);
                    System.out.println("Classroom added!");
                    break;
                case "2":
                    List<Classroom> classrooms = classroomService.getAllClassrooms();
                    classrooms.forEach(classroom -> System.out.println(classroomInfo(classroom)));
                    break;
                case "3":
                    System.out.print("Enter ID: ");
                    Long id = Long.parseLong(scanner.nextLine());
                    Optional<Classroom> classroom = classroomService.getClassroomById(id);
                    classroom.ifPresentOrElse(
                            c -> System.out.println(classroomInfo(c)),
                            () -> System.out.println("Classroom not found!")
                    );
                    break;
                case "4":
                    System.out.print("Enter ID to delete: ");
                    Long delId = Long.parseLong(scanner.nextLine());
                    Optional<Classroom> classroomToDelete = classroomService.getClassroomById(delId);
                    if (classroomToDelete.isPresent()) {
                        classroomService.deleteClassroom(delId);
                        System.out.println("Classroom deleted successfully.");
                    } else {
                        System.out.println("Classroom with ID " + delId + " does not exist.");
                    }
                    break;
                case "0":
                    System.out.println("Bye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private String classroomInfo(Classroom c) {
        return String.format(" ID: %d | Name: %s | Location: %s | Capacity: %d",
                c.getId(), c.getName(), c.getLocation(), c.getCapacity());
    }
}
