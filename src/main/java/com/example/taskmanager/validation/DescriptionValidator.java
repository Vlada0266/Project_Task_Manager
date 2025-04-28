package com.example.taskmanager.validation;

import com.example.taskmanager.model.Task;

public class DescriptionValidator extends AbstractTaskValidator {
    @Override
    public void validate(Task task) {
        if (task.getDescription() == null || task.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Описание задачи не может быть пустым!");
        }
        validateNext(task);
    }
}
