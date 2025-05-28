package services;

import models.AnimalRegistry;
import models.ShelterQueue;
import models.animals.Animal;
import models.Adopter;
import models.AdoptionForm;
import patterns.creational.factories.FormFactory;
import patterns.behavioral.strategies.AdoptionStrategy;

import java.time.LocalDate;

public class AdoptionService {
    private final ShelterQueue queue;
    private final AnimalRegistry registry;
    private final AdoptionStrategy strategy;
    private final FormFactory formFactory;

    public AdoptionService(ShelterQueue queue, AnimalRegistry registry, AdoptionStrategy strategy, FormFactory formFactory) {
        this.queue = queue;
        this.registry = registry;
        this.strategy = strategy;
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

    public Animal adoptNext(String adopterName) {
        Animal adopted = strategy.adopt(registry, queue);
        if (adopted != null) {
            Adopter adopter = new Adopter(adopterName);
            AdoptionForm form = formFactory.createAdoptionForm(adopter, adopted, LocalDate.now());
            form.submit();
        }
        return adopted;
    }
}
