package patterns.structural.templates;

import models.Adopter;
import models.AnimalRegistry;
import models.animals.Animal;
import patterns.behavioral.strategies.AnimalMatchingStrategy;
import patterns.structural.templates.AnimalProcessingTemplate;

public class PreferenceBasedAdoptionProcessor extends AnimalProcessingTemplate {
    private final Adopter adopter;
    private final AnimalRegistry registry;
    private final AnimalMatchingStrategy matchingStrategy; // Strategy injected
    private Animal adoptedAnimal;

    public PreferenceBasedAdoptionProcessor(Adopter adopter, AnimalRegistry registry, AnimalMatchingStrategy strategy) {
        this.adopter = adopter;
        this.registry = registry;
        this.matchingStrategy = strategy;
    }

    @Override
    protected void validateInput() {
        if (adopter == null) throw new IllegalArgumentException("Adopter must not be null.");
        if (adopter.getPreferences() == null) throw new IllegalArgumentException("Adopter preferences must be set.");
    }

    @Override
    protected Animal findAnimal() {
        adoptedAnimal = matchingStrategy.selectAnimal(adopter, registry, null); // queue not needed here
        if (adoptedAnimal == null) throw new IllegalStateException("No matching animals found.");
        return adoptedAnimal;
    }

    @Override
    protected void verifyEligibility(Animal animal) {
        // eligibility logic if needed
    }

    @Override
    protected void process(Animal animal) {
        adopter.adoptAnimal(animal);
        registry.removeAnimalById(animal.getId());
    }

    @Override
    protected void logOutcome(Animal animal) {
        System.out.println(adopter.getName() + " adopted based on strategy: " + animal.getName());
    }

    public Animal getAdoptedAnimal() {
        return adoptedAnimal;
    }
}


