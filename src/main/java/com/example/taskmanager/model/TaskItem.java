package com.example.taskmanager.model;

public abstract class TaskItem {
    private  String name;
    private  String description;

    public TaskItem(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }

    public abstract String getDetails();
}