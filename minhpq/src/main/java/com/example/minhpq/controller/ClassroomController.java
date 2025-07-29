package com.example.minhpq.controller;

import com.example.minhpq.model.Classroom;
import com.example.minhpq.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @GetMapping
    public List<Classroom> getAllClassrooms() {
        return classroomService.getAllClassrooms();
    }

    @GetMapping("/{id}")
    public Optional<Classroom> getClassroomById(@PathVariable Long id) {
        return classroomService.getClassroomById(id);
    }

    @PostMapping
    public Classroom createClassroom(@RequestBody Classroom classroom) {
        return classroomService.createClassroom(classroom);
    }

    @DeleteMapping("/{id}")
    public void deleteClassroom(@PathVariable Long id) {
        classroomService.deleteClassroom(id);
    }
}
