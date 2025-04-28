package com.example.taskmanager.validation;

import com.example.taskmanager.model.Task;

public abstract class AbstractTaskValidator implements TaskValidator {
    protected TaskValidator nextValidator;

    @Override
    public TaskValidator setNext(TaskValidator next) {
        this.nextValidator = next;
        return next;
    }

    protected void validateNext(Task task) {
        if (nextValidator != null) {
            nextValidator.validate(task);
        }
    }
}
