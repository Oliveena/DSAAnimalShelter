package patterns.templates;

import models.Adopter;
import models.AnimalRegistry;
import models.animals.Animal;
import util.matching.PreferenceMatcher;

import java.util.List;
import java.util.logging.Logger;

public class PreferenceBasedAdoptionProcessor extends AnimalProcessingTemplate {

    private final Adopter adopter;
    private final AnimalRegistry registry;
    private Animal adoptedAnimal;

    private final Logger logger = Logger.getLogger(PreferenceBasedAdoptionProcessor.class.getName());

    public PreferenceBasedAdoptionProcessor(Adopter adopter, AnimalRegistry registry) {
        this.adopter = adopter;
        this.registry = registry;
    }

    @Override
    protected void validateInput() {
        if (adopter == null) throw new IllegalArgumentException("Adopter must not be null.");
        if (adopter.getPreferences() == null) throw new IllegalArgumentException("Adopter preferences must be set.");
    }

    @Override
    protected Animal findAnimal() {
        List<Animal> availableAnimals = registry.getAllAnimals();
        adoptedAnimal = PreferenceMatcher.findBestMatch(adopter, availableAnimals);
        if (adoptedAnimal == null) throw new IllegalStateException("No matching animals found for preferences.");
        return adoptedAnimal;
    }

    @Override
    protected void verifyEligibility(Animal animal) {
        // Add eligibility checks here if needed
    }

    @Override
    protected void process(Animal animal) {
        adopter.adoptAnimal(animal);
        registry.removeAnimalById(animal.getId());
    }

    @Override
    protected void logOutcome(Animal animal) {
        logger.info(adopter.getName() + " adopted animal based on preferences: " + animal.getDetails());
    }
}

