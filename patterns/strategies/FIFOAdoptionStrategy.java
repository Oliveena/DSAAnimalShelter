package patterns.strategies;

import models.AnimalRegistry;
import models.Animal;
import models.ShelterQueue;

/**
 * A concrete implementation of the {@link AdoptionStrategy} that adopts animals
 * using a First In, First Out (FIFO) approach.
 * <p>
 * This strategy adopts the animal that has been in the shelter the longest. It
 * removes the adopted animal from both the shelter's queue and the registry.
 * <p>
 * This strategy ensures animals are adopted in the order they were placed in the queue,
 * making it ideal for scenarios where prioritizing older arrivals is necessary.
 */
public class FIFOAdoptionStrategy implements AdoptionStrategy {

    /**
     * Adopts an animal based on the FIFO (First In, First Out) principle.
     * <p>
     * The animal at the front of the queue is adopted first. The adopted animal is
     * then removed from both the registry and the queue.
     *
     * @param registry the {@link AnimalRegistry} containing all animals in the shelter
     * @param queue the {@link ShelterQueue} containing animals awaiting adoption
     * @return the adopted animal, or null if no animal is available for adoption
     */
    @Override
    public Animal adopt(AnimalRegistry registry, ShelterQueue queue) {
        Animal adopted = queue.dequeue();  // Adopt the first animal in the queue
        if (adopted != null) {
            registry.removeAnimalById(adopted.getId());  // Remove the adopted animal from the registry
        }
        return adopted;
    }
}
