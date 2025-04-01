package com.example.taskmanager.presenter;

import com.example.taskmanager.view.TaskView;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskManager;
import com.example.taskmanager.model.Project;
import javafx.event.ActionEvent;

public class TaskPresenter {
    private TaskView view;
    private TaskManager model;

    public TaskPresenter(TaskView view, TaskManager model) {
        this.view = view;
        this.model = model;

        // Настройка действий для кнопок с использованием лямбда-выражений
        view.setAddProjectButtonAction(event -> addProject());
        view.setAddTaskButtonAction(event -> addTask());
        view.setDeleteProjectButtonAction(event -> deleteProject());
        view.setDeleteTaskButtonAction(event -> deleteTask());
    }

    private void addProject() {
        Project newProject = new Project("New Project", "Description of project");
        model.addProject(newProject);
        view.displayProjects(model.getProjects());
    }

    private void addTask() {
        Task newTask = new Task("New Task", "Task description", "John Doe", "High");
        model.addTask(newTask);
        view.displayTasks(model.getTasks());
    }

    private void deleteProject() {
        // Логика удаления проекта
    }

    private void deleteTask() {
        // Логика удаления задачи
    }
}