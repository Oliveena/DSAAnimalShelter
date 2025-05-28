package patterns.structural.templates;

import models.animals.Animal;

/**
 * 🧩 AnimalProcessingTemplate - Abstract Template for Adoption Operations
 *
 * Defines the skeleton of an adoption-related operation using the Template Method Pattern.
 * Subclasses implement each step to handle specific types of animal processing (e.g., adoption,
 * return, preference-based matching).
 *
 * ➤ Processing Flow:
 *     1. {@code validateInput()}     - Ensures all necessary data is present
 *     2. {@code findAnimal()}        - Retrieves the relevant animal from registry/queue
 *     3. {@code verifyEligibility()} - Confirms animal is valid for operation
 *     4. {@code process()}           - Executes the main action (adopt/return/etc.)
 *     5. {@code logOutcome()}        - Records the result
 *
 * 💡 Subclasses include:
 *     - {@link PreferenceBasedAdoptionProcessor}
 *     - {@link FifoAdoptionProcessor}
 *     - {@link AnimalReturnProcessor}
 */

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
