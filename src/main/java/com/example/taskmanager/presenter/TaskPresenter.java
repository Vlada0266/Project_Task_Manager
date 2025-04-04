package com.example.taskmanager.presenter;

import com.example.taskmanager.model.*;
import com.example.taskmanager.service.TaskService;
import com.example.taskmanager.view.TaskView;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class TaskPresenter {
    private TaskView view;
    private TaskService taskService;

    public TaskPresenter(TaskView view, TaskService taskService) {
        this.view = view;
        this.taskService = taskService;
        bind();
        updateTaskList();
    }

    private void bind() {
        // Обработчик для добавления задачи
        view.getAddTaskButton().setOnAction(e -> addTask());

        // Обработчик для удаления задачи
        view.getDeleteTaskButton().setOnAction(e -> deleteTask());

        // Обработчик для очистки поиска
        view.getClearSearchButton().setOnAction(e -> clearSearch());

        // Обработчик для сортировки задач
        view.getSortButton().setOnAction(e -> sortTasks());

        view.getEditTaskButton().setOnAction(e -> editTask());

        view.getSearchField().textProperty().addListener((observable, oldValue, newValue) -> searchTasks(newValue));

        view.getSortButton().setOnAction(e -> sortTasks());

        view.getSearchField().textProperty().addListener((observable, oldValue, newValue) -> searchTasks(newValue));
    }


    private void addTask() {
        String taskName = view.getTaskInputField().getText();
        String taskDescription = view.getDescriptionField().getText();
        LocalDate deadline = view.getDeadlinePicker().getValue();
        String selectedPriority = view.getPriorityComboBox().getValue();
        String selectedStatus = view.getStatusComboBox().getValue();

        // Проверка на обязательные поля
        if (taskName == null || taskName.isEmpty()
                || taskDescription == null || taskDescription.isEmpty()
                || deadline == null
                || selectedPriority == null || selectedPriority.equals("Выберите приоритет")
                || selectedStatus == null || selectedStatus.equals("Выберите статус")) {

            showAlert("Ошибка", "Пожалуйста, заполните все поля: название, описание, дедлайн, приоритет и статус.");
            return;
        }

        try {
            Task.TaskPriority priority = Task.TaskPriority.valueOf(selectedPriority);
            Task.TaskStatus status = Task.TaskStatus.valueOf(selectedStatus);

            Task task = new Task(UUID.randomUUID(), UUID.randomUUID(), taskName, taskDescription, status, priority, deadline);
            taskService.addTask(task);
            updateTaskList();
            view.clearTaskInputFields(); // Очистим поля после успешного добавления

        } catch (IllegalArgumentException e) {
            showAlert("Ошибка", "Неверный формат данных. Пожалуйста, проверьте правильность ввода.");
        }
    }




    private void deleteTask() {
        Task selectedTask = view.getTaskListView().getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            taskService.deleteTaskById(selectedTask.getId()); // Assuming Task has a constructor with a name
            updateTaskList();
        }
    }
    private void editTask() {
        Task selectedTask = view.getTaskListView().getSelectionModel().getSelectedItem();
        if (selectedTask == null) return; // Если задача не выбрана, ничего не делаем

        // Открываем диалоговое окно для редактирования всех полей
        Task updatedTask = view.showEditDialog(selectedTask);
        if (updatedTask == null) return; // Если пользователь не ввел все данные, выходим

        // Обновляем задачу в сервисе
        taskService.updateTask(updatedTask);
        updateTaskList(); // Обновляем список задач на экране

    }


    private void searchTasks(String keyword) {
        view.getTaskListView().getItems().clear();
        if (keyword.isEmpty()) {
            updateTaskList();
            return;
        }
        for (Task task : taskService.searchTasks(keyword)) {
            view.getTaskListView().getItems().add(task);
        }
    }
    private void clearSearch() {
        view.getSearchField().clear();
        updateTaskList();
    }
    private void sortTasks() {
        String selectedSortOption = view.getSortComboBox().getValue(); // Получаем выбранный критерий сортировки
        List<Task> tasks = taskService.getTasks();  // Получаем список задач
        List<Task> sortedTasks = taskService.sortTasks(tasks, selectedSortOption); // Передаем задачи и выбранный критерий

        // Обновляем список задач с отсортированными элементами
        view.getTaskListView().getItems().clear();
        view.getTaskListView().getItems().addAll(sortedTasks);
    }

    private void updateTaskList() {
        view.getTaskListView().getItems().clear();
        for (Task task : taskService.getTasks()) {
            view.getTaskListView().getItems().add(task);
        }
    }

    // Показывает простое модальное окно с ошибкой
    private void showAlert(String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}

