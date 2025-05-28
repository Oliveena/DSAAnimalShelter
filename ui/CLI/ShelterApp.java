package ui.CLI;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

import controllers.AdoptionController;
import controllers.AnimalController;
import controllers.MedicalRecordController;
import models.*;
import models.animals.Animal;
import patterns.builders.*;
import patterns.factories.LogFactory;
import patterns.factories.FormFactory;
import patterns.observer.VolunteerManager;
import patterns.observer.VolunteerObserver;
import patterns.strategies.AdoptionStrategy;
import patterns.strategies.FIFOAdoptionStrategy;
import services.AdoptionService;
import services.AnimalService;
import services.MedicalRecordService;
import services.ShelterService;

import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * The main application for managing the animal shelter, providing functionality
 * for adding, listing, adopting, and removing animals from the shelter.
 * <p>
 * The app interacts with an animal registry and an adoption queue, providing
 * various functionalities such as adding animals, searching, sorting, and adopting animals.
 * It allows interaction via a command-line interface.
 */
public class ShelterApp {
        private ShelterQueue queue = new ShelterQueue();
        private FormFactory formFactory = new FormFactory();
        private Scanner scanner = new Scanner(System.in);
        private AdoptionStrategy adoptionStrategy = new FIFOAdoptionStrategy();
        private AnimalRegistry registry = new AnimalRegistry();
        private VolunteerManager volunteerManager = new VolunteerManager();
        private AnimalService animalService = new AnimalService(registry);
        private MedicalRecordService medicalService = new MedicalRecordService();
        private MedicalRecordController medicalController = new MedicalRecordController(medicalService, scanner);
        private AnimalController animalController = new AnimalController(animalService, medicalController, scanner, registry, queue);
        private ShelterService shelter = new ShelterService(registry, volunteerManager);

        private AdoptionService adoptionService = new AdoptionService(queue, registry, adoptionStrategy, formFactory);
        private AdoptionController adoptionController = new AdoptionController(adoptionService, scanner);

        // logging
    private static final Logger logger = LogFactory.getLogger(ShelterApp.class);

        // storing vaccination records
    private Map<Animal, List<String>> vaccinationRecords = new HashMap<>();

        public void start() {
            boolean exit = false;
            while (!exit) {
                ui.CLI.ShelterMenu.displayMenu();
                String choice = scanner.nextLine().trim();
                switch (choice) {
                    case "1" -> animalController.addAnimal();
                    case "2" -> animalController.listAnimals();
                    case "3" -> adoptionController.adoptAnimal();
                    case "4" -> animalController.findAnimalByName();
                    case "5" -> animalController.removeAnimal();
                    case "6" -> adoptionController.peekNextAnimal();
                    case "7" -> adoptionController.clearQueue();
                    case "8" -> animalController.findAnimalById();
                    case "9" -> animalController.findAnimalsBySpecies();
                    case "10" -> animalController.sortAnimals();
                    case "11" -> registerVolunteer();
                    case "12" -> addTask();
                    case "0" -> exit = true;
                    default -> System.out.println("Invalid choice.");
                }
            }
        }

        private void registerVolunteer() {
            System.out.println("\n--- Register a Volunteer ---");
            String name = prompt("Enter volunteer name: ");
            shelter.registerVolunteer(new Volunteer(name));
            System.out.println("Volunteer '" + name + "' registered.");
        }

        private void addTask() {
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
            shelter.addTask(new Task(description, dueDate));
            System.out.println("Task added and volunteers notified.");
        }



        // === HELPER METHODS ===

        private String prompt(String message) {
            System.out.print(message);
            return scanner.nextLine().trim();
        }

        private boolean promptBoolean(String message) {
            while (true) {
                String input = prompt(message);
                if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
                    return Boolean.parseBoolean(input);
                }
                System.out.println("Please enter 'true' or 'false'.");
            }
        }

        private int promptInt(String message, int min, int max) {
            while (true) {
                try {
                    int value = Integer.parseInt(prompt(message));
                    if (value < min || value > max) {
                        System.out.printf("Please enter a value between %d and %d.%n", min, max);
                        continue;
                    }
                    return value;
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
            }
        }

        private double promptDouble(String message, double min) {
            while (true) {
                try {
                    double value = Double.parseDouble(prompt(message));
                    if (value < min) {
                        System.out.println("Value must be greater than " + min);
                        continue;
                    }
                    return value;
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
            }
        }

        private String promptEnum(String message, List<String> allowed) {
            while (true) {
                String input = prompt(message).toLowerCase();
                if (allowed.contains(input)) return input;
                System.out.println("Invalid input. Allowed values: " + allowed);
            }
        }

        private void collectListEntries(Consumer<String> consumer) {
            while (true) {
                String input = scanner.nextLine().trim();
                if ("done".equalsIgnoreCase(input)) break;
                consumer.accept(input);
            }
        }
    }
