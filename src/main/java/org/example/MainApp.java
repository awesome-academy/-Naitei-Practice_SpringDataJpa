package org.example;

import org.example.config.AppConfig;
import org.example.entity.Course;
import org.example.service.CourseService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        CourseService courseService = context.getBean(CourseService.class);

        // tao moi
//        Course newCourse = new Course();
//        newCourse.setTitle("Spring Core");
//        newCourse.setDescription("Learn Spring Core with trainer xing gai VTTV");

        // luu
//        Course saved = courseService.saveCourse(newCourse);
//        System.out.println("Da tao moi khoa hoc: " + saved.getId());

        // tim khoa hoc theo id
//        Course found = courseService.getCourseById(saved.getId());
//        System.out.println("Khoa hoc tim duoc: " + found.getTitle());

        // xoa khoa hoc
//        courseService.deleteCourseById(saved.getId());
//        System.out.println("da xoa khoa hoc voi id: " + saved.getId());

        // Lay ra tat ca khoa hoc
//        List<Course> allCousre = courseService.getAllCourses();
//        System.out.println("Danh sach khoa hoc: ");
//        for (Course c : allCousre) {
//            System.out.println("- " + c.getId() + ": " + c.getTitle());
//        }

        //tim khoa hoc theo title
        List<Course> result = courseService.searchCoursesByTitle("Java");
        System.out.println("ket qua voi keyword 'Java':");
        for (Course c : result) {
            System.out.println(">> " + c.getTitle());
        }

        context.close();
    }
}
