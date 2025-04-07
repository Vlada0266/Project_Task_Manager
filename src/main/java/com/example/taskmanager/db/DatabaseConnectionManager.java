package com.example.taskmanager.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectionManager {

    private static final String URL = "jdbc:sqlite:task_manager.db"; // Путь к базе данных
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL);
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
    public static void initializeDatabase() {
        String createTasksTable = """
        CREATE TABLE IF NOT EXISTS tasks (
            id TEXT PRIMARY KEY NOT NULL,
            project_id TEXT NOT NULL,
            name TEXT NOT NULL,
            description TEXT,
            status TEXT NOT NULL CHECK(status IN ('Начата', 'В_работе', 'Выполнена', 'Отменена')),
            priority TEXT NOT NULL CHECK(priority IN ('Наивысший', 'Важный', 'Низкий')),
            deadline DATE,  -- Добавляем столбец для дедлайна
            FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE
        );
    """;

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            // Выполняем запросы на создание таблиц
            stmt.execute(createTasksTable);
        } catch (SQLException e) {
            System.err.println("Ошибка инициализации БД: " + e.getMessage());
        }
    }

}
