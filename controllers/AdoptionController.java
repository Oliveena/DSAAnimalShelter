package controllers;

import models.Adopter;
import models.AdoptionForm;
import models.animals.Animal;
import services.AdoptionService;

import java.time.LocalDate;
import java.util.Scanner;

public class AdoptionController {

    private final AdoptionService adoptionService;
    private final Scanner scanner;

    public AdoptionController(AdoptionService adoptionService, Scanner scanner) {
        this.adoptionService = adoptionService;
        this.scanner = scanner;
    }

    public void adoptAnimal() {
        System.out.println("\n--- Adopt Next Animal (FIFO) ---");

        if (adoptionService.isQueueEmpty()) {
            System.out.println("No animals in the adoption queue.");
            return;
        }

        String name = prompt("Enter adopter's name: ");
        Animal adopted = adoptionService.adoptNext(name);
        if (adopted != null) {
            System.out.println("Adoption successful! Here's your new companion: " + adopted.getDetails());
        } else {
            System.out.println("Adoption failed — no animals available for adoption.");
        }
    }

    public void peekNextAnimal() {
        System.out.println("\n--- Preview Next Animal in Adoption Queue ---");
        Animal next = adoptionService.peekNext();
        System.out.println(next != null
                ? "Next animal in the queue: " + next.getDetails()
                : "No animals in the adoption queue.");
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
}
