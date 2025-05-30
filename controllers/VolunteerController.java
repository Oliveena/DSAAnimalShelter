package controllers;

import services.VolunteerService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Controller for managing volunteer-related operations including
 * registration and task assignment.
 */
public class VolunteerController {

    private final VolunteerService volunteerService;
    private final Scanner scanner;

    /**
     * Constructs a new {@code VolunteerController}.
     *
     * @param volunteerService The service layer handling volunteer logic.
     * @param scanner          Scanner used for user input.
     */
    public VolunteerController(VolunteerService volunteerService, Scanner scanner) {
        this.volunteerService = volunteerService;
        this.scanner = scanner;
    }

    /**
     * Prompts the user to enter a volunteer's name and registers them via the {@link VolunteerService}.
     */
    public void registerVolunteer() {
        System.out.println("\n--- Register a Volunteer ---");
        String name = prompt("Enter volunteer name: ");
        volunteerService.registerVolunteer(name);
        System.out.println("Volunteer '" + name + "' registered.");
    }

    /**
     * Prompts the user to add a new task for volunteers, including a task description and due date.
     * The task is registered through the {@link VolunteerService}.
     */
    public void addTask() {
        System.out.println("\n--- Add New Volunteer Task ---");
        String description = prompt("Enter task description: ");
        LocalDate dueDate = promptForDate("Enter due date (YYYY-MM-DD): ");
        volunteerService.addTask(description, dueDate);
        System.out.println("Task added and volunteers notified.");
    }

    /**
     * Prompts the user to enter a date string and parses it into a {@link LocalDate}.
     * Retries until a valid date in the format YYYY-MM-DD is provided.
     *
     * @param message The prompt message.
     * @return A valid {@link LocalDate} entered by the user.
     */
    private LocalDate promptForDate(String message) {
        while (true) {
            String input = prompt(message);
            try {
                return LocalDate.parse(input);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }
    }

    /**
     * Prompts the user with a message and returns the trimmed input.
     *
     * @param message The prompt message to display.
     * @return The user's input as a trimmed string.
     */
    private String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }
}
