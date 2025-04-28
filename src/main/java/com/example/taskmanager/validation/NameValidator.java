package com.example.taskmanager.validation;

import com.example.taskmanager.model.Task;

public class NameValidator extends AbstractTaskValidator {
    @Override
    public void validate(Task task) {
        if (task.getName() == null || task.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Название задачи не может быть пустым!");
        }
        validateNext(task);
    }
}
