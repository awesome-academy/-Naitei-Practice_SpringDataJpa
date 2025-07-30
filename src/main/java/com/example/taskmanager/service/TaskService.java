package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    void createTask(Task task);
    List<Task> getAllTasks();
    Optional<Task> getTaskById(Long id);
    void updateTask(Task task);
    void deleteTask(Long id);
}
