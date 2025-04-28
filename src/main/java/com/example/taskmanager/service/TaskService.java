package com.example.taskmanager.service;

import com.example.taskmanager.db.TaskDAO;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.validation.DeadlineValidator;
import com.example.taskmanager.validation.DescriptionValidator;
import com.example.taskmanager.validation.NameValidator;
import com.example.taskmanager.validation.TaskValidator;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class TaskService implements TaskServiceInterface {

    private final TaskDAO taskDAO;
    private final TaskValidator validatorChain;

    public TaskService(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
        this.validatorChain = new NameValidator();
        validatorChain.setNext(new DescriptionValidator())
                .setNext(new DeadlineValidator());
    }

    @Override
    public List<Task> getTasks() {
        return taskDAO.getTasks();
    }

    @Override
    public void addTask(Task task) {
        validatorChain.validate(task); // Проверка задачи через цепочку
        taskDAO.addTask(task);
    }

    @Override
    public void deleteTaskById(UUID id) {
        taskDAO.deleteTaskById(id);
    }

    @Override
    public void updateTask(Task task) {
        validatorChain.validate(task); // Проверка задачи перед обновлением
        taskDAO.updateTask(task);
    }

    @Override
    public List<Task> searchTasks(String keyword) {
        return taskDAO.searchTasks(keyword);
    }

    public List<Task> sortTasksByPriority(List<Task> tasks) {
        tasks.sort(Comparator.comparing(Task::getPriority));
        return tasks;
    }

    public List<Task> sortTasksByStatus(List<Task> tasks) {
        tasks.sort(Comparator.comparing(Task::getStatus));
        return tasks;
    }

    public List<Task> sortTasksByDeadline(List<Task> tasks) {
        tasks.sort(Comparator.comparing(Task::getDeadline));
        return tasks;
    }

    @Override
    public List<Task> sortTasks(List<Task> tasks, String sortBy) {
        switch (sortBy) {
            case "Приоритет":
                return sortTasksByPriority(tasks);
            case "Статус":
                return sortTasksByStatus(tasks);
            case "Дедлайн":
                return sortTasksByDeadline(tasks);
            default:
                return tasks;
        }
    }
}
