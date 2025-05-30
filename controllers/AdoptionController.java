package controllers;

import models.Adopter;
import models.animals.Animal;
import services.AdoptionService;

import java.util.*;

/**
 * Handles adoption-related interactions in the shelter application.
 * <p>
 * This controller provides multiple adoption flows (FIFO, preference-based,
 * manual selection), tracks adopted animals, and supports queue inspection
 * and management. It uses {@link AdoptionService} as the underlying engine.
 * </p>
 *
 * <p>
 * This controller is CLI-oriented and uses a {@link Scanner} for user input.
 * </p>
 */
public class AdoptionController {

    private final AdoptionService adoptionService;
    private final Scanner scanner;
    private final List<Animal> adoptedAnimals = new ArrayList<>();
    private final Map<String, Animal> adoptedAnimalMap = new HashMap<>();

    /**
     * Constructs an AdoptionController.
     *
     * @param adoptionService The service layer for adoption logic.
     * @param scanner         Scanner instance for reading CLI input.
     */
    public AdoptionController(AdoptionService adoptionService, Scanner scanner) {
        this.adoptionService = adoptionService;
        this.scanner = scanner;
    }

    // === ADOPTION METHODS ===

    /**
     * Performs a FIFO-based adoption (Animal of the Month).
     * <p>
     * Adopts the first animal in the queue if available.
     * </p>
     */
    public void adoptAnimalOfTheMonth() {
        System.out.println("\n--- Adopt Animal of the Month (FIFO) ---");

        if (adoptionService.isQueueEmpty()) {
            System.out.println("No animals in the adoption queue.");
            return;
        }

        String adopterName = prompt("Enter adopter's name: ");
        Animal adopted = adoptionService.adoptNextFIFO(adopterName);
        handleAdoptionResult(adopted);
    }

    /**
     * Performs a preference-based adoption by asking the adopter for desired traits.
     */
    public void preferenceBasedAdoption() {
        System.out.println("\n--- Preference-Based Adoption ---");

        String adopterName = prompt("Enter adopter's name: ");
        String species = prompt("Preferred species (dog, cat, lizard, etc.): ");

        Adopter adopter = new Adopter(adopterName);
        adopter.setPreference("species", species);
        // Extend this with more preferences if needed

        Animal adopted = adoptionService.adoptByPreference(adopter);
        handleAdoptionResult(adopted);
    }

    /**
     * Allows manual selection of an animal from the full list of available animals.
     */
    public void adoptAnimalManually() {
        System.out.println("\n--- Manual Animal Selection ---");

        List<Animal> animals = adoptionService.getRegistry().getAllAnimals();
        if (animals.isEmpty()) {
            System.out.println("No animals available for adoption.");
            return;
        }

        displayAnimalList(animals);
        int index = promptAnimalChoice(animals.size());
        Animal selectedAnimal = animals.get(index - 1);
        adoptAnimal(selectedAnimal);
        System.out.println("Adoption successful! You adopted: " + selectedAnimal.getDetails());
    }

    // === UTILITY METHODS ===

    /**
     * Processes the result of an adoption attempt.
     *
     * @param adopted The adopted animal, or null if no match was found.
     */
    private void handleAdoptionResult(Animal adopted) {
        if (adopted != null) {
            adoptAnimal(adopted);
            System.out.println("Adoption successful! You adopted: " + adopted.getDetails());
        } else {
            System.out.println("No animals matched your criteria.");
        }
    }

    /**
     * Records an adopted animal into the controller's internal records.
     *
     * @param animal The animal to mark as adopted.
     */
    public void adoptAnimal(Animal animal) {
        adoptedAnimals.add(animal);
        adoptedAnimalMap.put(animal.getId(), animal);
    }

    /**
     * Retrieves a previously adopted animal by its ID.
     *
     * @param id The unique ID of the animal.
     * @return The adopted animal or null if not found.
     */
    public Animal getAdoptedAnimalById(String id) {
        return adoptedAnimalMap.get(id);
    }

    // === VIEWING & QUEUE CONTROL ===

    /**
     * Displays all currently available animals in the shelter.
     */
    public void listAnimals() {
        System.out.println("\n--- Animals in Shelter ---");
        List<Animal> animals = adoptionService.getRegistry().getAllAnimals();
        if (animals.isEmpty()) {
            System.out.println("No animals currently in the shelter.");
        } else {
            displayAnimalList(animals);
        }
    }

    /**
     * Displays the next animal in the adoption queue without removing it.
     */
    public void peekNextAnimal() {
        System.out.println("\n--- Preview Next Animal in Queue ---");
        Animal next = adoptionService.peekNext();
        System.out.println(next != null
                ? "Next animal in queue: " + next.getDetails()
                : "Queue is empty.");
    }

    /**
     * Clears the entire adoption queue.
     */
    public void clearQueue() {
        System.out.println("\n--- Clear Adoption Queue ---");
        if (adoptionService.isQueueEmpty()) {
            System.out.println("Adoption queue is already empty.");
        } else {
            adoptionService.clearQueue();
            System.out.println("Adoption queue cleared.");
        }
    }

    // === INPUT HELPERS ===

    /**
     * Prompts the user for input via CLI.
     *
     * @param message The prompt message.
     * @return Trimmed user input.
     */
    private String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    /**
     * Displays a numbered list of animals.
     *
     * @param animals List of animals to display.
     */
    private void displayAnimalList(List<Animal> animals) {
        for (int i = 0; i < animals.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, animals.get(i).getDetails());
        }
    }

    /**
     * Prompts the user to choose an animal from a list.
     *
     * @param max The number of animals in the list.
     * @return The index of the selected animal (1-based).
     */
    private int promptAnimalChoice(int max) {
        int choice = -1;
        while (choice < 1 || choice > max) {
            System.out.printf("Select animal to adopt (1 - %d): ", max);
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number.");
            }
        }
        return choice;
    }
}