package com.example.taskmanager;

import com.example.taskmanager.db.DatabaseConnectionManager;
import com.example.taskmanager.db.TaskDAO;
import com.example.taskmanager.service.TaskService;
import com.example.taskmanager.service.TaskServiceInterface;
import com.example.taskmanager.service.TaskServiceLoggerDecorator;
import com.example.taskmanager.validation.NameValidator;
import com.example.taskmanager.validation.DescriptionValidator;
import com.example.taskmanager.validation.DeadlineValidator;
import com.example.taskmanager.validation.TaskValidator;
import com.example.taskmanager.view.TaskView;
import com.example.taskmanager.presenter.TaskPresenter;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Инициализация базы данных
        DatabaseConnectionManager.initializeDatabase();

        // Создание DAO
        TaskDAO taskDAO = new TaskDAO();

        // Создание цепочки валидаторов через конструкторы
        TaskValidator validatorChain = new NameValidator(
                new DescriptionValidator(
                        new DeadlineValidator(null)
                )
        );

        // Создание сервиса с декоратором
        TaskServiceInterface taskService = new TaskServiceLoggerDecorator(new TaskService(taskDAO, validatorChain));


        // Создание View
        TaskView view = new TaskView(primaryStage);

        // Создание Presenter
        TaskPresenter presenter = new TaskPresenter(view, taskService);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

