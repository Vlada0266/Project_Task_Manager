package com.example.taskmanager.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task {
    private final StringProperty name;
    private final StringProperty description;
    private final StringProperty assignee;
    private final StringProperty priority;
    private final StringProperty status;

    public Task(String name, String description, String assignee, String priority) {
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.assignee = new SimpleStringProperty(assignee);
        this.priority = new SimpleStringProperty(priority);
        this.status = new SimpleStringProperty("Не выполнено"); // По умолчанию статус "Не выполнено"
    }

    // === Геттеры для TableView ===
    public StringProperty nameProperty() { return name; }
    public StringProperty descriptionProperty() { return description; }
    public StringProperty assigneeProperty() { return assignee; }
    public StringProperty priorityProperty() { return priority; }
    public StringProperty statusProperty() { return status; }

    // === Обычные геттеры ===
    public String getName() { return name.get(); }
    public String getDescription() { return description.get(); }
    public String getAssignee() { return assignee.get(); }
    public String getPriority() { return priority.get(); }
    public String getStatus() { return status.get(); }

    // === Сеттеры ===
    public void setName(String name) { this.name.set(name); }
    public void setDescription(String description) { this.description.set(description); }
    public void setAssignee(String assignee) { this.assignee.set(assignee); }
    public void setPriority(String priority) { this.priority.set(priority); }
    public void setStatus(String status) { this.status.set(status); }

    @Override
    public String toString() {
        return "Задача: " + name.get() + ", Исполнитель: " + assignee.get() +
                ", Приоритет: " + priority.get() + ", Статус: " + status.get();
    }
}