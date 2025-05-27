package services;

import models.*;
import patterns.builders.*;

import java.util.*;

public class AnimalService {
    private final AnimalRegistry registry;
    private final ShelterQueue queue;
    private final Shelter shelter;
    private Scanner scanner;

    private Map<Animal, List<String>> vaccinationRecords = new HashMap<>();

    public AnimalService(AnimalRegistry registry, ShelterQueue queue, Shelter shelter, Scanner scanner) {
        this.registry = registry;
        this.queue = queue;
        this.shelter = shelter;
        this.scanner = scanner;

    }

    /**
     * Adds a new animal to the shelter by prompting the user for input.
     * Validates the input and adds the animal to both the registry and adoption queue.
     */
    public void addAnimal() {

        if (registry.isAtCapacity()) {
            System.out.println("Shelter is at full capacity (" + registry.getMaxCapacity() + "). Cannot add more animals.");
            return;
        }

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

            animal = new CatBuilder()
                    .setName(name)
                    .setAge(age)
                    .setFurLength(fur)
                    .setIndoor(indoor)
                    .build();
        }
        // using MedicalRecordBuilder
        MedicalRecordBuilder medicalRecordBuilder = new MedicalRecordBuilder();

        System.out.println("Enter vaccinations (type 'done' to finish):");
        while (true) {
            String input = scanner.nextLine().trim();
            if ("done".equalsIgnoreCase(input)) break;
            medicalRecordBuilder.addVaccination(input);
        }

        System.out.println("Enter treatments (type 'done' to finish):");
        while (true) {
            String input = scanner.nextLine().trim();
            if ("done".equalsIgnoreCase(input)) break;
            medicalRecordBuilder.addTreatment(input);
        }

        System.out.println("Enter checkups (type 'done' to finish):");
        while (true) {
            String input = scanner.nextLine().trim();
            if ("done".equalsIgnoreCase(input)) break;
            medicalRecordBuilder.addCheckup(input);
        }

        MedicalRecord medicalRecord = medicalRecordBuilder.build();

        // Assuming your Animal class has a setter for MedicalRecord
        animal.setMedicalRecord(medicalRecord);

        try {
            registry.addAnimal(animal);
            queue.addAnimal(animal);
            shelter.addAnimal(animal);

            int count = registry.getAnimalCount();
            System.out.println(animal.getType() + " added. Current occupancy: " + count + "/" + registry.getMaxCapacity());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds a new animal to the shelter by prompting the user for input.
     * Validates the input and adds the animal to both the registry and adoption queue.
     */
    public void listAnimals() {
        System.out.println("\n--- List of All Animals in Shelter ---");

        var animals = registry.getAllAnimals();
        if (animals.isEmpty()) {
            System.out.println("No animals currently in the shelter.");
            return;
        }

        for (Animal animal : animals) {
            System.out.println(animal.getDetails());

            // Show MedicalRecord details if present
            MedicalRecord record = animal.getMedicalRecord();
            if (record != null) {
                System.out.println("Medical Record:");

                if (!record.getVaccinations().isEmpty()) {
                    System.out.println("  Vaccinations: " + String.join(", ", record.getVaccinations()));
                } else {
                    System.out.println("  Vaccinations: None");
                }

                if (!record.getTreatments().isEmpty()) {
                    System.out.println("  Treatments: " + String.join(", ", record.getTreatments()));
                } else {
                    System.out.println("  Treatments: None");
                }

                if (!record.getCheckups().isEmpty()) {
                    System.out.println("  Checkups: " + String.join(", ", record.getCheckups()));
                } else {
                    System.out.println("  Checkups: None");
                }
            } else {
                System.out.println("No medical record available.");
            }

            System.out.println();
        }
    }


    /**
     * Finds and displays an animal based on its ID.
     * Displays details of the animal if found.
     */
    public void findAnimalById() {
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

    /**
     * Removes an animal from the shelter based on the animal's ID.
     * If no animal is found with the provided ID, an appropriate message is displayed.
     */
    public void removeAnimal() {
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



    /**
     * Searches for animals by a partial name and displays the search results.
     * Includes vaccination records if they exist.
     */
    public void searchAnimal() {
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
                // Display vaccination records if they exist
                if (vaccinationRecords.containsKey(a)) {
                    System.out.println("Vaccination Records: " + vaccinationRecords.get(a));
                }
            }
        }
    }

    /**
     * Sorts the animals based on user-selected criteria, such as name or age.
     * Displays the sorted list of animals.
     */
    public void sortAnimals() {
        System.out.println("\n--- Sort Animals ---");
        System.out.println("1. By Name");
        System.out.println("2. By Age");
        System.out.print("Choose sorting option: ");
        String choice = scanner.nextLine().trim();

        List<Animal> animals = new ArrayList<>(registry.getAllAnimals());

        switch (choice) {
            case "1":
                animals.sort(Comparator.comparing(Animal::getName, String.CASE_INSENSITIVE_ORDER));
                break;
            case "2":
                animals.sort(Comparator.comparingInt(Animal::getAge));
                break;
            default:
                System.out.println("Invalid option.");
                return;
        }

        if (animals.isEmpty()) {
            System.out.println("No animals to display.");
        } else {
            for (Animal a : animals) {
                System.out.println(a.getDetails());
                if (vaccinationRecords.containsKey(a)) {
                    System.out.println("Vaccination Records: " + vaccinationRecords.get(a));
                }
            }
        }
    }

    /**
     * Method to add animals from UI without using the scanner.
     * @param name
     * @param age
     * @param type
     * @param extras
     */
    public void addAnimalFromUI(String name, int age, String type, Map<String, String> extras) {
        Animal animal;

        if (type.equalsIgnoreCase("dog")) {
            animal = new DogBuilder()
                    .setName(name)
                    .setAge(age)
                    .setBreed(extras.getOrDefault("breed", "Unknown"))
                    .setTrained(Boolean.parseBoolean(extras.getOrDefault("trained", "false")))
                    .build();
        } else if (type.equalsIgnoreCase("cat")) {
            animal = new CatBuilder()
                    .setName(name)
                    .setAge(age)
                    .setFurLength(extras.getOrDefault("furLength", "short"))
                    .setIndoor(Boolean.parseBoolean(extras.getOrDefault("indoor", "false")))
                    .build();
        } else {
            throw new IllegalArgumentException("Invalid animal type: " + type);
        }

        registry.addAnimal(animal);
        queue.addAnimal(animal);
        shelter.addAnimal(animal);

        System.out.println(animal.getType() + " added from UI. Total in shelter: " + registry.getAnimalCount());
    }

}
