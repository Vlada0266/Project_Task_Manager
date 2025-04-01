package com.example.taskmanager.model;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Project> projects;
    private List<Task> tasks;

    public TaskManager() {
        this.projects = new ArrayList<>();
        this.tasks = new ArrayList<>();
    }

    // Метод для добавления проекта
    public void addProject(Project project) {
        projects.add(project);
    }

    // Метод для добавления задачи
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Получить список проектов
    public List<Project> getProjects() {
        return projects;
    }

    // Получить список задач
    public List<Task> getTasks() {
        return tasks;
    }
}
