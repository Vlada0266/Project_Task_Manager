package com.example.taskmanager.view;

import com.example.taskmanager.model.Task;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;


public class TaskView extends BorderPane {
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
    private ComboBox<String> priorityComboBox;  // Добавляем ComboBox для приоритета
    private ComboBox<String> statusComboBox;    // Добавляем ComboBox для статуса

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

        descriptionField = new TextArea();
        descriptionField.setPromptText("Введите описание задачи...");
        descriptionField.setPrefRowCount(1); // Несколько строк для удобства

        // Поле поиска
        searchField = new TextField();
        searchField.setPromptText("Поиск задачи...");

        // Кнопки
        addTaskButton = new Button("Добавить");
        deleteTaskButton = new Button("Удалить");
        editTaskButton = new Button("Редактировать");
        clearSearchButton = new Button("Очистить поиск");

        // Список задач
        taskListView = new ListView<>();

        // Выпадающий список для сортировки
        sortComboBox = new ComboBox<>();
        sortComboBox.getItems().addAll("Приоритет", "Статус", "Дедлайн");
        sortComboBox.setValue("Приоритет"); // Значение по умолчанию

        // Кнопка сортировки
        sortButton = new Button("Сортировать");

        // Верхняя панель (добавление задач)
        deadlinePicker = new DatePicker();
        deadlinePicker.setPromptText("Выберите дедлайн");

        // Выпадающий список для выбора приоритета
        priorityComboBox = new ComboBox<>();
        priorityComboBox.getItems().add("Выберите приоритет");
        priorityComboBox.getItems().addAll("Наивысший", "Важный", "Низкий");
        priorityComboBox.setValue("Выберите приоритет"); // Значение по умолчанию

        // Список задач
        taskListView = new ListView<>();

        // Выпадающий список для выбора статуса задачи
        statusComboBox = new ComboBox<>();
        statusComboBox.getItems().add("Выберите статус");
        statusComboBox.getItems().addAll("Начата", "В_работе", "Выполнена", "Отменена");
        statusComboBox.setValue("Выберите статус"); // Значение по умолчанию

        GridPane fieldsPanel = new GridPane();
        fieldsPanel.setHgap(10); // Расстояние между столбцами
        fieldsPanel.setVgap(10); // Расстояние между строками
        fieldsPanel.setPadding(new Insets(10));

        // Размещение элементов в сетке
        fieldsPanel.add(new Label("Имя задачи:"), 0, 0); // Этикетка для имени
        fieldsPanel.add(taskInputField, 1, 0); // Поле ввода для имени задачи

        fieldsPanel.add(new Label("Описание:"), 0, 1); // Этикетка для описания
        fieldsPanel.add(descriptionField, 1, 1); // Поле ввода для описания

        fieldsPanel.add(new Label("Дедлайн:"), 0, 2); // Этикетка для дедлайна
        fieldsPanel.add(deadlinePicker, 1, 2); // Поле выбора дедлайна

        fieldsPanel.add(new Label("Приоритет:"), 0, 3); // Этикетка для приоритета
        fieldsPanel.add(priorityComboBox, 1, 3); // Выпадающий список для приоритета

        fieldsPanel.add(new Label("Статус:"), 0, 4); // Этикетка для статуса
        fieldsPanel.add(statusComboBox, 1, 4); // Выпадающий список для статуса

        // Панель с кнопками для добавления, удаления, редактирования, сортировки
        HBox buttonsPanel = new HBox(10, addTaskButton, deleteTaskButton, editTaskButton, sortButton, sortComboBox);
        buttonsPanel.setPadding(new Insets(10));
        buttonsPanel.setSpacing(10); // Расстояние между кнопками
        buttonsPanel.setAlignment(Pos.TOP_CENTER); // Выравнивание по левому краю

        // Панель поиска
        HBox searchPanel = new HBox(10, searchField, clearSearchButton);
        searchPanel.setPadding(new Insets(10));

        // Размещение элементов на сцене
        VBox topPanel = new VBox(10, fieldsPanel, buttonsPanel); // Создаем VBox для полей и кнопок
        topPanel.setPadding(new Insets(10));

        setTop(topPanel); // Размещаем верхнюю панель
        setCenter(taskListView); // Размещаем список задач в центре
        setBottom(searchPanel); // Размещаем панель поиска внизу

