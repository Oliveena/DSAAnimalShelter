package ui.CLI;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

import controllers.AdoptionController;
import controllers.AnimalController;
import controllers.MedicalRecordController;
import controllers.VolunteerController;
import models.*;
import models.animals.Animal;
import patterns.builders.*;
import patterns.factories.LogFactory;
import patterns.factories.FormFactory;
import patterns.observer.VolunteerManager;
import patterns.observer.VolunteerObserver;
import patterns.strategies.AdoptionStrategy;
import patterns.strategies.FIFOAdoptionStrategy;
import services.*;

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
    private static ShelterApp instance;

    public static ShelterApp getInstance() {
        if (instance == null) instance = new ShelterApp();
        return instance;
    }

    // === Dependencies ===
    private final ShelterQueue queue = new ShelterQueue();
    private final AnimalRegistry registry = new AnimalRegistry();
    private final Scanner scanner = new Scanner(System.in);

    private final FormFactory formFactory = new FormFactory();
    private final VolunteerManager volunteerManager = new VolunteerManager();
    private final AdoptionStrategy adoptionStrategy = new FIFOAdoptionStrategy();

    private final ShelterService shelterService = new ShelterService(registry, volunteerManager);
    private final AnimalService animalService = new AnimalService(registry);
    private final MedicalRecordService medicalService = new MedicalRecordService();
    private final VolunteerService volunteerService = new VolunteerService(volunteerManager, shelterService);
    private final AdoptionService adoptionService = new AdoptionService(queue, registry, adoptionStrategy, formFactory);

    // === Controllers ===
    private final MedicalRecordController medicalController = new MedicalRecordController(medicalService, scanner);
    private final AnimalController animalController = new AnimalController(animalService, medicalController, scanner, registry, queue, shelterService);
    private final VolunteerController volunteerController = new VolunteerController(volunteerService, scanner);
    private final AdoptionController adoptionController = new AdoptionController(adoptionService, scanner);

    // === Logging / State ===
    private static final Logger logger = LogFactory.getLogger(ShelterApp.class);
    private final Map<Animal, List<String>> vaccinationRecords = new HashMap<>();

    // === Private Constructor ===
    public ShelterApp() {}

    // === CLI Start Method ===
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
                case "11" -> volunteerController.registerVolunteer();
                case "12" -> volunteerController.addTask();
                case "0" -> exit = true;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // === CLI Prompt Helpers ===
    public String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    public boolean promptBoolean(String message) {
        while (true) {
            String input = prompt(message);
            if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(input);
            }
            System.out.println("Please enter 'true' or 'false'.");
        }
    }

    public int promptInt(String message, int min, int max) {
        while (true) {
            try {
                int value = Integer.parseInt(prompt(message));
                if (value >= min && value <= max) return value;
            } catch (NumberFormatException ignored) {}
            System.out.printf("Please enter a value between %d and %d.%n", min, max);
        }
    }

    public double promptDouble(String message, double min) {
        while (true) {
            try {
                double value = Double.parseDouble(prompt(message));
                if (value >= min) return value;
            } catch (NumberFormatException ignored) {}
            System.out.printf("Please enter a number greater than %.2f%n", min);
        }
    }

    public String promptEnum(String message, List<String> allowed) {
        while (true) {
            String input = prompt(message).toLowerCase();
            if (allowed.contains(input)) return input;
            System.out.println("Invalid input. Allowed: " + allowed);
        }
    }

    public void collectListEntries(Consumer<String> consumer) {
        while (true) {
            String input = scanner.nextLine().trim();
            if ("done".equalsIgnoreCase(input)) break;
            consumer.accept(input);
        }
    }

    // === Accessors for Controllers and Services ===

    public AnimalController getAnimalController() {
        return animalController;
    }

    public AdoptionController getAdoptionController() {
        return adoptionController;
    }

    public VolunteerController getVolunteerController() {
        return volunteerController;
    }

    public AnimalService getAnimalService() {
        return animalService;
    }

    public VolunteerService getVolunteerService() {
        return volunteerService;
    }

    public AdoptionService getAdoptionService() {
        return adoptionService;
    }

    public ShelterService getShelterService() {
        return shelterService;
    }

    public MedicalRecordService getMedicalRecordService() {
        return medicalService;
    }
}
