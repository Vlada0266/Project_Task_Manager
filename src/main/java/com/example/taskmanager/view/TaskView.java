package com.example.taskmanager.view;

import com.example.taskmanager.model.Task;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class TaskView extends BorderPane {
    private Button addTaskButton;
    private Button deleteTaskButton;
    private TextField taskInputField;
    private TextField searchField;
    private Button clearSearchButton;
    private ListView<Task> taskListView;
    private Button sortButton;

    public TaskView(Stage primaryStage) {
        initializeUI();
        primaryStage.setScene(new Scene(this, 800, 600));
        primaryStage.setTitle("Task Manager");
        primaryStage.show();
    }

    private void initializeUI() {
        // Поле ввода для задач
        taskInputField = new TextField();
        taskInputField.setPromptText("Введите задачу...");

        // Поле поиска
        searchField = new TextField();
        searchField.setPromptText("Поиск задачи...");

        // Кнопки
        addTaskButton = new Button("Добавить");
        deleteTaskButton = new Button("Удалить");
        clearSearchButton = new Button("Очистить поиск");
        sortButton = new Button("Сортировать");

        // Список задач
        taskListView = new ListView<>();

        // Верхняя панель (добавление задач)
        HBox topPanel = new HBox(10, taskInputField, addTaskButton, deleteTaskButton, sortButton);
        topPanel.setPadding(new Insets(10));

        // Панель поиска
        HBox searchPanel = new HBox(10, searchField, clearSearchButton);
        searchPanel.setPadding(new Insets(10));

        // Размещение элементов на сцене
        setTop(topPanel);
        setCenter(taskListView);
        setBottom(searchPanel);
    }

    // Геттеры для элементов
    public Button getAddTaskButton() { return addTaskButton; }
    public Button getDeleteTaskButton() { return deleteTaskButton; }
    public TextField getTaskInputField() { return taskInputField; }
    public ListView<Task> getTaskListView() { return taskListView; }
    public TextField getSearchField() { return searchField; }
    public Button getClearSearchButton() { return clearSearchButton; }
    public Button getSortButton() { return sortButton; }
}
