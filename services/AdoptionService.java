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

public class AdoptionService {
    private final ShelterQueue queue;
    private final AnimalRegistry registry;
    private final FormFactory formFactory;

    // Create reusable strategies (or inject via constructor/DI)
    private final AnimalMatchingStrategy fifoStrategy = new FifoMatchingStrategy();
    private final AnimalMatchingStrategy preferenceStrategy = new PreferenceMatchingStrategy();

    public AdoptionService(ShelterQueue queue, AnimalRegistry registry, FormFactory formFactory) {
        this.queue = queue;
        this.registry = registry;
        this.formFactory = formFactory;
    }

    public boolean isQueueEmpty() {
        return queue.isEmpty();
    }

    public Animal peekNext() {
        return queue.peekNext();
    }

    public void clearQueue() {
        queue.clear();
    }

    // FIFO Adoption
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

    // Preference-Based Adoption
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

    // Strategy implementations (could be static classes or moved outside)
    private static class FifoMatchingStrategy implements AnimalMatchingStrategy {
        @Override
        public Animal selectAnimal(Adopter adopter, AnimalRegistry registry, ShelterQueue queue) {
            return queue.peekNext();
        }
    }

    public AnimalRegistry getRegistry() {
        return registry;
    }

}
