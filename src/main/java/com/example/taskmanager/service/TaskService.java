package com.example.taskmanager.service;


import com.example.taskmanager.db.TaskDAO;
import com.example.taskmanager.model.Task;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
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

    public void updateTask(Task task) { taskDAO.updateTask(task); }

    public List<Task> searchTasks(String keyword) { return taskDAO.searchTasks(keyword); }

    public List<Task> sortTasksByPriority(List<Task> tasks) {
        tasks.sort(Comparator.comparing(Task::getPriority));
        return tasks;
    }

    public List<Task> sortTasksByStatus(List<Task> tasks) {
        tasks.sort(Comparator.comparing(Task::getStatus));
        return tasks;
    }

    public List<Task> sortTasksByDeadline(List<Task> tasks) {
        tasks.sort(Comparator.comparing(Task::getDeadline)); // Сортировка по дедлайну
        return tasks;
    }

    // Метод для сортировки по выбранному критерию
    public List<Task> sortTasks(List<Task> tasks, String sortBy) {
        switch (sortBy) {
            case "Приоритет":
                return sortTasksByPriority(tasks);
            case "Статус":
                return sortTasksByStatus(tasks);
            case "Дедлайн":
                return sortTasksByDeadline(tasks);
            default:
                return tasks; // Если ничего не выбрано, возвращаем как есть
        }
    }
}


