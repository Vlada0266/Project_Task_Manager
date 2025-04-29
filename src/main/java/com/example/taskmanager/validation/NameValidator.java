package com.example.taskmanager.validation;

import com.example.taskmanager.model.Task;

public class NameValidator extends AbstractTaskValidator {

    public NameValidator(TaskValidator nextValidator) {
        super(nextValidator);
    }

    @Override
    public void validate(Task task) {
        System.out.println("Проверка имени: " + task.getName());
        if (task.getName() == null || task.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Название задачи не может быть пустым!");
        }
        validateNext(task);
    }
}

