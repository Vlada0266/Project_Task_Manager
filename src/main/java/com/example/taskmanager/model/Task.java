package com.example.taskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@AllArgsConstructor
@Getter
@ToString(of = {"id", "name"})
public class Task {
    private UUID id;
    private UUID projectId;
    private String name;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;

    public enum TaskStatus {
        OPEN, IN_PROGRESS, DONE, REJECT
    }

    public enum TaskPriority {
        HIGH, MAJOR, LOW
    }
}