        taskListView.setCellFactory(param -> new ListCell<Task>() {
            @Override
            protected void updateItem(Task item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    // Форматируем вывод задачи с дедлайном
                    String deadlineText = item.getDeadline() != null ? item.getDeadline().toString() : "Без дедлайна";
                    setText("Задача: " + item.getName() + " | Описание: " + item.getDescription() + " | Статус: " + item.getStatus() + " | Приоритет: " + item.getPriority() + " | Дедлайн: " + deadlineText);
                }
            }
        });
    }


    // Геттеры для элементов
    public Button getAddTaskButton() {
        return addTaskButton;
    }

    public Button getDeleteTaskButton() {
        return deleteTaskButton;
    }

    public Button getClearSearchButton() {
        return clearSearchButton;
    }

    public Button getSortButton() {
        return sortButton;
    }

    public Button getEditTaskButton() {
        return editTaskButton;
    }

    public TextField getTaskInputField() {
        return taskInputField;
    }

    public ListView<Task> getTaskListView() {
        return taskListView;
    }

    public TextField getSearchField() {
        return searchField;
    }

    public ComboBox<String> getSortComboBox() {
        return sortComboBox;
    }

    public DatePicker getDeadlinePicker() {
        return deadlinePicker;
    }

    public ComboBox<String> getStatusComboBox() {
        return statusComboBox;
    }

    public ComboBox<String> getPriorityComboBox() {
        return priorityComboBox;
    }

    public TextArea getDescriptionField() {
        return descriptionField;
    }


    public Task showEditDialog(Task task) {
        // Диалог для редактирования задачи
        TextInputDialog nameDialog = new TextInputDialog(task.getName());
        nameDialog.setTitle("Редактирование задачи");
        nameDialog.setHeaderText("Введите новое название задачи:");
        nameDialog.setContentText("Название:");

        TextInputDialog descriptionDialog = new TextInputDialog(task.getDescription());
        descriptionDialog.setTitle("Редактирование задачи");
        descriptionDialog.setHeaderText("Введите новое описание задачи:");
        descriptionDialog.setContentText("Описание:");

        // Список для выбора приоритета
        ComboBox<String> priorityComboBox = new ComboBox<>();
        priorityComboBox.getItems().addAll("Наивысший", "Важный", "Низкий");
        priorityComboBox.setValue(task.getPriority().name()); // Устанавливаем текущий приоритет

        // Список для выбора статуса
        ComboBox<String> statusComboBox = new ComboBox<>();
        statusComboBox.getItems().addAll("Начата", "В_работе", "Выполнена", "Отменена");
        statusComboBox.setValue(task.getStatus().name()); // Устанавливаем текущий статус

        // Выбор даты дедлайна
        DatePicker deadlinePicker = new DatePicker();
        deadlinePicker.setValue(task.getDeadline()); // Устанавливаем текущий дедлайн

        // Создание кнопок ОК и Отмена
        Button okButton = new Button("ОК");
        Button cancelButton = new Button("Отмена");

        // Создаем Stage для диалога
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Редактирование задачи");

        // Обработчик для кнопки "ОК" - закрыть окно
        okButton.setOnAction(e -> dialogStage.close());

        // Обработчик для кнопки "Отмена" - закрыть окно без изменений
        cancelButton.setOnAction(e -> dialogStage.close());

        // Контейнер для кнопок
        HBox buttonBox = new HBox(10, okButton, cancelButton);
        buttonBox.setAlignment(Pos.TOP_RIGHT);

        // Создание нового окна для ввода
        VBox vbox = new VBox(10, nameDialog.getEditor(), descriptionDialog.getEditor(), priorityComboBox, statusComboBox, deadlinePicker, buttonBox);
        Scene dialogScene = new Scene(vbox, 300, 250);
        dialogStage.setScene(dialogScene);
        vbox.setAlignment(Pos.TOP_LEFT);

        // Показать окно и ждать, пока пользователь не закроет его
        dialogStage.showAndWait();

        // После закрытия диалога возвращаем обновленную задачу
        Task updatedTask = new Task(
                task.getId(), // Сохраняем ID
                task.getProjectId(), // Сохраняем ID проекта
                nameDialog.getEditor().getText(), // Обновленное название
                descriptionDialog.getEditor().getText(), // Обновленное описание
                Task.TaskStatus.valueOf(statusComboBox.getValue()), // Обновленный статус
                Task.TaskPriority.valueOf(priorityComboBox.getValue()), // Обновленный приоритет
                deadlinePicker.getValue() // Обновленный дедлайн
        );

        return updatedTask;
    }
}