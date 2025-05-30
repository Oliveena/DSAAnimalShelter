package patterns.structural.templates;

import models.Adopter;
import models.AnimalRegistry;
import models.ShelterQueue;
import models.animals.Animal;
import patterns.behavioral.strategies.AnimalMatchingStrategy;

import java.util.logging.Logger;

/**
 * Adoption processor implementing a FIFO (First-In-First-Out) adoption strategy.
 * Extends the AnimalProcessingTemplate to define the adoption process steps.
 * Uses an injected AnimalMatchingStrategy to select the next animal for adoption from a queue.
 */
public class FifoAdoptionProcessor extends AnimalProcessingTemplate {

    private final AnimalMatchingStrategy matchingStrategy;

    private final AnimalRegistry registry;
    private final ShelterQueue queue;
    private final Adopter adopter;
    private Animal adoptedAnimal;

    private final Logger logger = Logger.getLogger(FifoAdoptionProcessor.class.getName());

    /**
     * Constructs a FifoAdoptionProcessor.
     *
     * @param adopter          The adopter performing the adoption.
     * @param registry         The registry containing animals.
     * @param queue            The shelter queue from which animals are adopted in FIFO order.
     * @param matchingStrategy The strategy used to select the animal from the queue.
     */
    public FifoAdoptionProcessor(Adopter adopter, AnimalRegistry registry, ShelterQueue queue, AnimalMatchingStrategy matchingStrategy) {
        this.adopter = adopter;
        this.registry = registry;
        this.queue = queue;
        this.matchingStrategy = matchingStrategy;
    }

    /**
     * Gets the animal adopted by this processor.
     *
     * @return The adopted animal, or null if none adopted yet.
     */
    public Animal getAdoptedAnimal() {
        return adoptedAnimal;
    }

    /**
     * Validates the input parameters before adoption processing.
     * Ensures the adopter is not null.
     *
     * @throws IllegalArgumentException if adopter is null.
     */
    @Override
    protected void validateInput() {
        if (adopter == null) throw new IllegalArgumentException("Adopter must not be null.");
    }

    /**
     * Finds the next animal to adopt using the provided matching strategy.
     * Removes the adopted animal from the registry upon selection.
     *
     * @return The animal selected for adoption.
     * @throws IllegalStateException if no animals are available for adoption.
     */
    @Override
    protected Animal findAnimal() {
        adoptedAnimal = matchingStrategy.selectAnimal(adopter, registry, queue);

        if (adoptedAnimal == null) throw new IllegalStateException("No animals available for adoption.");
        registry.removeAnimalById(adoptedAnimal.getId());  // Remove from registry

        return adoptedAnimal;
    }

    /**
     * Verifies eligibility of the adopter to adopt the animal.
     * Override to implement specific eligibility criteria if needed.
     *
     * @param animal The animal selected for adoption.
     */
    @Override
    protected void verifyEligibility(Animal animal) {
        // Add eligibility logic here if needed
    }

    /**
     * Processes the adoption by associating the animal with the adopter.
     *
     * @param animal The animal to be adopted.
     */
    @Override
    protected void process(Animal animal) {
        adopter.adoptAnimal(animal);
    }

    /**
     * Logs the outcome of the adoption process.
     *
     * @param animal The animal that was adopted.
     */
    @Override
    protected void logOutcome(Animal animal) {
        logger.info(adopter.getName() + " adopted animal: " + animal.getName());
    }
}
