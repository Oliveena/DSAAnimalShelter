package controllers;

import models.animals.Animal;
import models.*;
import patterns.creational.builders.*;
import services.AnimalService;
import services.ShelterService;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The {@code AnimalController} manages all animal-related interactions
 * within the shelter system, including adding, listing, searching, sorting,
 * and removing animals.
 *
 * <p>It leverages {@link AnimalService}, {@link MedicalRecordController},
 * and {@link ShelterService} to perform these tasks, and uses a background
 * thread pool to handle asynchronous tasks like logging.</p>
 *
 * <p>This controller is designed for a CLI-based interface and relies
 * heavily on user input.</p>
 */
public class AnimalController {
    private final AnimalService animalService;
    private final MedicalRecordController medicalController;
    private final Scanner scanner;
    private final AnimalRegistry registry;
    private final ShelterQueue queue;
    private final ShelterService shelterService;
    private final ExecutorService executor = Executors.newFixedThreadPool(2); // basic threading for background tasks

    /**
     * Constructs a new {@code AnimalController} with the provided dependencies.
     *
     * @param animalService     Service to manage animal storage and limits.
     * @param medicalController Controller for managing medical records.
     * @param scanner           Scanner for user input.
     * @param registry          Animal registry for searching and removal.
     * @param queue             Shelter queue for FIFO adoption.
     * @param shelterService    Core service to manage shelter state.
     */
    public AnimalController(AnimalService animalService,
                            MedicalRecordController medicalController,
                            Scanner scanner,
                            AnimalRegistry registry,
                            ShelterQueue queue,
                            ShelterService shelterService) {
        this.animalService = animalService;
        this.medicalController = medicalController;
        this.scanner = scanner;
        this.registry = registry;
        this.queue = queue;
        this.shelterService = shelterService;
    }

