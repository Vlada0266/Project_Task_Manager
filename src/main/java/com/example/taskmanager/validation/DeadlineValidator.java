package com.example.taskmanager.validation;

import com.example.taskmanager.model.Task;

import java.time.LocalDate;

public class DeadlineValidator extends AbstractTaskValidator {

    public DeadlineValidator(TaskValidator nextValidator) {
        super(nextValidator);
    }

    @Override
    public void validate(Task task) {
        if (task.getDeadline() == null) {
            throw new IllegalArgumentException("Дедлайн не может быть пустым!");
        }

        if (task.getDeadline().isBefore(LocalDate.now())) {
             throw new IllegalArgumentException("Дедлайн не может быть в прошлом!");
        }

        validateNext(task); // Переход к следующему валидатору, если есть
    }
}
