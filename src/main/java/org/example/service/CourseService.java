package org.example.service;

import org.example.entity.Course;
import org.example.entity.Instructor;

import java.util.List;

public interface CourseService {

    Course saveCourse(Course course);

    Course getCourseById(Long id);

    List<Course> getAllCourses();

    void deleteCourseById(Long id);

    List<Course> searchCoursesByTitle(String keyword);

    Instructor getInstructorById(Long id);
}
