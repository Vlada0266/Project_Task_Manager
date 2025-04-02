package com.example.taskmanager.presenter;

import com.example.taskmanager.model.*;
import com.example.taskmanager.service.TaskService;
import com.example.taskmanager.view.TaskView;

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
    }

    private void addTask() {
        String taskName = view.getTaskInputField().getText();
        if (!taskName.isEmpty()) {
            Task task = new Task(UUID.randomUUID(), UUID.randomUUID(), taskName, "Hello", Task.TaskStatus.OPEN, Task.TaskPriority.MAJOR);
            taskService.addTask(task);
            updateTaskList();
        }
    }

    private void deleteTask() {
        Task selectedTask = view.getTaskListView().getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            taskService.deleteTaskById(selectedTask.getId()); // Assuming Task has a constructor with a name
            updateTaskList();
        }
    }

    private void clearSearch() {
        view.getSearchField().clear();
        updateTaskList();
    }

    private void sortTasks() {
//        taskService.sortTasksByPriority();
        updateTaskList();
    }

    private void updateTaskList() {
        view.getTaskListView().getItems().clear();
        for (Task task : taskService.getTasks()) {
            view.getTaskListView().getItems().add(task);
        }
    }
}

