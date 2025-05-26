package ui.CLI;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

import models.*;
import models.animals.Animal;
import patterns.builders.*;
import patterns.factories.LogFactory;
import patterns.factories.FormFactory;
import patterns.observer.VolunteerManager;
import patterns.observer.VolunteerObserver;
import patterns.strategies.AdoptionStrategy;
import patterns.strategies.FIFOAdoptionStrategy;
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
        private final AdoptionStrategy adoptionStrategy = new FIFOAdoptionStrategy();
        private final AnimalRegistry registry = new AnimalRegistry();
        private final VolunteerManager volunteerManager = new VolunteerManager();
        private final ShelterService shelter = new ShelterService(registry, volunteerManager);
        private final Scanner scanner = new Scanner(System.in);
        private final ShelterQueue queue = new ShelterQueue();

        // logging
    private static final Logger logger = LogFactory.getLogger(ShelterApp.class);

        // storing vaccination records
    private Map<Animal, List<String>> vaccinationRecords = new HashMap<>();
    private final FormFactory formFactory = new FormFactory();

        public void start() {
            boolean exit = false;
            while (!exit) {
                ui.CLI.ShelterMenu.displayMenu();
                String choice = scanner.nextLine().trim();
                switch (choice) {
                    case "1" -> addAnimal();
                    case "2" -> listAnimals();
                    case "3" -> adoptAnimal();
                    case "4" -> findAnimalByName();
                    case "5" -> removeAnimal();
                    case "6" -> peekNextAnimal();
                    case "7" -> clearQueue();
                    case "8" -> findAnimalById();
                    case "9" -> findAnimalsBySpecies();
                    case "10" -> sortAnimals();
                    case "11" -> registerVolunteer();
                    case "12" -> addTask();
                    case "0" -> exit = true;
                    default -> System.out.println("Invalid choice.");
                }
            }
        }

        private void addAnimal() {
            if (registry.isAtCapacity()) {
                System.out.println("Shelter is at full capacity. Cannot add more animals.");
                return;
            }

            System.out.println("\n--- Add New Animal ---");

            String type = promptEnum("Enter type (Dog/Cat/Bird/Lizard): ", List.of("dog", "cat", "bird", "lizard"));
            String name = prompt("Enter animal's name: ");
            int age = promptInt("Enter age (0–30): ", 0, 30);
            String breed = prompt("Enter breed (or 'Unknown'): ");

            Animal animal = switch (type) {
                case "dog" -> new DogBuilder()
                        .setName(name)
                        .setAge(age)
                        .setBreed(breed)
                        .setTrained(promptBoolean("Is the dog trained? (true/false): "))
                        .build();
                case "cat" -> new CatBuilder()
                        .setName(name)
                        .setAge(age)
                        .setBreed(breed)
                        .setFurLength(prompt("Enter fur length (e.g. short, long): "))
                        .setIndoor(promptBoolean("Is the cat an indoor cat? (true/false): "))
                        .build();
                case "bird" -> new BirdBuilder()
                        .setName(name)
                        .setAge(age)
                        .setBreed(breed)
                        .setCanFly(promptBoolean("Can the bird fly? (true/false): "))
                        .build();
                case "lizard" -> new LizardBuilder()
                        .setName(name)
                        .setAge(age)
                        .setBreed(breed)
                        .setVenomous(promptBoolean("Is the lizard venomous? (true/false): "))
                        .build();
                default -> null;
            };

            MedicalRecordBuilder mrBuilder = new MedicalRecordBuilder();
            System.out.println("Enter vaccinations (type 'done' to finish):");
            collectListEntries(mrBuilder::addVaccination);
            System.out.println("Enter treatments (type 'done' to finish):");
            collectListEntries(mrBuilder::addTreatment);
            System.out.println("Enter checkups (type 'done' to finish):");
            collectListEntries(mrBuilder::addCheckup);

            animal.setMedicalRecord(mrBuilder.build());

            try {
                registry.addAnimal(animal);
                queue.addAnimal(animal);
                shelter.addAnimal(animal);
                System.out.println(STR."\{animal.getSpecies()} added. Current occupancy: \{registry.getAnimalCount()}/\{registry.getMaxCapacity()}");
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
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
                displayMedicalRecord(animal);
                System.out.println();
            }
        }

        private void sortAnimals() {
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
                    displayMedicalRecord(a);
                });
            }
        }

        private void findAnimalById() {
            System.out.println("\n--- Find Animal by ID ---");
            String id = prompt("Enter the animal ID: ");
            Animal animal = registry.findById(id);
            System.out.println(animal != null
                    ? STR."Found the animal: \{animal.getDetails()}"
                    : STR."No animal found with ID: \{id}");
        }

        private void findAnimalByName() {
            System.out.println("\n--- Search Animal ---");
            String name = prompt("Enter part of the animal name: ");
            var results = registry.searchByName(name);
            if (results.isEmpty()) {
                System.out.println(STR."No animals found matching: \"\{name}\"");
            } else {
                System.out.println(STR."Found \{results.size()} result(s):");
                results.forEach(a -> {
                    System.out.println(a.getDetails());
                    displayMedicalRecord(a);
                });
            }
        }

        private void findAnimalsBySpecies() {
            System.out.println("\n--- Find Animals by Species ---");
            String species = prompt("Enter species (e.g., Dog, Cat, Bird): ");

            List<Animal> matched = registry.getAllAnimals().stream()
                    .filter(animal -> animal.getSpecies().equalsIgnoreCase(species))
                    .toList();

            if (matched.isEmpty()) {
                System.out.println(STR."No animals found for species: \{species}");
            } else {
                matched.forEach(animal -> System.out.println(animal.getDetails()));
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
                Adopter adopter = new Adopter(prompt("Enter adopter's name: "));
                AdoptionForm form = formFactory.createAdoptionForm(adopter, adopted, LocalDate.now());
                form.submit();

                System.out.println(STR."Adoption successful! Here's your new companion: \{adopted.getDetails()}");
            } else {
                System.out.println("Adoption failed — no animals available for adoption.");
            }
        }

        private void peekNextAnimal() {
            System.out.println("\n--- Preview Next Animal in Adoption Queue ---");
            Animal next = queue.peekNext();
            System.out.println(next != null
                    ? STR."Next animal in the queue: \{next.getDetails()}"
                    : "No animals in the adoption queue.");
        }

        private void removeAnimal() {
            System.out.println("\n--- Remove Animal ---");

            if (registry.isEmpty()) {
                System.out.println("There are no animals to remove.");
                return;
            }

            String id = prompt("Enter the animal ID to remove: ");
            boolean success = registry.removeAnimalById(id);
            System.out.println(success
                    ? STR."Animal with ID \{id} was removed."
                    : STR."No animal found with ID: \{id}");
        }

        private void clearQueue() {
            System.out.println("\n--- Clear Adoption Queue ---");
            if (queue.isEmpty()) {
                System.out.println("The adoption queue is already empty.");
            } else {
                queue.clear();
                System.out.println("The adoption queue has been cleared.");
            }
        }

        private void registerVolunteer() {
            System.out.println("\n--- Register a Volunteer ---");
            String name = prompt("Enter volunteer name: ");
            shelter.registerVolunteer(new Volunteer(name));
            System.out.println(STR."Volunteer '\{name}' registered.");
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

        private void displayMedicalRecord(Animal animal) {
            MedicalRecord record = animal.getMedicalRecord();
            if (record == null) {
                System.out.println("No medical record available.");
                return;
            }

            System.out.println("Medical Record:");
            System.out.println(STR."  Vaccinations: \{formatList(record.getVaccinations())}");
            System.out.println(STR."  Treatments: \{formatList(record.getTreatments())}");
            System.out.println(STR."  Checkups: \{formatList(record.getCheckups())}");
        }

        private String formatList(List<String> items) {
            return items.isEmpty() ? "None" : String.join(", ", items);
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
                        System.out.println(STR."Value must be greater than \{min}");
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
                System.out.println(STR."Invalid input. Allowed values: \{allowed}");
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
