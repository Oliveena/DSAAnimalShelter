package patterns.structural.templates;

import models.AnimalRegistry;
import models.animals.Animal;

import java.util.logging.Logger;

/**
 * AnimalReturnProcessor - Handles Returning Adopted Animals
 *
 * Allows adopters to return animals to the shelter, typically due to incompatibility,
 * unforeseen circumstances, or medical issues.
 *
 * ➤ Steps:
 *     1. Validate animal ID and return reason
 *     2. Locate animal via ID
 *     3. (TODO) Verify that animal was adopted previously
 *     4. Re-register animal in the shelter registry
 *     5. Log return details
 *
 * 💡 Can be extended to:
 *     - Track return statistics
 *     - Analyze reasons for failed adoptions
 */
public class AnimalReturnProcessor extends AnimalProcessingTemplate {

    private final String animalId;
    private final String reason;
    private final AnimalRegistry registry;
    private Animal animal;

    private final Logger logger = Logger.getLogger(AnimalReturnProcessor.class.getName());

    /**
     * Creates an AnimalReturnProcessor to handle an animal return.
     *
     * @param animalId The ID of the animal being returned.
     * @param reason   The reason for the return.
     * @param registry The animal registry to update.
     */
    public AnimalReturnProcessor(String animalId, String reason, AnimalRegistry registry) {
        this.animalId = animalId;
        this.reason = reason;
        this.registry = registry;
    }

    /**
     * Validates the input parameters for the return process.
     *
     * @throws IllegalArgumentException if animal ID or reason is missing or empty.
     */
    @Override
    protected void validateInput() {
        if (animalId == null || animalId.trim().isEmpty())
            throw new IllegalArgumentException("Animal ID cannot be empty.");
        if (reason == null || reason.trim().isEmpty())
            throw new IllegalArgumentException("Return reason must be provided.");
    }

    /**
     * Finds the animal in the registry by its ID.
     *
     * @return The animal to be returned.
     * @throws IllegalArgumentException if no animal is found with the given ID.
     */
    @Override
    protected Animal findAnimal() {
        animal = registry.findById(animalId);
        if (animal == null)
            throw new IllegalArgumentException("Animal with ID " + animalId + " not found.");
        return animal;
    }

    /**
     * Verifies eligibility for returning the animal.
     * <p>
     * TODO: Implement check to confirm the animal was previously adopted.
     *
     * @param animal The animal being returned.
     */
    @Override
    protected void verifyEligibility(Animal animal) {
        // TODO: add logic for validating if was adopted previously
    }

    /**
     * Processes the return by re-adding the animal to the registry.
     *
     * @param animal The animal being returned.
     */
    @Override
    protected void process(Animal animal) {
        registry.addAnimal(animal); // Return to registry
    }

    /**
     * Logs the outcome of the return process.
     *
     * @param animal The animal that was returned.
     */
    @Override
    protected void logOutcome(Animal animal) {
        logger.info("Animal " + animal.getName() + " was returned. Reason: " + reason);
    }
}
