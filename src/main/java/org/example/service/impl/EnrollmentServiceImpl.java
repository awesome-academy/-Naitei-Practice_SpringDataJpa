package org.example.service.impl;

import org.example.entity.Enrollment;
import org.example.repository.EnrollmentRepository;
import org.example.service.EnrollmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public Enrollment saveEnrollment(Enrollment e) {
        return enrollmentRepository.save(e);
    }

    public Optional<Enrollment> getById(Long id) {
        return enrollmentRepository.findById(id);
    }

    public List<Enrollment> getAll() {
        return enrollmentRepository.findAll();
    }

    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }

    public List<Enrollment> getByCourse(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }
}