    /**
     * Prompts the user for animal information and adds a new animal to the shelter.
     * Includes medical record creation and queue placement.
     */
    public void addAnimal() {
        if (animalService.isAtCapacity()) {
            System.out.println("Shelter is at full capacity.");
            return;
        }

        System.out.println("\n--- Add New Animal ---");
        String type = promptEnum("Enter type (Dog/Cat/Bird/Lizard): ", List.of("dog", "cat", "bird", "lizard"));
        String name = prompt("Enter name: ");
        int age = promptInt("Enter age (0–30): ", 0, 30);
        String breed = prompt("Enter breed (or 'Unknown'): ");

        Animal animal = switch (type) {
            case "dog" -> new DogBuilder().setName(name).setAge(age).setBreed(breed)
                    .setTrained(promptBoolean("Is the dog trained? (true/false): ")).build();
            case "cat" -> new CatBuilder().setName(name).setAge(age).setBreed(breed)
                    .setFurLength(prompt("Enter fur length: "))
                    .setIndoor(promptBoolean("Indoor cat? (true/false): ")).build();
            case "bird" -> new BirdBuilder().setName(name).setAge(age).setBreed(breed)
                    .setCanFly(promptBoolean("Can the bird fly? (true/false): ")).build();
            case "lizard" -> new LizardBuilder().setName(name).setAge(age).setBreed(breed)
                    .setVenomous(promptBoolean("Is the lizard venomous? (true/false): ")).build();
            default -> null;
        };

        medicalController.collectMedicalRecord(animal);
        shelterService.addAnimal(animal);
        queue.enqueue(animal);

        System.out.println(animal.getSpecies() + " added. Total: " +
                animalService.getAnimalCount() + "/" + animalService.getMaxCapacity());

        executor.submit(() -> {
            System.out.println("[Background Thread] Logging new animal: " + animal.getName());
            try {
                Thread.sleep(500); // simulate delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    /**
     * Lists all animals currently in the shelter, along with their medical records.
     */
    public void listAnimals() {
        System.out.println("\n--- All Animals in Shelter ---");
        List<Animal> animals = animalService.getAllAnimals();

        if (animals.isEmpty()) {
            System.out.println("No animals currently in the shelter.");
            return;
        }

        animals.stream()
                .sorted(Comparator.comparing(Animal::getName, String.CASE_INSENSITIVE_ORDER))
                .map(animal -> {
                    medicalController.displayMedicalRecord(animal);
                    return animal.getDetails();
                })
                .forEach(System.out::println);
    }

    /**
     * Prompts the user to choose a sort method and displays sorted animals.
     */
    public void sortAnimals() {
        System.out.println("\n--- Sort Animals ---");
        System.out.println("1. By Name");
        System.out.println("2. By Age");
        String choice = prompt("Choose sorting option: ");

        List<Animal> animals = new ArrayList<>(registry.getAllAnimals());

        switch (choice) {
            case "1" -> animals.sort(Comparator.comparing(Animal::getName, String.CASE_INSENSITIVE_ORDER));
            case "2" -> animals.sort(Comparator.comparingInt(Animal::getAge));
            default -> {
                System.out.println("Invalid option.");
                return;
            }
        }

        if (animals.isEmpty()) {
            System.out.println("No animals to display.");
        } else {
            animals.forEach(a -> {
                System.out.println(a.getDetails());
                medicalController.displayMedicalRecord(a);
            });
        }
    }

    /**
     * Finds and displays animal details by unique ID.
     */
    public void findAnimalById() {
        System.out.println("\n--- Find Animal by ID ---");
        String id = prompt("Enter the animal ID: ");
        Animal animal = registry.findById(id);
        System.out.println(animal != null
                ? "Found the animal: " + animal.getDetails()
                : "No animal found with ID: " + id);
    }

    /**
     * Searches animals by name (partial match).
     */
    public void findAnimalByName() {
        System.out.println("\n--- Search Animal ---");
        String name = prompt("Enter part of the animal name: ");
        var results = registry.searchByName(name);
        if (results.isEmpty()) {
            System.out.println("No animals found matching: \"" + name + "\"");
        } else {
            System.out.println("Found " + results.size() + " result(s):");
            results.forEach(a -> {
                System.out.println(a.getDetails());
                medicalController.displayMedicalRecord(a);
            });
        }
    }

    /**
     * Filters and displays animals based on species.
     */
    public void findAnimalsBySpecies() {
        System.out.println("\n--- Find Animals by Species ---");
        String species = prompt("Enter species (e.g., Dog, Cat, Bird): ");

        List<Animal> matched = registry.getAllAnimals().stream()
                .filter(animal -> animal.getSpecies().name().equalsIgnoreCase(species))
                .toList();

        if (matched.isEmpty()) {
            System.out.println("No animals found for species: " + species);
        } else {
            matched.forEach(animal -> System.out.println(animal.getDetails()));
        }
    }

    /**
     * Removes an animal from the registry by its ID.
     */
    public void removeAnimal() {
        System.out.println("\n--- Remove Animal ---");

        if (registry.isEmpty()) {
            System.out.println("There are no animals to remove.");
            return;
        }

        String id = prompt("Enter the animal ID to remove: ");
        boolean success = registry.removeAnimalById(id);
        System.out.println(success
                ? "Animal with ID " + id + " was removed."
                : "No animal found with ID: " + id);
    }

    // === INPUT HELPERS ===

    /**
     * Prompts the user with a custom message and returns their input.
     */
    private String prompt(String msg) {
        System.out.print(msg);
        return scanner.nextLine().trim();
    }

    /**
     * Prompts for a boolean value.
     */
    private boolean promptBoolean(String message) {
        while (true) {
            String input = prompt(message);
            if ("true".equalsIgnoreCase(input) || "false".equalsIgnoreCase(input)) {
                return Boolean.parseBoolean(input);
            }
            System.out.println("Enter 'true' or 'false'.");
        }
    }

    /**
     * Prompts the user for an integer within a range.
     */
    private int promptInt(String message, int min, int max) {
        while (true) {
            try {
                int value = Integer.parseInt(prompt(message));
                if (value >= min && value <= max) return value;
            } catch (NumberFormatException ignored) {}
            System.out.printf("Enter a number between %d and %d.%n", min, max);
        }
    }

    /**
     * Prompts the user for an enum-like choice.
     *
     * @param message  The prompt message.
     * @param allowed  Allowed values (case-insensitive).
     * @return A validated input value from the allowed list.
     */
    public String promptEnum(String message, List<String> allowed) {
        while (true) {
            String input = prompt(message);
            for (String option : allowed) {
                if (option.equalsIgnoreCase(input)) {
                    return option;  // return original casing
                }
            }
            System.out.println("Invalid input. Allowed: " + allowed);
        }
    }

    /**
     * Gracefully shuts down the background executor service.
     */
    public void shutdown() {
        executor.shutdown();
    }
}