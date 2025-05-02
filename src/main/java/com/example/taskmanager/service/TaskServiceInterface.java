package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import java.util.List;
import java.util.UUID;

public interface TaskServiceInterface {
    List<Task> getTasks();
    void addTask(Task task);
    void deleteTaskById(UUID id);
    void updateTask(Task task);
    List<Task> searchTasks(String keyword);
    List<Task> sortTasks(List<Task> tasks, String sortBy);
}
