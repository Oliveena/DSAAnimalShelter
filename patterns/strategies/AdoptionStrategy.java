package patterns.strategies;

import models.Animal;
import models.AnimalRegistry;
import models.ShelterQueue;

/**
 * Defines the strategy for adopting an animal from the shelter.
 * <p>
 * This interface allows different adoption patterns.strategies to be implemented, enabling
 * various ways of selecting and adopting animals based on custom criteria.
 * <p>
 * Implementing classes must define the {@link #adopt(AnimalRegistry, ShelterQueue)} method
 * to specify how an animal is selected and adopted from the registry and queue.
 */
public interface AdoptionStrategy {

    /**
     * Adopts an animal from the shelter's registry or queue.
     * <p>
     * This method defines the adoption process, selecting an animal based on a specific
     * adoption strategy, and removes the adopted animal from the registry or queue.
     *
     * @param registry the {@link AnimalRegistry} containing all animals in the shelter
     * @param queue the {@link ShelterQueue} containing animals awaiting adoption
     * @return the adopted anaimal, or null if no animal is available for adoption
     */
    Animal adopt(AnimalRegistry registry, ShelterQueue queue);
}
