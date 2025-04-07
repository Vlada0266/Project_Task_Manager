package com.example.taskmanager.view;

import com.example.taskmanager.model.Task;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class TaskView extends BorderPane {
    // Основные UI-элементы
    private Button addTaskButton;
    private Button deleteTaskButton;
    private Button editTaskButton;
    private TextField taskInputField;
    private TextArea descriptionField;
    private TextField searchField;
    private Button clearSearchButton;
    private ListView<Task> taskListView;
    private Button sortButton;
    private ComboBox<String> sortComboBox;
    private DatePicker deadlinePicker;
    private ComboBox<String> priorityComboBox;
    private ComboBox<String> statusComboBox;

    public TaskView(Stage primaryStage) {
        initializeUI();
        primaryStage.setScene(new Scene(this, 800, 600));
        primaryStage.setTitle("Task Manager");
        primaryStage.show();
    }

    private void initializeUI() {
        // Поле для ввода задачи
        taskInputField = new TextField();
        taskInputField.setPromptText("Введите задачу...");

        // Поле для ввода описания
        descriptionField = new TextArea();
        descriptionField.setPromptText("Введите описание задачи...");
        descriptionField.setPrefRowCount(1);

        // Поле для поиска задач
        searchField = new TextField();
        searchField.setPromptText("Поиск задачи...");

        // Кнопки
        addTaskButton = new Button("Добавить");
        deleteTaskButton = new Button("Удалить");
        editTaskButton = new Button("Редактировать");
        clearSearchButton = new Button("Очистить поиск");

        // Список задач
        taskListView = new ListView<>();

        // Комбобокс для сортировки
        sortComboBox = new ComboBox<>();
        sortComboBox.getItems().addAll("Приоритет", "Статус", "Дедлайн");
        sortComboBox.setValue("Приоритет");

        sortButton = new Button("Сортировать");

        // Элементы для ввода дополнительных параметров задачи
        deadlinePicker = new DatePicker();
        deadlinePicker.setPromptText("Выберите дедлайн");

        priorityComboBox = new ComboBox<>();
        priorityComboBox.getItems().add("Выберите приоритет");
        priorityComboBox.getItems().addAll("Наивысший", "Важный", "Низкий");
        priorityComboBox.setValue("Выберите приоритет");

        statusComboBox = new ComboBox<>();
        statusComboBox.getItems().add("Выберите статус");
        statusComboBox.getItems().addAll("Начата", "В_работе", "Выполнена", "Отменена");
        statusComboBox.setValue("Выберите статус");

        // Сетка с полями ввода
        GridPane fieldsPanel = new GridPane();
        fieldsPanel.setHgap(10);
        fieldsPanel.setVgap(10);
        fieldsPanel.setPadding(new Insets(10));

        fieldsPanel.add(new Label("Имя задачи:"), 0, 0);
        fieldsPanel.add(taskInputField, 1, 0);

        fieldsPanel.add(new Label("Описание:"), 0, 1);
        fieldsPanel.add(descriptionField, 1, 1);

        fieldsPanel.add(new Label("Дедлайн:"), 0, 2);
        fieldsPanel.add(deadlinePicker, 1, 2);

        fieldsPanel.add(new Label("Приоритет:"), 0, 3);
        fieldsPanel.add(priorityComboBox, 1, 3);

        fieldsPanel.add(new Label("Статус:"), 0, 4);
        fieldsPanel.add(statusComboBox, 1, 4);

        // Панель с кнопками
        HBox buttonsPanel = new HBox(10, addTaskButton, deleteTaskButton, editTaskButton, sortButton, sortComboBox);
        buttonsPanel.setPadding(new Insets(10));
        buttonsPanel.setSpacing(10);
        buttonsPanel.setAlignment(Pos.TOP_CENTER);

        // Панель поиска
        HBox searchPanel = new HBox(10, searchField, clearSearchButton);
        searchPanel.setPadding(new Insets(10));

        // Основная компоновка
        VBox topPanel = new VBox(10, fieldsPanel, buttonsPanel);
        topPanel.setPadding(new Insets(10));

        setTop(topPanel);
        setCenter(taskListView);
        setBottom(searchPanel);

        // Кастомизация отображения задач в списке
        taskListView.setCellFactory(param -> new ListCell<Task>() {
            @Override
            protected void updateItem(Task item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Label nameLabel = new Label(item.getName());
                    nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

                    Label descriptionLabel = new Label("Описание: " + item.getDescription());
                    descriptionLabel.setStyle("-fx-text-fill: #555555;");

                    Label statusLabel = new Label("Статус: " + item.getStatus());
                    statusLabel.setStyle("-fx-font-weight: bold;" + getStatusColorStyle(item.getStatus()));

                    Label priorityLabel = new Label("Приоритет: " + item.getPriority());
                    priorityLabel.setStyle("-fx-font-weight: bold;" + getPriorityColorStyle(item.getPriority()));

                    String deadlineText = item.getDeadline() != null ? item.getDeadline().toString() : "Без дедлайна";
                    Label deadlineLabel = new Label("Дедлайн: " + deadlineText);
                    deadlineLabel.setStyle("-fx-text-fill: #444444;");

                    HBox metaBox = new HBox(10, statusLabel, priorityLabel, deadlineLabel);
                    metaBox.setSpacing(15);
                    metaBox.setStyle("-fx-padding: 4 0 0 0;");

                    VBox vbox = new VBox(nameLabel, descriptionLabel, metaBox);
                    vbox.setSpacing(3);
                    vbox.setStyle("-fx-padding: 10; -fx-background-color: #f0f4f8; -fx-border-color: #d0d7de; -fx-border-radius: 6; -fx-background-radius: 6;");

                    setGraphic(vbox);
                }
            }

            // Цвета текста в зависимости от приоритета
            private String getPriorityColorStyle(Task.TaskPriority priority) {
                return switch (priority) {
                    case Наивысший -> "-fx-text-fill: #d32f2f;";
                    case Важный -> "-fx-text-fill: #f57c00;";
                    case Низкий -> "-fx-text-fill: #388e3c;";
                };
            }

            // Цвета текста в зависимости от статуса
            private String getStatusColorStyle(Task.TaskStatus status) {
                return switch (status) {
                    case Начата -> "-fx-text-fill: #1976d2;";
                    case В_работе -> "-fx-text-fill: #0288d1;";
                    case Выполнена -> "-fx-text-fill: #388e3c;";
                    case Отменена -> "-fx-text-fill: #9e9e9e;";
                };
            }
        });

        // Очистка выделения при клике вне списка
        Platform.runLater(() -> {
            this.getScene().getRoot().setOnMouseClicked(event -> {
                if (!taskListView.isHover()) {
                    taskListView.getSelectionModel().clearSelection();
                }
            });
        });
    }

    // Геттеры для доступа к UI-элементам
    public Button getAddTaskButton() { return addTaskButton; }
    public Button getDeleteTaskButton() { return deleteTaskButton; }
    public Button getClearSearchButton() { return clearSearchButton; }
    public Button getSortButton() { return sortButton; }
    public Button getEditTaskButton() { return editTaskButton; }
    public TextField getTaskInputField() { return taskInputField; }
    public ListView<Task> getTaskListView() { return taskListView; }
    public TextField getSearchField() { return searchField; }
    public ComboBox<String> getSortComboBox() { return sortComboBox; }
    public DatePicker getDeadlinePicker() { return deadlinePicker; }
    public ComboBox<String> getStatusComboBox() { return statusComboBox; }
    public ComboBox<String> getPriorityComboBox() { return priorityComboBox; }
    public TextArea getDescriptionField() { return descriptionField; }

    // Диалог редактирования задачи
    public Task showEditDialog(Task task) {
        TextInputDialog nameDialog = new TextInputDialog(task.getName());
        nameDialog.setTitle("Редактирование задачи");
        nameDialog.setHeaderText("Введите новое название задачи:");
        nameDialog.setContentText("Название:");

        TextInputDialog descriptionDialog = new TextInputDialog(task.getDescription());
        descriptionDialog.setTitle("Редактирование задачи");
        descriptionDialog.setHeaderText("Введите новое описание задачи:");
        descriptionDialog.setContentText("Описание:");

        ComboBox<String> priorityComboBox = new ComboBox<>();
        priorityComboBox.getItems().addAll("Наивысший", "Важный", "Низкий");
        priorityComboBox.setValue(task.getPriority().name());

        ComboBox<String> statusComboBox = new ComboBox<>();
        statusComboBox.getItems().addAll("Начата", "В_работе", "Выполнена", "Отменена");
        statusComboBox.setValue(task.getStatus().name());

        DatePicker deadlinePicker = new DatePicker();
        deadlinePicker.setValue(task.getDeadline());

        Button okButton = new Button("ОК");
        Button cancelButton = new Button("Отмена");

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Редактирование задачи");

        okButton.setOnAction(e -> dialogStage.close());
        cancelButton.setOnAction(e -> dialogStage.close());

        HBox buttonBox = new HBox(10, okButton, cancelButton);
        buttonBox.setAlignment(Pos.TOP_RIGHT);

        VBox vbox = new VBox(10,
                nameDialog.getEditor(),
                descriptionDialog.getEditor(),
                priorityComboBox,
                statusComboBox,
                deadlinePicker,
                buttonBox);
        vbox.setAlignment(Pos.TOP_LEFT);
        dialogStage.setScene(new Scene(vbox, 300, 250));
        dialogStage.showAndWait();

        return new Task(
                task.getId(),
                task.getProjectId(),
                nameDialog.getEditor().getText(),
                descriptionDialog.getEditor().getText(),
                Task.TaskStatus.valueOf(statusComboBox.getValue()),
                Task.TaskPriority.valueOf(priorityComboBox.getValue()),
                deadlinePicker.getValue()
        );
    }

    // Очистка всех полей формы
    public void clearTaskInputFields() {
        taskInputField.clear();
        descriptionField.clear();
        deadlinePicker.setValue(null);
        priorityComboBox.setValue("Выберите приоритет");
        statusComboBox.setValue("Выберите статус");
    }
}
