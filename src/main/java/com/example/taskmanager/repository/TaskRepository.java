package com.example.taskmanager.repository;

import com.example.taskmanager.model.Task;
import java.util.List;

public interface TaskRepository {
    void addTask(Task task);
    void removeTask(Task task);
    List<Task> getAllTasks();
}
