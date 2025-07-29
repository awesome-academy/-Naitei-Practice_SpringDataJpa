package org.example.service;

import org.example.config.AppConfig;
import org.example.entity.Course;
import org.example.entity.Instructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CourseServiceTest {

    private CourseService courseService;

    @BeforeAll
    void setUp() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        courseService = context.getBean(CourseService.class);
    }

    @Test
    void testCreateAndReadCourse() {
        Instructor instructor = courseService.getInstructorById(1L);
        Course course = new Course("JUnit Testing", "Test with JUnit", instructor);
        course.setInstructor(instructor);

        Course saved = courseService.saveCourse(course);
        assertNotNull(saved.getId());

        Course found = courseService.getCourseById(saved.getId());
        assertEquals("JUnit Testing", found.getTitle());

        // clean up
        courseService.deleteCourseById(saved.getId());
    }

    @Test
    void testSearchCourse() {
        List<Course> results = courseService.searchCoursesByTitle("Spring");
        assertNotNull(results);
        assertTrue(results.size() >= 0);
    }

    @Test
    void testGetAllCourses() {
        List<Course> allCourses = courseService.getAllCourses();
        assertNotNull(allCourses);
    }

    @Test
    void testGetInstructorById() {
        Instructor instructor = courseService.getInstructorById(1L);
        assertNotNull(instructor);
        assertEquals(1L, instructor.getId());
    }
}
