package models;

import java.time.LocalDate;

public class Task {
    private String description;
    private LocalDate dueDate;

    public Task(String description, LocalDate dueDate) {
        this.description = description;
        this.dueDate = dueDate;

    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getDetails() {
        return "Description: " + description + "\nDue Date: " + dueDate;

    }
}
