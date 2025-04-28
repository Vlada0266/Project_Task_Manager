package com.example.taskmanager.validation;

import com.example.taskmanager.model.Task;

import java.time.LocalDate;

public class DeadlineValidator extends AbstractTaskValidator {
    @Override
    public void validate(Task task) {
        if (task.getDeadline() != null && task.getDeadline().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Дедлайн не может быть в прошлом!");
        }
        validateNext(task);
    }
}
