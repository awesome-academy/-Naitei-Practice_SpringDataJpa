package org.example.service.impl;

import org.example.entity.Course;
import org.example.entity.Instructor;
import org.example.repository.CourseRepository;
import org.example.repository.InstructorRepository;
import org.example.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    public CourseServiceImpl(CourseRepository courseRepository, InstructorRepository instructorRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

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

    public Instructor getInstructorById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor ko ton tai"));
    }
}
