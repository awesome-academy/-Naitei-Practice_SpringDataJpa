package com.example;

import com.example.entity.Student;
import com.example.config.AppConfig;
import com.example.entity.Course;
import com.example.repository.StudentRepository;
import com.example.repository.CourseRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.ApplicationContext;

public class AppMain {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Initialize repositories
        StudentRepository studentRepository = context.getBean(StudentRepository.class);
        CourseRepository courseRepository = context.getBean(CourseRepository.class);

        // CRUD operations for Student
        // Create
        Student student = new Student(1L, "John Doe", 20);
        studentRepository.save(student);
        System.out.println("Created Student: " + student.getName());

        // Read
        Student foundStudent = studentRepository.findById(1L).orElse(null);
        System.out.println("Found Student: " + (foundStudent != null ? foundStudent.getName() : "Not found"));

        // Update
        if (foundStudent != null) {
            foundStudent.setAge(21);
            studentRepository.save(foundStudent);
            System.out.println("Updated Student Age: " + foundStudent.getAge());
        }

        // Delete
        if (foundStudent != null) {
            studentRepository.delete(foundStudent);
            System.out.println("Deleted Student with ID: " + foundStudent.getId());
        }

        // CRUD operations for Course
        // Create
        Course course = new Course(1L, "Java Programming", "JAVA101");
        courseRepository.save(course);
        System.out.println("Created Course: " + course.getTitle());

        // Read
        Course foundCourse = courseRepository.findById(1L).orElse(null);
        System.out.println("Found Course: " + (foundCourse != null ? foundCourse.getTitle() : "Not found"));

        // Update
        if (foundCourse != null) {
            foundCourse.setCode("JAVA102");
            courseRepository.save(foundCourse);
            System.out.println("Updated Course Code: " + foundCourse.getCode());
        }

        // Delete
        if (foundCourse != null) {
            courseRepository.delete(foundCourse);
            System.out.println("Deleted Course with ID: " + foundCourse.getId());
        }

        // Close context
        ((AnnotationConfigApplicationContext) context).close();
    }
}