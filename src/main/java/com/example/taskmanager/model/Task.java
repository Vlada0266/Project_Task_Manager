package com.example.taskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Task {
    private UUID id;
    private UUID projectId;
    private String name;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDate deadline;


    public enum TaskStatus {
        Начата, В_работе, Выполнена, Отменена
    }

    public enum TaskPriority {
        Наивысший, Важный, Низкий
    }
}