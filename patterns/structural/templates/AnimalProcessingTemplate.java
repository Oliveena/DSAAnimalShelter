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
 *     1. {@code validateInput()}      - Ensures all necessary data is present.
 *     2. {@code findAnimal()}         - Retrieves the relevant animal from registry/queue.
 *     3. {@code verifyEligibility()}  - Confirms animal is valid for operation.
 *     4. {@code process()}            - Executes the main action (adopt/return/etc.).
 *     5. {@code logOutcome()}         - Records the result.
 *
 * 💡 Subclasses include:
 *     - {@link PreferenceBasedAdoptionProcessor}
 *     - {@link FifoAdoptionProcessor}
 *     - {@link AnimalReturnProcessor}
 */
public abstract class AnimalProcessingTemplate {

    /**
     * Template method defining the sequence of steps for animal processing.
     * It is final to prevent subclasses from altering the processing order.
     */
    public final void execute() {
        validateInput();
        Animal animal = findAnimal();
        verifyEligibility(animal);
        process(animal);
        logOutcome(animal);
    }

    /**
     * Validates input data before processing.
     * Subclasses should throw IllegalArgumentException or other exceptions if validation fails.
     */
    protected abstract void validateInput();

    /**
     * Finds and returns the animal to be processed.
     *
     * @return The animal involved in this processing.
     */
    protected abstract Animal findAnimal();

    /**
     * Verifies that the animal meets eligibility criteria for the operation.
     * This could include checks like adoption status, health, or shelter rules.
     *
     * @param animal The animal to verify.
     */
    protected abstract void verifyEligibility(Animal animal);

    /**
     * Performs the main processing step such as adoption, return, or other operations.
     *
     * @param animal The animal to process.
     */
    protected abstract void process(Animal animal);

    /**
     * Logs or reports the outcome of the processing.
     *
     * @param animal The animal processed.
     */
    protected abstract void logOutcome(Animal animal);
}
