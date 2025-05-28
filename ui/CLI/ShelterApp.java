package ui.CLI;

import java.util.*;

import controllers.*;
import models.*;
import models.animals.Animal;
import patterns.creational.factories.LogFactory;
import patterns.creational.factories.FormFactory;
import patterns.behavioral.observer.VolunteerManager;
import patterns.behavioral.strategies.AdoptionStrategy;
import patterns.behavioral.strategies.FIFOAdoptionStrategy;
import services.*;
import ui.CLI.menus.AdminMenu;
import ui.CLI.menus.VetMenu;
import ui.CLI.menus.VolunteerMenu;

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
    private final AdoptionController adoptionController = new AdoptionController(adoptionService, scanner);

    private final AdminController adminController = new AdminController(animalController, adoptionController, shelterService);
    private final VetController vetController = new VetController(registry, medicalService, scanner);
    private final VolunteerController volunteerController = new VolunteerController(volunteerService, scanner);

    // === Logging / State ===
    private static final Logger logger = LogFactory.getLogger(ShelterApp.class);
    private final Map<Animal, List<String>> vaccinationRecords = new HashMap<>();

    // === Private Constructor ===
    public ShelterApp() {}

    // === CLI Start Method ===
    public void start() {
        Map<String, MenuOption> mainMenu = new LinkedHashMap<>();

        mainMenu.put("1", new MenuOption("Admin Menu", this::showAdminMenu));
        mainMenu.put("2", new MenuOption("Volunteer Menu", this::showVolunteerMenu));
        mainMenu.put("3", new MenuOption("Veterinarian Menu", this::showVetMenu));
        mainMenu.put("0", new MenuOption("Exit", () -> System.exit(0)));

        runMenu("=== Animal Shelter System ===", mainMenu);
    }

    // === CLI Menu Methods ===

    private void runMenu(String title, Map<String, MenuOption> menu) {
        while (true) {
            System.out.println("\n" + title);
            for (Map.Entry<String, MenuOption> entry : menu.entrySet()) {
                System.out.printf("%s. %s%n", entry.getKey(), entry.getValue().getDescription());
            }
            System.out.print("Select an option: ");
            String choice = scanner.nextLine().trim();
            MenuOption option = menu.get(choice);
            if (option != null) {
                option.execute();
                if (option.getDescription().equalsIgnoreCase("Return to Main Menu")) break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void showAdminMenu() {
        AdminMenu adminMenu = new AdminMenu(adminController);
        runMenu("🐾 Admin Menu", adminMenu.getMenu());
    }

    private void showVolunteerMenu() {
        VolunteerMenu volunteerMenu = new VolunteerMenu(volunteerController);
        runMenu("Volunteer Menu", volunteerMenu.getMenu());
    }

    private void showVetMenu() {
        VetMenu vetMenu = new VetMenu(vetController);
        runMenu("Vet Menu", vetMenu.getMenu());
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
