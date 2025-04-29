package com.example.taskmanager.validation;

import com.example.taskmanager.model.Task;

public class DescriptionValidator extends AbstractTaskValidator {

    public DescriptionValidator(TaskValidator nextValidator) {
        super(nextValidator);
    }

    @Override
    public void validate(Task task) {
        System.out.println("Проверка описания задачи: " + task.getDescription());
        if (task.getDescription() == null || task.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Описание задачи не может быть пустым!");
        }
        validateNext(task);
    }
}

