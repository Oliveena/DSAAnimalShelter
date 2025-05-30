package ui.CLI;

import java.util.*;

import controllers.*;
import models.*;
import models.animals.Animal;
import patterns.creational.factories.LogFactory;
import patterns.creational.factories.FormFactory;
import patterns.behavioral.observer.VolunteerManager;
import services.*;
import ui.CLI.menus.AdminMenu;
import ui.CLI.menus.VetMenu;
import ui.CLI.menus.VolunteerMenu;

import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * The main CLI application for managing the animal shelter system.
 * <p>
 * Provides functionality for adding, listing, sorting, and adopting animals via
 * an interactive console interface. This class serves as the central controller
 * for coordinating all services and controllers in the system.
 */
public class ShelterApp {

    private static ShelterApp instance;

    /**
     * Returns the singleton instance of the ShelterApp.
     *
     * @return ShelterApp instance
     */
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

    private final ShelterService shelterService = new ShelterService(registry, volunteerManager);
    private final AnimalService animalService = new AnimalService(registry);
    private final MedicalRecordService medicalService = new MedicalRecordService();
    private final VolunteerService volunteerService = new VolunteerService(volunteerManager, shelterService);
    private final AdoptionService adoptionService = new AdoptionService(queue, registry, formFactory);

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

    /**
     * Private constructor to enforce singleton pattern.
     */
    public ShelterApp() {}

    /**
     * Starts the command-line interface and displays the main menu.
     */
    public void start() {
        Map<String, MenuOption> mainMenu = new LinkedHashMap<>();

        mainMenu.put("1", new MenuOption("Admin Menu", this::showAdminMenu));
        mainMenu.put("2", new MenuOption("Volunteer Menu", this::showVolunteerMenu));
        mainMenu.put("3", new MenuOption("Veterinarian Menu", this::showVetMenu));
        mainMenu.put("0", new MenuOption("Exit", () -> System.exit(0)));

        runMenu("=== Animal Shelter System ===", mainMenu);
    }

    /**
     * Displays and handles interaction for a given menu.
     *
     * @param title the title to display above the menu
     * @param menu  the menu options
     */
    private void runMenu(String title, Map<String, MenuOption> menu) {
        while (true) {
            System.out.println("\n" + title);
            for (Map.Entry<String, MenuOption> entry : menu.entrySet()) {
                System.out.printf("%s. %s%n", entry.getKey(), entry.getValue().getDescription());
            }
            System.out.print("Select an option: ");
            String choice = scanner.nextLine().trim();

            MenuOption option = menu.entrySet().stream()
                    .filter(e -> e.getKey().equalsIgnoreCase(choice))
                    .map(Map.Entry::getValue)
                    .findFirst()
                    .orElse(null);

            if (option != null) {
                option.execute();
                if (option.getDescription().equalsIgnoreCase("Return to Main Menu")) break;
            } else {
                System.out.println("Invalid choice '" + choice + "'. Please try again.");
            }
        }
    }

    /** Displays the Admin menu. */
    private void showAdminMenu() {
        AdminMenu adminMenu = new AdminMenu(adminController, adoptionController);
        runMenu("🐾 Admin Menu", adminMenu.getMenu());
    }

    /** Displays the Volunteer menu. */
    private void showVolunteerMenu() {
        VolunteerMenu volunteerMenu = new VolunteerMenu(volunteerController);
        runMenu("Volunteer Menu", volunteerMenu.getMenu());
    }

    /** Displays the Veterinarian menu. */
    private void showVetMenu() {
        VetMenu vetMenu = new VetMenu(vetController);
        runMenu("Vet Menu", vetMenu.getMenu());
    }

    // === CLI Prompt Helpers ===

    /**
     * Prompts the user for input with a custom message.
     *
     * @param message the message to display
     * @return user input as a trimmed string
     */
    public String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    /**
     * Prompts the user for a boolean value.
     *
     * @param message the message to display
     * @return the boolean result
     */
    public boolean promptBoolean(String message) {
        while (true) {
            String input = prompt(message);
            if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(input);
            }
            System.out.println("Please enter 'true' or 'false'.");
        }
    }

    /**
     * Prompts the user for an integer between min and max.
     */
    public int promptInt(String message, int min, int max) {
        while (true) {
            try {
                int value = Integer.parseInt(prompt(message));
                if (value >= min && value <= max) return value;
            } catch (NumberFormatException ignored) {}
            System.out.printf("Please enter a value between %d and %d.%n", min, max);
        }
    }

    /**
     * Prompts the user for a double greater than a minimum value.
     */
    public double promptDouble(String message, double min) {
        while (true) {
            try {
                double value = Double.parseDouble(prompt(message));
                if (value >= min) return value;
            } catch (NumberFormatException ignored) {}
            System.out.printf("Please enter a number greater than %.2f%n", min);
        }
    }

    /**
     * Prompts the user to enter a value that matches one of the allowed strings.
     */
    public String promptEnum(String message, List<String> allowed) {
        while (true) {
            String input = prompt(message).toLowerCase();
            if (allowed.contains(input)) return input;
            System.out.println("Invalid input. Allowed: " + allowed);
        }
    }

    /**
     * Continuously collects input lines until "done" is entered.
     *
     * @param consumer consumer to handle each input line
     */
    public void collectListEntries(Consumer<String> consumer) {
        while (true) {
            String input = scanner.nextLine().trim();
            if ("done".equalsIgnoreCase(input)) break;
            consumer.accept(input);
        }
    }

    /**
     * Starts the application silently (without showing any menus).
     * Useful for performance testing or background initialization.
     */
    public void startSilently() {
        System.out.println("Initializing components...");
    }

    // === Accessors for Controllers and Services ===

    public AnimalController getAnimalController() { return animalController; }

    public AdoptionController getAdoptionController() { return adoptionController; }

    public VolunteerController getVolunteerController() { return volunteerController; }

    public AnimalService getAnimalService() { return animalService; }

    public VolunteerService getVolunteerService() { return volunteerService; }

    public AdoptionService getAdoptionService() { return adoptionService; }

    public ShelterService getShelterService() { return shelterService; }

    public MedicalRecordService getMedicalRecordService() { return medicalService; }
}
