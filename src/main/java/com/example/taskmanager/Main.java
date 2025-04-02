package com.example.taskmanager;

import com.example.taskmanager.db.DatabaseConnection;
import com.example.taskmanager.db.TaskDAO;
import com.example.taskmanager.service.TaskService;
import com.example.taskmanager.view.TaskView;
import com.example.taskmanager.presenter.TaskPresenter;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        DatabaseConnection.initializeDatabase();
        TaskDAO taskDAO = new TaskDAO();
        TaskService taskService = new TaskService(taskDAO);
        TaskView view = new TaskView(primaryStage);
        TaskPresenter presenter = new TaskPresenter(view, taskService);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
