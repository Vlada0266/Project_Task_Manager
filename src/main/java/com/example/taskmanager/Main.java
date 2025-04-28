package com.example.taskmanager;

import com.example.taskmanager.db.DatabaseConnectionManager;
import com.example.taskmanager.db.TaskDAO;
import com.example.taskmanager.service.TaskService;
import com.example.taskmanager.service.TaskServiceInterface;
import com.example.taskmanager.service.TaskServiceLoggerDecorator;
import com.example.taskmanager.view.TaskView;
import com.example.taskmanager.presenter.TaskPresenter;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Инициализация базы данных (создание таблиц, если они не существуют)
        DatabaseConnectionManager.initializeDatabase();

        // Создание объекта доступа к данным (DAO)
        TaskDAO taskDAO = new TaskDAO();

        // Создание сервисного слоя, обернутого в декоратор для логирования
        TaskServiceInterface taskService = new TaskServiceLoggerDecorator(new TaskService(taskDAO));

        // Создание представления (UI) — сцена отображается в TaskView
        TaskView view = new TaskView(primaryStage);

        // Создание презентера, который связывает логику (сервис) и интерфейс (view)
        TaskPresenter presenter = new TaskPresenter(view, taskService);  // Здесь передаем интерфейс TaskServiceInterface
    }

    public static void main(String[] args) {
        launch(args);
    }
}
