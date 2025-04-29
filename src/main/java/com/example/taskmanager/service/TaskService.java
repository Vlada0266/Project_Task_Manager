package com.example.taskmanager.service;

import com.example.taskmanager.db.TaskDAO;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.validation.TaskValidator;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class TaskService implements TaskServiceInterface {

    private final TaskDAO taskDAO;
    private final TaskValidator validatorChain;

    @Override
    public List<Task> getTasks() {
        return taskDAO.getTasks();
    }

    @Override
    public void addTask(Task task) {
        validatorChain.validate(task); // Валидируем задачу перед добавлением
        taskDAO.addTask(task);
    }

    @Override
    public void deleteTaskById(UUID id) {
        taskDAO.deleteTaskById(id);
    }

    @Override
    public void updateTask(Task task) {
        validatorChain.validate(task); // Валидируем задачу перед обновлением
        taskDAO.updateTask(task);
    }

    @Override
    public List<Task> searchTasks(String keyword) {
        return taskDAO.searchTasks(keyword);
    }

    @Override
    public List<Task> sortTasks(List<Task> tasks, String sortBy) {
        switch (sortBy) {
            case "Приоритет":
                tasks.sort(Comparator.comparing(Task::getPriority));
                break;
            case "Статус":
                tasks.sort(Comparator.comparing(Task::getStatus));
                break;
            case "Дедлайн":
                tasks.sort(Comparator.comparing(Task::getDeadline));
                break;
            default:
                // Ничего не делаем, оставляем как есть
        }
        return tasks;
    }
}
