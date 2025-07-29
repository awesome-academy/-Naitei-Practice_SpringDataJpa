package org.example.service;

import org.example.entity.Enrollment;

import java.util.List;
import java.util.Optional;

public interface EnrollmentService {

    Enrollment saveEnrollment(Enrollment e);

    Optional<Enrollment> getById(Long id);

    List<Enrollment> getAll();

    void deleteEnrollment(Long id);

    List<Enrollment> getByCourse(Long courseId);
}
