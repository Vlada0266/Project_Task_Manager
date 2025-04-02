package com.example.taskmanager.service;

import com.example.taskmanager.db.TaskDAO;
import com.example.taskmanager.model.Task;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class TaskService {

    private final TaskDAO taskDAO;

    public List<Task> getTasks() {
        return taskDAO.getTasks();
    }

    public void addTask(Task task) {
        taskDAO.addTask(task);
    }

    public void deleteTaskById(UUID id) {
        taskDAO.deleteTaskById(id);
    }
}
