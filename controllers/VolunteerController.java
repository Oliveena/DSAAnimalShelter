package controllers;

import services.VolunteerService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class VolunteerController {
    private final VolunteerService volunteerService;
    private final Scanner scanner;

    public VolunteerController(VolunteerService volunteerService, Scanner scanner) {
        this.volunteerService = volunteerService;
        this.scanner = scanner;
    }

    public void registerVolunteer() {
        System.out.println("\n--- Register a Volunteer ---");
        String name = prompt("Enter volunteer name: ");
        volunteerService.registerVolunteer(name);
        System.out.println("Volunteer '" + name + "' registered.");
    }

    public void addTask() {
        System.out.println("\n--- Add New Volunteer Task ---");
        String description = prompt("Enter task description: ");
        LocalDate dueDate;
        while (true) {
            try {
                dueDate = LocalDate.parse(prompt("Enter due date (YYYY-MM-DD): "));
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }
        volunteerService.addTask(description, dueDate);
        System.out.println("Task added and volunteers notified.");
    }

    private String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }
}
