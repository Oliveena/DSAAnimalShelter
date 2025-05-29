package controllers;

import models.Adopter;
import models.animals.Animal;
import services.AdoptionService;

import java.util.*;

public class AdoptionController {

    private final AdoptionService adoptionService;
    private final Scanner scanner;

    public AdoptionController(AdoptionService adoptionService, Scanner scanner) {
        this.adoptionService = adoptionService;
        this.scanner = scanner;
    }

    // keeping track of adopted animals
    private final List<Animal> adoptedAnimals = new ArrayList<>();


    public void adoptAnimalOfTheMonth() {
        System.out.println("\n--- Adopt Animal of the Month (FIFO) ---");

        if (adoptionService.isQueueEmpty()) {
            System.out.println("No animals in the adoption queue.");
            return;
        }

        String adopterName = prompt("Enter adopter's name: ");
        Animal adopted = adoptionService.adoptNextFIFO(adopterName);

        if (adopted != null) {
            System.out.println("Adoption successful! Here's your new companion: " + adopted.getDetails());
        } else {
            System.out.println("Adoption failed — no animals available for adoption.");
        }
    }

    public void preferenceBasedAdoption() {
        System.out.println("\n--- Preference-Based Adoption ---");

        String adopterName = prompt("Enter adopter's name: ");

        // Here, you need to gather preferences; simple example:
        System.out.println("Please enter your animal preferences.");

        String species = prompt("Species (dog, cat, lizard, etc.): ");

        Adopter adopter = new Adopter(adopterName);
        adopter.setPreference("species", species);
        // Add other preference settings based on input

        Animal adopted = adoptionService.adoptByPreference(adopter);
        if (adopted != null) {
            System.out.println("Adoption successful! You adopted: " + adopted.getDetails());
        } else {
            System.out.println("No animals matched your preferences.");
        }
    }

    public void listAnimals() {
        System.out.println("\n--- Animals in Shelter ---");

        // Assuming adoptionService exposes registry or has a method to get all animals
        var animals = adoptionService.getRegistry().getAllAnimals();

        if (animals.isEmpty()) {
            System.out.println("No animals currently in the shelter.");
        } else {
            animals.forEach(animal -> System.out.println(animal.getDetails()));
        }
    }


    public void peekNextAnimal() {
        System.out.println("\n--- Preview Next Animal in Adoption Queue ---");
        Animal next = adoptionService.peekNext();
        System.out.println(next != null
                ? "Next animal in the queue: " + next.getDetails()
                : "No animals in the adoption queue.");
    }

    private final Map<String, Animal> adoptedAnimalMap = new HashMap<>();

    public void adoptAnimal(Animal animal) {
        if (animal != null) {
            adoptedAnimals.add(animal);
            adoptedAnimalMap.put(animal.getId(), animal); // assuming Animal has unique getId()
        }
    }

    public Animal getAdoptedAnimalById(String id) {
        return adoptedAnimalMap.get(id);
    }

    public void clearQueue() {
        System.out.println("\n--- Clear Adoption Queue ---");
        if (adoptionService.isQueueEmpty()) {
            System.out.println("The adoption queue is already empty.");
        } else {
            adoptionService.clearQueue();
            System.out.println("The adoption queue has been cleared.");
        }
    }

    private String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    public void adoptAnimal() {
        System.out.println("\n--- Manual Animal Adoption ---");

        List<Animal> animals = adoptionService.getRegistry().getAllAnimals();
        if (animals.isEmpty()) {
            System.out.println("No animals available for adoption.");
            return;
        }

        for (int i = 0; i < animals.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, animals.get(i).getDetails());
        }

        int choice = -1;
        while (choice < 1 || choice > animals.size()) {
            System.out.print("Select animal to adopt (1 - " + animals.size() + "): ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        Animal selectedAnimal = animals.get(choice - 1);
        adoptAnimal(selectedAnimal);

        System.out.println("Adoption successful! You adopted: " + selectedAnimal.getDetails());
    }

}
