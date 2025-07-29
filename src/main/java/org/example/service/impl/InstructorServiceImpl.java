package org.example.service.impl;

import org.example.entity.Instructor;
import org.example.repository.InstructorRepository;
import org.example.service.InstructorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;

    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public Instructor saveInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    public Optional<Instructor> getInstructorById(Long id) {
        return instructorRepository.findById(id);
    }

    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    public void deleteInstructorById(Long id) {
        instructorRepository.deleteById(id);
    }

    public List<Instructor> searchByName(String keyword) {
        return instructorRepository.findByNameContainingIgnoreCase(keyword);
    }
}
