package com.example.taskmanager.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import com.example.taskmanager.model.Project;
import com.example.taskmanager.model.Task;
import java.util.List;

public class TaskView extends VBox {

    private TableView<Project> projectTableView;
    private TableView<Task> taskTableView;
    private Button addProjectButton, addTaskButton, deleteProjectButton, deleteTaskButton;
    private TextField searchField;

    public TaskView() {
        // Инициализация элементов
        projectTableView = new TableView<>();
        taskTableView = new TableView<>();
        addProjectButton = new Button("Add Project");
        addTaskButton = new Button("Add Task");
        deleteProjectButton = new Button("Delete Project");
        deleteTaskButton = new Button("Delete Task");
        searchField = new TextField();

        // Добавление элементов в контейнер VBox
        getChildren().addAll(
                projectTableView,
                taskTableView,
                addProjectButton,
                addTaskButton,
                deleteProjectButton,
                deleteTaskButton,
                searchField
        );
    }

    // Устанавливаем действия для кнопок
    public void setAddProjectButtonAction(EventHandler<ActionEvent> handler) {
        addProjectButton.setOnAction(handler);
    }

    public void setAddTaskButtonAction(EventHandler<ActionEvent> handler) {
        addTaskButton.setOnAction(handler);
    }

    public void setDeleteProjectButtonAction(EventHandler<ActionEvent> handler) {
        deleteProjectButton.setOnAction(handler);
    }

    public void setDeleteTaskButtonAction(EventHandler<ActionEvent> handler) {
        deleteTaskButton.setOnAction(handler);
    }

    // Метод для отображения проектов
    public void displayProjects(List<Project> projects) {
        // Логика для отображения проектов
    }

    // Метод для отображения задач
    public void displayTasks(List<Task> tasks) {
        // Логика для отображения задач
    }

    // Дополнительные методы для обновления интерфейса
}
