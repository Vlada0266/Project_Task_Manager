package com.example.taskmanager.validation;

import com.example.taskmanager.model.Task;

import java.time.LocalDate;

public class DeadlineValidator extends AbstractTaskValidator {

    public DeadlineValidator(TaskValidator nextValidator) {
        super(nextValidator);
    }

    @Override
    public void validate(Task task) {
        System.out.println("Проверка дедлайна задачи: " + task.getDeadline());
        if (task.getDeadline() != null && task.getDeadline().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Дедлайн не может быть в прошлом!");
        }
        validateNext(task);
    }
}
