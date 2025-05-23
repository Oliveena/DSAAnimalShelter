package templates;

import data.AnimalRegistry;
import data.Adopter;
import domain.Animal;
import domain.ShelterQueue;
import strategies.FIFOAdoptionStrategy;

import java.util.logging.Logger;

public class FifoAdoptionProcessor extends AnimalProcessingTemplate {

    private final AnimalRegistry registry;
    private final ShelterQueue queue;
    private final Adopter adopter;
    private Animal adoptedAnimal;

    private final Logger logger = Logger.getLogger(FifoAdoptionProcessor.class.getName());

    public FifoAdoptionProcessor(Adopter adopter, AnimalRegistry registry, ShelterQueue queue) {
        this.adopter = adopter;
        this.registry = registry;
        this.queue = queue;
    }

    @Override
    protected void validateInput() {
        if (adopter == null) throw new IllegalArgumentException("Adopter must not be null.");
    }

    @Override
    protected Animal findAnimal() {
        adoptedAnimal = new FIFOAdoptionStrategy().adopt(registry, queue);
        if (adoptedAnimal == null) throw new IllegalStateException("No animals available for adoption.");
        return adoptedAnimal;
    }

    @Override
    protected void verifyEligibility(Animal animal) {
        // Add custom eligibility checks here if needed
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
