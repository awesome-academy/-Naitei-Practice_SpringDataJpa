package com.example;

import com.example.config.AppConfig;
import com.example.entity.Course;
import com.example.entity.Student;
import com.example.repository.CourseRepository;
import com.example.repository.StudentRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashSet;
import java.util.Set;

public class AppMain {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        StudentRepository studentRepository = context.getBean(StudentRepository.class);
        CourseRepository courseRepository = context.getBean(CourseRepository.class);

        // ----- CREATE -----
        Course java = new Course(null, "Java Programming", "JAVA101");
        Course python = new Course(null, "Python Basics", "PY101");

        courseRepository.save(java);
        courseRepository.save(python);
        System.out.println("✅ Created Courses");

        Set<Course> courses = new HashSet<>();
        courses.add(java);
        courses.add(python);

        Student student = new Student(null, "John Doe", 20);
        student.setCourses(courses);

        // Thiết lập liên kết 2 chiều
        java.getStudents().add(student);
        python.getStudents().add(student);

        studentRepository.save(student);
        System.out.println("✅ Saved student with courses");

        Long studentId = student.getId(); // Lưu lại ID để đọc lại
        Long pythonCourseId = python.getId(); // Lưu lại ID của course để dùng khi xóa

        // ----- READ -----
        Student foundStudent = studentRepository.findByIdWithCourses(studentId).orElse(null);

        if (foundStudent != null) {
            System.out.println("📘 Found Student: " + foundStudent.getName());
            System.out.println("📚 Courses enrolled:");
            foundStudent.getCourses().forEach(c -> System.out.println(" - " + c.getTitle()));
        }

        // ----- UPDATE -----
        if (foundStudent != null) {
            foundStudent.setAge(21);
            studentRepository.save(foundStudent);
            System.out.println("🔁 Updated Student Age to: " + foundStudent.getAge());
        }

        // ----- DELETE COURSE FROM STUDENT -----
        if (foundStudent != null) {
            Course courseToRemove = courseRepository.findByIdWithStudents(pythonCourseId).orElse(null);

            if (courseToRemove != null) {
                foundStudent.getCourses().remove(courseToRemove);
                courseToRemove.getStudents().remove(foundStudent); // bidirectional
                studentRepository.save(foundStudent);
                System.out.println("❌ Removed course PY101 from student");
            }
        }

        // ----- DELETE STUDENT -----
        if (foundStudent != null) {
            studentRepository.delete(foundStudent);
            System.out.println("🗑️ Deleted Student: " + foundStudent.getName());
        }

        // ----- DELETE ALL COURSES -----
        courseRepository.deleteAll();
        System.out.println("🗑️ Deleted all courses");

        ((AnnotationConfigApplicationContext) context).close();
    }
}
