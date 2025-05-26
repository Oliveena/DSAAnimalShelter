package patterns.strategies;

import models.animals.Animal;
import models.AnimalRegistry;
import models.ShelterQueue;

/**
 * AdoptionStrategy - Strategy Interface for Animal Adoption
 *
 * Defines the contract for different animal adoption strategies in the shelter system.
 * <p>
 * This interface enables flexible and pluggable algorithms for selecting animals
 * based on different criteria (e.g., FIFO, preference matching).
 *
 * <p>
 * Implementing classes must define the {@link #adopt(AnimalRegistry, ShelterQueue)} method
 * to specify how an animal is selected and adopted using the provided registry and queue.
 *
 * @see FIFOAdoptionStrategy
 * @see util.matching.PreferenceMatcher (via {@link patterns.templates.PreferenceBasedAdoptionProcessor})
 */
public interface AdoptionStrategy {

    /**
     * Selects and adopts an animal from the shelter's registry or queue based on the strategy.
     * <p>
     * The implementation determines how animals are prioritized and removed from the system.
     *
     * @param registry the {@link AnimalRegistry} containing all animals in the shelter
     * @param queue the {@link ShelterQueue} containing animals awaiting adoption
     * @return the adopted animal, or {@code null} if no animal is available for adoption
     */
    Animal adopt(AnimalRegistry registry, ShelterQueue queue);
}
