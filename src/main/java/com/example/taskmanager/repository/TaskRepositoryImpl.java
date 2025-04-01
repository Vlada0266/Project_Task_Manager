package com.example.taskmanager.repository;

import com.example.taskmanager.model.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {
    private final List<Task> tasks = new ArrayList<>();

    @Override
    public void addTask(Task task) { tasks.add(task); }

    @Override
    public void removeTask(Task task) { tasks.remove(task); }

    @Override
    public List<Task> getAllTasks() { return tasks; }
}