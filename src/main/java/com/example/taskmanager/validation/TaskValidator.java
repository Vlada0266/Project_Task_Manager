package com.example.taskmanager.validation;

import com.example.taskmanager.model.Task;

public interface TaskValidator {
    void validate(Task task);
    TaskValidator setNext(TaskValidator next);
}
