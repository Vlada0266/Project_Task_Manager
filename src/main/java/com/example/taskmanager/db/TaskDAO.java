package com.example.taskmanager.db;


import com.example.taskmanager.model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskDAO {
    public void addTask(Task task) {
        String sql = "INSERT INTO tasks (id, project_id, name, description, status, priority) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, task.getId().toString());
            pstmt.setString(2, task.getProjectId().toString());
            pstmt.setString(3, task.getName());
            pstmt.setString(4, task.getDescription());
            pstmt.setString(5, task.getStatus().name());
            pstmt.setString(6, task.getPriority().name());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ошибка при добавлении задачи: " + e.getMessage());
        }
    }

    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Task task = new Task(
                        UUID.fromString(rs.getString("id")),
                        UUID.fromString(rs.getString("project_id")),
                        rs.getString("name"),
                        rs.getString("description"),
                        Task.TaskStatus.valueOf(rs.getString("status")),
                        Task.TaskPriority.valueOf(rs.getString("priority"))
                );
                tasks.add(task);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении задач: " + e.getMessage());
        }
        return tasks;
    }

    public void deleteTaskById(UUID id) {
        String sql = "DELETE FROM tasks AS t WHERE t.id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,id.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ошибка при удалении задачи: " + e.getMessage());
        }
    }
}