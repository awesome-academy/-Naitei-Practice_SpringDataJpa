package org.example.service;

import org.example.entity.Course;
import org.example.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // Cap nhat db
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    // Check course
    public Course getCourseById(Long id) {
        Optional<Course> optional = courseRepository.findById(id);
        return optional.orElse(null);
    }

    // lay toan bo coure
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // xoa course theo Id
    public void deleteCourseById(Long id) {
        courseRepository.deleteById(id);
    }

    // tim course theo keyword title
    public List<Course> searchCoursesByTitle(String keyword) {
        return courseRepository.searchByTitleKeyword(keyword);
    }

}
