package org.example.service;

import org.example.entity.Instructor;

import java.util.List;
import java.util.Optional;

public interface InstructorService {

    Instructor saveInstructor(Instructor instructor);

    Optional<Instructor> getInstructorById(Long id);

    List<Instructor> getAllInstructors();

    void deleteInstructorById(Long id);

    List<Instructor> searchByName(String keyword);
}
