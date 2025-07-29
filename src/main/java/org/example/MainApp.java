package org.example;

import org.example.config.AppConfig;
import org.example.entity.Course;
import org.example.entity.Instructor;
import org.example.service.CourseService;
import org.example.service.EnrollmentService;
import org.example.service.InstructorService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        CourseService courseService = context.getBean(CourseService.class);
        EnrollmentService enrollmentService = context.getBean(EnrollmentService.class);
        InstructorService instructorService = context.getBean(InstructorService.class);

        // lay random 7 id gv
        long randomInstructorId = 1 + (long) (Math.random() * 7);
        Instructor instructor = courseService.getInstructorById(randomInstructorId);

        // lay random id 1->10 khoa hoc
        long randomCourseId = 1 + (long) (Math.random() * 10);
        Course course = courseService.getCourseById(randomCourseId);

        // tao moi course
//        Course newCourse = new Course();
//        newCourse.setTitle("Spring Core");
//        newCourse.setDescription("Learn Spring Core with trainer xing gai VTTV");
//        newCourse.setInstructor(instructor);

        // luu course
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
//            Long instructorId = (c.getInstructor() != null) ? c.getInstructor().getId() : null;
//            System.out.println("- " + c.getId() + ": " + c.getTitle() + " - Instructor ID: " + instructorId);
//        }

//        tim khoa hoc theo title
//        List<Course> result = courseService.searchCoursesByTitle("Java");
//        System.out.println("ket qua voi keyword 'Java':");
//        for (Course c : result) {
//            System.out.println(">> " + c.getTitle());
//        }

        // tao moi 1 enrollment
//        Enrollment newEnrollment = new Enrollment();
//        newEnrollment.setStudentName("Tran Minh T");
//        newEnrollment.setEnrollmentDate(LocalDate.now());
//        newEnrollment.setCourse(course);

//        Enrollment saved = enrollmentService.saveEnrollment(newEnrollment);
//        System.out.println("Da tao enrollment voi ID la: " + saved.getId());

        // tim enroll theo ID
//        Enrollment found = enrollmentService.getById(saved.getId())
//                .orElse(null);
//
//        if (found != null) {
//            System.out.println("Tim duoc: " + found.getStudentName());
//        }
//
//        // lay ra tat ca svien
//        List<Enrollment> all = enrollmentService.getAll();
//        System.out.println("Tat ca enrollment: ");
//        for (Enrollment e : all) {
//            String courseTitle = e.getCourse() != null ? e.getCourse().getTitle() : "N/A";
//            System.out.println(" - " + e.getId() + ": " + e.getStudentName() + " -> " + courseTitle);
//        }
//
//        // tim svien theo course id
//        System.out.println("Tim enrollment theo courseId = " + course.getId());
//        List<Enrollment> byCourse = enrollmentService.getByCourse(course.getId());
//        for (Enrollment e : byCourse) {
//            System.out.println(" >> " + e.getStudentName() + " (Enroll ID: " + e.getId() + ")");
//        }
//
//        // xoa enrollment
//        enrollmentService.deleteEnrollment(saved.getId());
//        System.out.println("Da xoa enrollment ten la: " + saved.getStudentName());


        // tao moi 1 gv
        Instructor newInstructor = new Instructor();
        newInstructor.setName("Hoang Van H");

        Instructor saved = instructorService.saveInstructor(newInstructor);
        System.out.println("Da tao moi instructor id: " + saved.getId());

        // tim gv theo id
        instructorService.getInstructorById(saved.getId()).ifPresentOrElse(
                i -> System.out.println("Tim duoc: " + i.getName()),
                () -> System.out.println("Ko tim duoc")
        );

        // Lay tat ca gv hien co
        List<Instructor> all = instructorService.getAllInstructors();
        System.out.println("Danh sach Instructor:");
        for (Instructor i : all) {
            System.out.println(" - " + i.getId() + ": " + i.getName());
        }

        // Tim theo ten
        List<Instructor> result = instructorService.searchByName("pham");
        System.out.println("ket qua voi 'pham':");
        for (Instructor i : result) {
            System.out.println(" >> " + i.getName());
        }

        // Xoa 1 gv
        instructorService.deleteInstructorById(saved.getId());
        System.out.println("Da xoa instuctor = " + saved.getName());

        context.close();
    }
}
