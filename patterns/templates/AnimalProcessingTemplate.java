package patterns.templates;

import models.Animal;

public abstract class AnimalProcessingTemplate {

    public final void execute() {
        validateInput();
        Animal animal = findAnimal();
        verifyEligibility(animal);
        process(animal);
        logOutcome(animal);
    }

    protected abstract void validateInput();
    protected abstract Animal findAnimal();
    protected abstract void verifyEligibility(Animal animal);
    protected abstract void process(Animal animal);
    protected abstract void logOutcome(Animal animal);
}
