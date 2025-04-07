package com.example.taskmanager.service;

import com.example.taskmanager.db.TaskDAO;
import com.example.taskmanager.model.Task;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class TaskService {

    // Объект доступа к данным (DAO), взаимодействует с базой данных
    private final TaskDAO taskDAO;

    // Получение всех задач из базы данных
    public List<Task> getTasks() {
        return taskDAO.getTasks();
    }

    // Добавление новой задачи
    public void addTask(Task task) {
        taskDAO.addTask(task);
    }

    // Удаление задачи по её идентификатору
    public void deleteTaskById(UUID id) {
        taskDAO.deleteTaskById(id);
    }

    // Обновление существующей задачи
    public void updateTask(Task task) {
        taskDAO.updateTask(task);
    }

    // Поиск задач по ключевому слову (в названии или описании)
    public List<Task> searchTasks(String keyword) {
        return taskDAO.searchTasks(keyword);
    }

    // Сортировка задач по приоритету (от меньшего к большему)
    public List<Task> sortTasksByPriority(List<Task> tasks) {
        tasks.sort(Comparator.comparing(Task::getPriority));
        return tasks;
    }

    // Сортировка задач по статусу (по порядку перечисления в enum)
    public List<Task> sortTasksByStatus(List<Task> tasks) {
        tasks.sort(Comparator.comparing(Task::getStatus));
        return tasks;
    }

    // Сортировка задач по дате дедлайна (от ближайшего к более позднему)
    public List<Task> sortTasksByDeadline(List<Task> tasks) {
        tasks.sort(Comparator.comparing(Task::getDeadline));
        return tasks;
    }

    // Метод, определяющий тип сортировки на основе выбранной пользователем опции
    public List<Task> sortTasks(List<Task> tasks, String sortBy) {
        switch (sortBy) {
            case "Приоритет":
                return sortTasksByPriority(tasks);
            case "Статус":
                return sortTasksByStatus(tasks);
            case "Дедлайн":
                return sortTasksByDeadline(tasks);
            default:
                return tasks; // Если не выбрана сортировка, возвращаем задачи без изменений
        }
    }
}
