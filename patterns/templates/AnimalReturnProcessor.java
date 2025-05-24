package patterns.templates;

import models.AnimalRegistry;
import models.Animal;

import java.util.logging.Logger;

public class AnimalReturnProcessor extends patterns.templates.AnimalProcessingTemplate {

    private final String animalId;
    private final String reason;
    private final AnimalRegistry registry;
    private Animal animal;

    private final Logger logger = Logger.getLogger(AnimalReturnProcessor.class.getName());

    public AnimalReturnProcessor(String animalId, String reason, AnimalRegistry registry) {
        this.animalId = animalId;
        this.reason = reason;
        this.registry = registry;
    }

    @Override
    protected void validateInput() {
        if (animalId == null || animalId.trim().isEmpty())
            throw new IllegalArgumentException("Animal ID cannot be empty.");
        if (reason == null || reason.trim().isEmpty())
            throw new IllegalArgumentException("Return reason must be provided.");
    }

    @Override
    protected Animal findAnimal() {
        animal = registry.findById(animalId);
        if (animal == null)
            throw new IllegalArgumentException("Animal with ID " + animalId + " not found.");
        return animal;
    }

    @Override
    protected void verifyEligibility(Animal animal) {
        //TODO: add logic for validating if was adopted previously
    }

    @Override
    protected void process(Animal animal) {
        registry.addAnimal(animal); // Return to registry
    }

    @Override
    protected void logOutcome(Animal animal) {
        logger.info("Animal " + animal.getName() + " was returned. Reason: " + reason);
    }
}
