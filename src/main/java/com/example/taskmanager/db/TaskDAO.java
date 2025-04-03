package com.example.taskmanager.db;


import com.example.taskmanager.model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class TaskDAO {
    public void addTask(Task task) {
        String sql = "INSERT INTO tasks (id, project_id, name, description, status, priority, deadline) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, task.getId().toString());
            pstmt.setString(2, task.getProjectId().toString());
            pstmt.setString(3, task.getName());
            pstmt.setString(4, task.getDescription());
            pstmt.setString(5, task.getStatus().name());
            pstmt.setString(6, task.getPriority().name());
            pstmt.setDate(7, java.sql.Date.valueOf(task.getDeadline())); // Сохраняем дедлайн
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ошибка при добавлении задачи: " + e.getMessage());
        }
    }

    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Task task = new Task(
                        UUID.fromString(rs.getString("id")),
                        UUID.fromString(rs.getString("project_id")),
                        rs.getString("name"),
                        rs.getString("description"),
                        Task.TaskStatus.valueOf(rs.getString("status")),
                        Task.TaskPriority.valueOf(rs.getString("priority")),
                        rs.getDate("deadline") != null ? rs.getDate("deadline").toLocalDate() : null // Получаем дедлайн
                );
                tasks.add(task);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении задач: " + e.getMessage());
        }
        return tasks;
    }

    // Метод для удаления задачи
    public void deleteTaskById(UUID id) {
        String sql = "DELETE FROM tasks WHERE id = ?";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ошибка при удалении задачи: " + e.getMessage());
        }
    }
    public void updateTask(Task task) {
        String sql = "UPDATE tasks SET name = ?, description = ?, status = ?, priority = ?, deadline = ? WHERE id = ?";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, task.getName());
            pstmt.setString(2, task.getDescription());
            pstmt.setString(3, task.getStatus().name());
            pstmt.setString(4, task.getPriority().name());
            pstmt.setDate(5, task.getDeadline() != null ? java.sql.Date.valueOf(task.getDeadline()) : null);
            pstmt.setString(6, task.getId().toString());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ошибка при обновлении задачи: " + e.getMessage());
        }
    }

    public List<Task> searchTasks(String keyword) {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE name LIKE ? OR description LIKE ?";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Task task = new Task(
                        UUID.fromString(rs.getString("id")),
                        UUID.fromString(rs.getString("project_id")),
                        rs.getString("name"),
                        rs.getString("description"),
                        Task.TaskStatus.valueOf(rs.getString("status")),
                        Task.TaskPriority.valueOf(rs.getString("priority")),
                        rs.getDate("deadline").toLocalDate()
                );
                tasks.add(task);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при поиске задач: " + e.getMessage());
        }
        return tasks;
    }

}