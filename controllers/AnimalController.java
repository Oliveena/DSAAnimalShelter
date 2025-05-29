package controllers;

import models.animals.Animal;
import models.*;
import models.animals.Dog;
import patterns.creational.builders.*;
import services.AnimalService;
import services.ShelterService;

import java.util.*;

public class AnimalController {
    private AnimalService animalService;
    private MedicalRecordController medicalController;
    private Scanner scanner;
    private AnimalRegistry registry ;
    private ShelterQueue queue;
    private ShelterService shelterService;

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
    }

    public void listAnimals() {
        System.out.println("\n--- All Animals in Shelter ---");
        List<Animal> animals = animalService.getAllAnimals();

        if (animals.isEmpty()) {
            System.out.println("No animals currently in the shelter.");
            return;
        }

        for (Animal animal : animals) {
            System.out.println(animal.getDetails());
            medicalController.displayMedicalRecord(animal);
            System.out.println();
        }
    }

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

    public void findAnimalById() {
        System.out.println("\n--- Find Animal by ID ---");
        String id = prompt("Enter the animal ID: ");
        Animal animal = registry.findById(id);
        System.out.println(animal != null
                ? "Found the animal: " + animal.getDetails()
                : "No animal found with ID: " + id);
    }

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

    // === PROMPT HELPERS ===

    private String prompt(String msg) {
        System.out.print(msg);
        return scanner.nextLine().trim();
    }

    private boolean promptBoolean(String message) {
        while (true) {
            String input = prompt(message);
            if ("true".equalsIgnoreCase(input) || "false".equalsIgnoreCase(input)) {
                return Boolean.parseBoolean(input);
            }
            System.out.println("Enter 'true' or 'false'.");
        }
    }

    private int promptInt(String message, int min, int max) {
        while (true) {
            try {
                int value = Integer.parseInt(prompt(message));
                if (value >= min && value <= max) return value;
            } catch (NumberFormatException ignored) {}
            System.out.printf("Enter a number between %d and %d.%n", min, max);
        }
    }

    private String promptEnum(String message, List<String> allowed) {
        while (true) {
            String input = prompt(message).toLowerCase();
            if (allowed.contains(input)) return input;
            System.out.println("Allowed: " + allowed);
        }
    }

    public void addAnimalDirectly(Animal animal) {
        animalService.addAnimal(animal);
    }




}
