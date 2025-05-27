package services;


import models.Task;
import models.Volunteer;
import patterns.observer.VolunteerObserver;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class VolunteerService {
    private final Shelter shelter;
    private Scanner scanner;

    public VolunteerService(Shelter shelter, Scanner scanner) {
        this.shelter = shelter;
        this.scanner = scanner;
    }

    public void registerVolunteer() {
        System.out.println("\n--- Register a Volunteer ---");
        System.out.print("Enter volunteer name: ");
        String name = scanner.nextLine().trim();

        VolunteerObserver volunteer = new Volunteer(name);
        shelter.registerVolunteer(volunteer);
        System.out.println("Volunteer '" + name + "' registered.");
    }

    public void addTask() {
        System.out.println("\n--- Add New Volunteer Task ---");
        System.out.print("Enter task description: ");
        String description = scanner.nextLine().trim();

        LocalDate dueDate;
        while (true) {
            try {
                System.out.print("Enter due date (YYYY-MM-DD): ");
                String dateInput = scanner.nextLine().trim();
                dueDate = LocalDate.parse(dateInput);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }

        Task task = new Task(description, dueDate);
        shelter.addTask(task);
        System.out.println("Task added and volunteers notified.");
    }
}
