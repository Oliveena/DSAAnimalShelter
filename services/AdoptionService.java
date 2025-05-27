package services;

import models.Animal;
import models.ShelterQueue;
import models.AnimalRegistry;
import patterns.strategies.AdoptionStrategy;
import patterns.strategies.FIFOAdoptionStrategy;

public class AdoptionService {
    private ShelterQueue queue;
    private AnimalRegistry registry;
    private AdoptionStrategy adoptionStrategy;

    public AdoptionService(AdoptionStrategy strategy,ShelterQueue queue, AnimalRegistry registry) {
        this.queue = queue;
        this.registry = registry;
        this.adoptionStrategy = new FIFOAdoptionStrategy();
    }

    /**
     * Displays the next animal in the adoption queue without removing it.
     */
    public void peekNextAnimal() {
        System.out.println("\n--- Preview Next Animal in Adoption Queue ---");

        Animal nextAnimal = queue.peekNext();
        if (nextAnimal == null) {
            System.out.println("No animals in the adoption queue.");
        } else {
            System.out.println("Next animal in the queue: " + nextAnimal.getDetails());
        }
    }

    /**
     * Clears all animals from the adoption queue.
     * If the queue is already empty, a message is displayed.
     */
    public void clearQueue() {
        System.out.println("\n--- Clear Adoption Queue ---");

        if (queue.isEmpty()) {
            System.out.println("The adoption queue is already empty.");
        } else {
            queue.clear(); // This will clear all animals from the queue
            System.out.println("The adoption queue has been cleared.");
        }
    }


    /**
     * Adopts the next animal in the queue using the adoption strategy.
     * The adopted animal is removed from the registry and queue.
     */
    public void adoptAnimal() {
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

}
