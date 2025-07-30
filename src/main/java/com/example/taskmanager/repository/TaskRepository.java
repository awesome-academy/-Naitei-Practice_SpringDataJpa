package com.example.taskmanager.repository;

import com.example.taskmanager.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    // CRUD mặc định: save(), findAll(), findById(), deleteById()
}
