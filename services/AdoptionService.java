package services;

import models.Adopter;
import models.AnimalRegistry;
import models.ShelterQueue;
import models.animals.Animal;
import patterns.behavioral.strategies.AnimalMatchingStrategy;
import patterns.behavioral.strategies.PreferenceMatchingStrategy;
import patterns.creational.factories.FormFactory;
import patterns.structural.templates.FifoAdoptionProcessor;
import patterns.structural.templates.PreferenceBasedAdoptionProcessor;

import java.time.LocalDate;

/**
 * Service class managing animal adoption processes including FIFO and preference-based adoptions.
 * Uses strategy and template method patterns to handle different adoption workflows.
 */
public class AdoptionService {
    private final ShelterQueue queue;
    private final AnimalRegistry registry;
    private final FormFactory formFactory;

    // Reusable adoption strategies
    private final AnimalMatchingStrategy fifoStrategy = new FifoMatchingStrategy();
    private final AnimalMatchingStrategy preferenceStrategy = new PreferenceMatchingStrategy();

    /**
     * Constructs an AdoptionService with the given shelter queue, animal registry, and form factory.
     *
     * @param queue       Shelter queue managing waiting adopters.
     * @param registry    Registry containing all animals in the shelter.
     * @param formFactory Factory to create adoption forms.
     */
    public AdoptionService(ShelterQueue queue, AnimalRegistry registry, FormFactory formFactory) {
        this.queue = queue;
        this.registry = registry;
        this.formFactory = formFactory;
    }

    /**
     * Checks whether the adoption queue is empty.
     *
     * @return true if the queue is empty; false otherwise.
     */
    public boolean isQueueEmpty() {
        return queue.isEmpty();
    }

    /**
     * Peeks at the next animal available for adoption in the queue without removing it.
     *
     * @return The next animal in the queue, or null if none available.
     */
    public Animal peekNext() {
        return queue.peekNext();
    }

    /**
     * Clears all entries from the adoption queue.
     */
    public void clearQueue() {
        queue.clear();
    }

    /**
     * Attempts to adopt the next animal in the queue using a FIFO approach.
     * Creates and submits an adoption form upon successful adoption.
     *
     * @param adopterName Name of the adopter.
     * @return The adopted animal, or null if adoption failed.
     */
    public Animal adoptNextFIFO(String adopterName) {
        Adopter adopter = new Adopter(adopterName);
        FifoAdoptionProcessor processor = new FifoAdoptionProcessor(adopter, registry, queue, fifoStrategy);

        try {
            processor.execute();
            Animal adopted = processor.getAdoptedAnimal();
            if (adopted != null) {
                formFactory.createAdoptionForm(adopter, adopted, LocalDate.now()).submit();
            }
            return adopted;
        } catch (Exception e) {
            System.out.println("FIFO adoption failed: " + e.getMessage());
            return null;
        }
    }

    /**
     * Attempts to adopt an animal based on the preferences of the provided adopter.
     * Creates and submits an adoption form upon successful adoption.
     *
     * @param adopter The adopter with preferences.
     * @return The adopted animal, or null if none found.
     */
    public Animal adoptByPreference(Adopter adopter) {
        PreferenceBasedAdoptionProcessor processor = new PreferenceBasedAdoptionProcessor(adopter, registry, preferenceStrategy);

        try {
            processor.execute();
            Animal adopted = processor.getAdoptedAnimal();
            if (adopted != null) {
                formFactory.createAdoptionForm(adopter, adopted, LocalDate.now()).submit();
            }
            return adopted;
        } catch (Exception e) {
            System.out.println("Preference-based adoption failed: " + e.getMessage());
            return null;
        }
    }

    /**
     * Simple FIFO strategy implementation for selecting the next animal in the queue.
     */
    private static class FifoMatchingStrategy implements AnimalMatchingStrategy {
        @Override
        public Animal selectAnimal(Adopter adopter, AnimalRegistry registry, ShelterQueue queue) {
            return queue.peekNext();
        }
    }

    /**
     * Gets the animal registry.
     *
     * @return The animal registry instance.
     */
    public AnimalRegistry getRegistry() {
        return registry;
    }
}
