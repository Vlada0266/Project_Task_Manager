package com.example.taskmanager;

import com.example.taskmanager.model.TaskManager;
import com.example.taskmanager.view.TaskView;
import com.example.taskmanager.presenter.TaskPresenter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Создание модели
        TaskManager model = new TaskManager();

        // Создание представления
        TaskView view = new TaskView();

        // Создание презентера
        TaskPresenter presenter = new TaskPresenter(view, model);

        // Настройка сцены и отображение
        StackPane root = new StackPane();
        root.getChildren().add(view); // Добавляем TaskView в корень сцены

        Scene scene = new Scene(root, 800, 600); // Размер сцены
        primaryStage.setTitle("Task Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Запуск JavaFX приложения
    }
}
