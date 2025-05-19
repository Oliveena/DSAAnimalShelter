package ui;

import java.util.Scanner;

import builders.DogBuilder;
import logic.Animal;
import logic.ShelterQueue;
import data.AnimalRegistry;
import strategies.AdoptionStrategy;
import strategies.FIFOAdoptionStrategy;


public class ShelterApp {
    private AdoptionStrategy adoptionStrategy = new FIFOAdoptionStrategy();
    private AnimalRegistry registry = new AnimalRegistry();
    private ShelterQueue queue = new ShelterQueue();
    private Scanner scanner = new Scanner(System.in);


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

        // Register and queue
        if (animal != null) {
            registry.addAnimal(animal);
            queue.enqueue(animal);
            System.out.println(animal.getType() + " added to the shelter and adoption queue.");
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
        }
    }

    private void searchAnimal() {
        System.out.println("\n--- Search Animal ---");
        System.out.print("Enter part of the animal name: ");
        String name = scanner.nextLine().trim();

        var results = registry.searchByName(name);
        if (results.isEmpty()) {
            System.out.println("No animals found matching: \"" + name + "\"");
        } else {
            System.out.println("Found " + results.size() + " result(s):");
            for (Animal a : results) {
                System.out.println(a.getDetails());
            }
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


    private void adoptAnimal() {
        System.out.println("\n--- Adopt Next Animal (FIFO) ---");

        if (queue.isEmpty()) {
            System.out.println("No animals in the adoption queue.");
            return;
        }

        Animal adopted = adoptionStrategy.adopt(registry, queue);
        if (adopted != null) {
            System.out.println("Adoption successful! Here's your new companion: " + adopted.getDetails());
        } else {
            System.out.println("Adoption failed — no animals available for adoption.");
        }
    }
}
