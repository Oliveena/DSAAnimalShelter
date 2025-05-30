package patterns.structural.templates;

import models.Adopter;
import models.AnimalRegistry;
import models.animals.Animal;
import patterns.behavioral.strategies.AnimalMatchingStrategy;

/**
 * Template for processing animal adoption based on adopter preferences.
 * Implements the Template Method pattern to provide the adoption workflow,
 * and uses a strategy for selecting a matching animal.
 */
public class PreferenceBasedAdoptionProcessor extends AnimalProcessingTemplate {
    private final Adopter adopter;
    private final AnimalRegistry registry;
    private final AnimalMatchingStrategy matchingStrategy; // Injected strategy for animal matching
    private Animal adoptedAnimal;

    /**
     * Constructs a PreferenceBasedAdoptionProcessor with the given adopter,
     * animal registry, and matching strategy.
     *
     * @param adopter         The adopter with preferences.
     * @param registry        The registry of available animals.
     * @param strategy        The strategy used to select a matching animal.
     */
    public PreferenceBasedAdoptionProcessor(Adopter adopter, AnimalRegistry registry, AnimalMatchingStrategy strategy) {
        this.adopter = adopter;
        this.registry = registry;
        this.matchingStrategy = strategy;
    }

    /**
     * Validates the input data before adoption processing.
     * Ensures adopter and their preferences are not null.
     *
     * @throws IllegalArgumentException if adopter or preferences are null.
     */
    @Override
    protected void validateInput() {
        if (adopter == null) {
            throw new IllegalArgumentException("Adopter must not be null.");
        }
        if (adopter.getPreferences() == null) {
            throw new IllegalArgumentException("Adopter preferences must be set.");
        }
    }

    /**
     * Finds a suitable animal based on adopter preferences using the injected strategy.
     *
     * @return The matched animal.
     * @throws IllegalStateException if no matching animal is found.
     */
    @Override
    protected Animal findAnimal() {
        // Preference matching does not rely on a queue, so null is passed for the queue parameter
        adoptedAnimal = matchingStrategy.selectAnimal(adopter, registry, null);
        if (adoptedAnimal == null) {
            throw new IllegalStateException("No matching animals found.");
        }
        return adoptedAnimal;
    }

    /**
     * Verifies eligibility of the adopter for the selected animal.
     * Override to add specific eligibility rules if required.
     *
     * @param animal The animal selected for adoption.
     */
    @Override
    protected void verifyEligibility(Animal animal) {
        // Implement eligibility logic if needed (e.g., adopter meets criteria)
    }

    /**
     * Processes the adoption by associating the animal with the adopter
     * and removing the animal from the registry.
     *
     * @param animal The animal to be adopted.
     */
    @Override
    protected void process(Animal animal) {
        adopter.adoptAnimal(animal);
        registry.removeAnimalById(animal.getId());
    }

    /**
     * Logs the outcome of the adoption process.
     *
     * @param animal The animal that was adopted.
     */
    @Override
    protected void logOutcome(Animal animal) {
        System.out.println(adopter.getName() + " adopted " + animal.getName() + " based on preference strategy.");
    }

    /**
     * Returns the animal that was adopted during the process.
     *
     * @return The adopted animal.
     */
    public Animal getAdoptedAnimal() {
        return adoptedAnimal;
    }
}
