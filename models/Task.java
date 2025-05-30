package models;

import java.time.LocalDate;

/**
 * Represents a task with a description and a due date.
 * <p>
 * A task encapsulates details about what needs to be done and when it is due.
 * </p>
 */
public class Task {
    private String description;
    private LocalDate dueDate;

    /**
     * Constructs a new {@code Task} with the given description and due date.
     *
     * @param description the description of the task
     * @param dueDate     the date by which the task should be completed
     */
    public Task(String description, LocalDate dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }

    /**
     * Returns the task description.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the due date of the task.
     *
     * @return the due date
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Returns a string representation of the task details,
     * including the description and due date.
     *
     * @return formatted task details
     */
    public String getDetails() {
        return "Description: " + description + "\nDue Date: " + dueDate;
    }
}
