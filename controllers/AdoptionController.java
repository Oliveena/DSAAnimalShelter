package controllers;

import models.Adopter;
import models.animals.Animal;
import services.AdoptionService;

import java.util.*;

public class AdoptionController {

    private final AdoptionService adoptionService;
    private final Scanner scanner;
    private final List<Animal> adoptedAnimals = new ArrayList<>();
    private final Map<String, Animal> adoptedAnimalMap = new HashMap<>();

    public AdoptionController(AdoptionService adoptionService, Scanner scanner) {
        this.adoptionService = adoptionService;
        this.scanner = scanner;
    }

    // === ADOPTION METHODS ===

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

    private void handleAdoptionResult(Animal adopted) {
        if (adopted != null) {
            adoptAnimal(adopted);
            System.out.println("Adoption successful! You adopted: " + adopted.getDetails());
        } else {
            System.out.println("No animals matched your criteria.");
        }
    }

    public void adoptAnimal(Animal animal) {
        adoptedAnimals.add(animal);
        adoptedAnimalMap.put(animal.getId(), animal);
    }

    public Animal getAdoptedAnimalById(String id) {
        return adoptedAnimalMap.get(id);
    }

    // === VIEWING & QUEUE CONTROL ===

    public void listAnimals() {
        System.out.println("\n--- Animals in Shelter ---");
        List<Animal> animals = adoptionService.getRegistry().getAllAnimals();
        if (animals.isEmpty()) {
            System.out.println("No animals currently in the shelter.");
        } else {
            displayAnimalList(animals);
        }
    }

    public void peekNextAnimal() {
        System.out.println("\n--- Preview Next Animal in Queue ---");
        Animal next = adoptionService.peekNext();
        System.out.println(next != null
                ? "Next animal in queue: " + next.getDetails()
                : "Queue is empty.");
    }

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

    private String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private void displayAnimalList(List<Animal> animals) {
        for (int i = 0; i < animals.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, animals.get(i).getDetails());
        }
    }

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
