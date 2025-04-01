package com.example.taskmanager.model;

import java.util.ArrayList;
import java.util.List;

public class Project extends TaskItem {
    private  List<Task> tasks;

    public Project(String name, String description) {
        super(name, description);
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) { tasks.add(task); }

    @Override
    public String getDetails() {
        return "Project: " + getName() + ", Tasks: " + tasks.size();
    }
}