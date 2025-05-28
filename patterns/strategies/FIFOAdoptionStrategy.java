package patterns.strategies;

import models.AnimalRegistry;
import models.animals.Animal;
import models.ShelterQueue;
import java.util.logging.Logger;
import patterns.factories.LogFactory;

/**
 *  FIFOAdoptionStrategy - First-In, First-Out Adoption Logic
 *
 * This strategy implements a simple FIFO (queue-based) approach for processing animal adoptions.
 * The animal that has been in the shelter the longest (i.e. front of the queue) is selected first.
 *
 *  Use Case:
 * This strategy is ideal for spotlight or promotional adoptions such as the "Animal of the Month".
 * It helps prioritize animals who have waited the longest.
 *
 *  This strategy does NOT consider adopter preferences.
 *
 * @see patterns.templates.FifoAdoptionProcessor
 */
public class FIFOAdoptionStrategy implements AdoptionStrategy {

    private static final Logger logger = LogFactory.getLogger(FIFOAdoptionStrategy.class);

    /**
     * Performs the FIFO adoption by retrieving the first animal in the {@link ShelterQueue},
     * removing it from the queue and the {@link AnimalRegistry}, and logging the outcome.
     *
     * @param registry the central animal registry (used for removal)
     * @param queue the shelter's FIFO adoption queue
     * @return the adopted {@link Animal}, or {@code null} if the queue is empty
     */
    @Override
    public Animal adopt(AnimalRegistry registry, ShelterQueue queue) {
        Animal adopted = queue.dequeue();  // Adopt the first animal in the queue

        if (adopted != null) {
            registry.removeAnimalById(adopted.getId());
            logger.info("Adopted animal (FIFO): " + adopted.getName() + " [ID: " + adopted.getId() + "]");
        } else {
            logger.warning("No animal available for adoption (FIFO queue was empty).");
        }

        return adopted;
    }
}
