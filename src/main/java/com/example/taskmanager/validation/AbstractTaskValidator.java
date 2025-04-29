package com.example.taskmanager.validation;

import com.example.taskmanager.model.Task;

public abstract class AbstractTaskValidator implements TaskValidator {

    private final TaskValidator nextValidator;

    // Новый конструктор для установки следующего валидатора
    protected AbstractTaskValidator(TaskValidator nextValidator) {
        this.nextValidator = nextValidator;
    }

    protected void validateNext(Task task) {
        if (nextValidator != null) {
            nextValidator.validate(task);
        }
    }
}

