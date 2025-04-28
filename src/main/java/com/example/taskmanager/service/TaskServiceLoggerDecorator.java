package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;

import java.util.List;
import java.util.UUID;

public class TaskServiceLoggerDecorator implements TaskServiceInterface {

    private final TaskServiceInterface decoratedTaskService;

    public TaskServiceLoggerDecorator(TaskServiceInterface decoratedTaskService) {
        this.decoratedTaskService = decoratedTaskService;
    }

    @Override
    public List<Task> getTasks() {
        System.out.println("Вызван метод getTasks()");
        return decoratedTaskService.getTasks();
    }

    @Override
    public void addTask(Task task) {
        System.out.println("Вызван метод addTask(): " + task.getName());
        decoratedTaskService.addTask(task);
    }

    @Override
    public void deleteTaskById(UUID id) {
        System.out.println("Вызван метод deleteTaskById(): " + id);
        decoratedTaskService.deleteTaskById(id);
    }

    @Override
    public void updateTask(Task task) {
        System.out.println("Вызван метод updateTask(): " + task.getName());
        decoratedTaskService.updateTask(task);
    }

    @Override
    public List<Task> searchTasks(String keyword) {
        System.out.println("Вызван метод searchTasks(): " + keyword);
        return decoratedTaskService.searchTasks(keyword);
    }

    @Override
    public List<Task> sortTasks(List<Task> tasks, String sortBy) {
        System.out.println("Вызван метод sortTasks(): " + sortBy);
        return decoratedTaskService.sortTasks(tasks, sortBy);
    }
}
