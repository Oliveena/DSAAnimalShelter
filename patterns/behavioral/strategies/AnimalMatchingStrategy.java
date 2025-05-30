package patterns.behavioral.strategies;

import models.Adopter;
import models.AnimalRegistry;
import models.ShelterQueue;
import models.animals.Animal;

/**
 * Strategy interface for matching an {@link Adopter} with an {@link Animal}.
 * <p>
 * Implementations of this interface define different matching algorithms,
 * allowing the shelter system to dynamically select the most appropriate
 * animal based on the adopter’s preferences and shelter conditions.
 * <p>
 * Common strategies may include:
 * <ul>
 *     <li>First-available animal matching</li>
 *     <li>Preference-based matching</li>
 *     <li>Health status or behavior-based filtering</li>
 * </ul>
 *
 * @see models.Adopter
 * @see models.AnimalRegistry
 * @see models.ShelterQueue
 */
public interface AnimalMatchingStrategy {

    /**
     * Selects the most suitable animal for the given adopter based on the implemented strategy.
     *
     * @param adopter  the adopter seeking to adopt an animal
     * @param registry the current registry of available animals in the shelter
     * @param queue    the shelter's queue of animals awaiting adoption
     * @return the selected {@link Animal}, or {@code null} if no suitable match is found
     */
    Animal selectAnimal(Adopter adopter, AnimalRegistry registry, ShelterQueue queue);
}
