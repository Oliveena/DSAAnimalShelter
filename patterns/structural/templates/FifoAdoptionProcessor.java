package patterns.structural.templates;

import models.Adopter;
import models.AnimalRegistry;
import models.ShelterQueue;
import models.animals.Animal;
import patterns.behavioral.strategies.AnimalMatchingStrategy;

import java.util.logging.Logger;

public class FifoAdoptionProcessor extends AnimalProcessingTemplate {

    private final AnimalMatchingStrategy matchingStrategy;

    private final AnimalRegistry registry;
    private final ShelterQueue queue;
    private final Adopter adopter;
    private Animal adoptedAnimal;

    private final Logger logger = Logger.getLogger(FifoAdoptionProcessor.class.getName());

    // Updated constructor: accept strategy as a parameter
    public FifoAdoptionProcessor(Adopter adopter, AnimalRegistry registry, ShelterQueue queue, AnimalMatchingStrategy matchingStrategy) {
        this.adopter = adopter;
        this.registry = registry;
        this.queue = queue;
        this.matchingStrategy = matchingStrategy;
    }

    public Animal getAdoptedAnimal() {
        return adoptedAnimal;
    }

    @Override
    protected void validateInput() {
        if (adopter == null) throw new IllegalArgumentException("Adopter must not be null.");
    }

    @Override
    protected Animal findAnimal() {
        // Use strategy to select animal
        adoptedAnimal = matchingStrategy.selectAnimal(adopter, registry, queue);

        if (adoptedAnimal == null) throw new IllegalStateException("No animals available for adoption.");
        registry.removeAnimalById(adoptedAnimal.getId());  // Remove from registry

        return adoptedAnimal;
    }

    @Override
    protected void verifyEligibility(Animal animal) {
        // Add eligibility logic here if needed
    }

    @Override
    protected void process(Animal animal) {
        adopter.adoptAnimal(animal);
    }

    @Override
    protected void logOutcome(Animal animal) {
        logger.info(adopter.getName() + " adopted animal: " + animal.getName());
    }
}
