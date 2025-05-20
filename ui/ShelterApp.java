package ui;

import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import builders.DogBuilder;

import logic.Animal;
import data.AnimalRegistry;
import logic.ShelterQueue;
import strategies.AdoptionStrategy;
import strategies.FIFOAdoptionStrategy;

public class ShelterApp {
    private AdoptionStrategy adoptionStrategy = new FIFOAdoptionStrategy();
    private AnimalRegistry registry = new AnimalRegistry();
    private Scanner scanner = new Scanner(System.in);
    private ShelterQueue queue = new ShelterQueue();

    // Store vaccination records
    private Map<Animal, List<String>> vaccinationRecords = new HashMap<>();

    public void start() {
        boolean exit = false;
        while (!exit) {
            ShelterMenu.displayMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1": addAnimal(); break;
                case "2": listAnimals(); break;
                case "3": adoptAnimal(); break;
                case "4": searchAnimal(); break;
                case "5": removeAnimal(); break;
                case "6": peekNextAnimal(); break;
                case "7": clearQueue(); break;
                case "8": findAnimalById(); break;
                case "0": exit = true; break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private void addAnimal() {
        System.out.println("\n--- Add New Animal ---");

        String type;
        while (true) {
            System.out.print("Enter type (Dog/Cat): ");
            type = scanner.nextLine().trim();
            if (type.equalsIgnoreCase("dog") || type.equalsIgnoreCase("cat")) {
                break;
            }
            System.out.println("Invalid type. Please enter 'Dog' or 'Cat'.");
        }

        System.out.print("Enter animal's name: ");
        String name = scanner.nextLine().trim();

        int age = -1;
        while (true) {
            try {
                System.out.print("Enter age (whole number): ");
                age = Integer.parseInt(scanner.nextLine().trim());
                if (age < 0 || age > 30) {
                    System.out.println("Please enter a realistic age (0–30).");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }

        Animal animal = null;

        if (type.equalsIgnoreCase("dog")) {
            System.out.print("Enter breed: ");
            String breed = scanner.nextLine().trim();

            boolean trained = false;
            while (true) {
                System.out.print("Is the dog trained? (true/false): ");
                String trainedInput = scanner.nextLine().trim();
                if (trainedInput.equalsIgnoreCase("true") || trainedInput.equalsIgnoreCase("false")) {
                    trained = Boolean.parseBoolean(trainedInput);
                    break;
                }
                System.out.println("Please enter true or false.");
            }

            animal = new DogBuilder()
                    .setName(name)
                    .setAge(age)
                    .setBreed(breed)
                    .setTrained(trained)
                    .build();
        } else if (type.equalsIgnoreCase("cat")) {
            System.out.print("Enter fur length (e.g. short, long): ");
            String fur = scanner.nextLine().trim();

            boolean indoor = false;
            while (true) {
                System.out.print("Is the cat an indoor cat? (true/false): ");
                String indoorInput = scanner.nextLine().trim();
                if (indoorInput.equalsIgnoreCase("true") || indoorInput.equalsIgnoreCase("false")) {
                    indoor = Boolean.parseBoolean(indoorInput);
                    break;
                }
                System.out.println("Please enter true or false.");
            }

            animal = new builders.CatBuilder()
                    .setName(name)
                    .setAge(age)
                    .setFurLength(fur)
                    .setIndoor(indoor)
                    .build();
        }

        // Add vaccination record
        System.out.print("Has the animal been vaccinated? (yes/no): ");
        String vaccinatedInput = scanner.nextLine().trim();
        if (vaccinatedInput.equalsIgnoreCase("yes")) {
            List<String> vaccinationDates = new ArrayList<>();
            boolean addingVaccinationDates = true;

            while (addingVaccinationDates) {
                System.out.print("Enter vaccination date (YYYY-MM-DD) or type 'done' to finish: ");
                String date = scanner.nextLine().trim();
                if (date.equalsIgnoreCase("done")) {
                    addingVaccinationDates = false;
                } else {
                    vaccinationDates.add(date);
                }
            }
            // Store the vaccination records for this animal
            vaccinationRecords.put(animal, vaccinationDates);
        }

        // Register and queue
        if (animal != null) {
            registry.addAnimal(animal);
            queue.addAnimal(animal);
            System.out.println(animal.getType() + " added to the shelter.");
        } else {
            System.out.println("Failed to create animal.");
        }
    }

    private void listAnimals() {
        System.out.println("\n--- List of All Animals in Shelter ---");

        var animals = registry.getAllAnimals();
        if (animals.isEmpty()) {
            System.out.println("No animals currently in the shelter.");
            return;
        }

        for (Animal animal : animals) {
            System.out.println(animal.getDetails());

            // Display vaccination records if they exist
            if (vaccinationRecords.containsKey(animal)) {
                System.out.println("Vaccination Records: " + vaccinationRecords.get(animal));
            }
        }
    }

    /**
     * Find and display animal by its ID
     */
    private void findAnimalById() {
        System.out.println("\n--- Find Animal by ID ---");
        System.out.print("Enter the animal ID: ");
        String id = scanner.nextLine().trim();

        // Use the findById method in AnimalRegistry
        Animal animal = registry.findById(id);
        if (animal != null) {
            System.out.println("Found the animal: " + animal.getDetails());
        } else {
            System.out.println("No animal found with ID: " + id);
        }
    }

    private void removeAnimal() {
        System.out.println("\n--- Remove Animal ---");

        if (registry.isEmpty()) {
            System.out.println("There are no animals to remove.");
            return;
        }

        System.out.print("Enter the animal ID to remove: ");
        String id = scanner.nextLine().trim();

        boolean success = registry.removeAnimalById(id);
        if (success) {
            System.out.println("Animal with ID " + id + " was removed.");
        } else {
            System.out.println("No animal found with ID: " + id);
        }
    }

    private void peekNextAnimal() {
        System.out.println("\n--- Preview Next Animal in Adoption Queue ---");

        Animal nextAnimal = queue.peekNext();
        if (nextAnimal == null) {
            System.out.println("No animals in the adoption queue.");
        } else {
            System.out.println("Next animal in the queue: " + nextAnimal.getDetails());
        }
    }

    /**
     * Clear the adoption queue (remove all animals from the queue).
     */
    private void clearQueue() {
        System.out.println("\n--- Clear Adoption Queue ---");

        if (queue.isEmpty()) {
            System.out.println("The adoption queue is already empty.");
        } else {
            queue.clear(); // This will clear all animals from the queue
            System.out.println("The adoption queue has been cleared.");
        }
    }



    private void adoptAnimal() {
        System.out.println("\n--- Adopt Next Animal (FIFO) ---");

        if (queue.isEmpty()) {
            System.out.println("No animals in the adoption queue.");
            return;
        }

        Animal adopted = adoptionStrategy.adopt(registry, queue);  // pass both registry and queue
        if (adopted != null) {
            System.out.println("Adoption successful! Here's your new companion: " + adopted.getDetails());
        } else {
            System.out.println("Adoption failed — no animals available for adoption.");
        }
    }


    private void searchAnimal() {
        System.out.println("\n--- Search Animal ---");
        // Search logic remains the same
    }
}
