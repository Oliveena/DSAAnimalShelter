package patterns.strategies;

import models.AnimalRegistry;
import models.animals.Animal;
import models.ShelterQueue;
import java.util.logging.Logger;
import patterns.factories.LogFactory;

public class FIFOAdoptionStrategy implements AdoptionStrategy {
    private static final Logger logger = LogFactory.getLogger(FIFOAdoptionStrategy.class);

    @Override
    public Animal adopt(AnimalRegistry registry, ShelterQueue queue) {
        models.animals.Animal adopted = queue.dequeue();  // Adopt the first animal in the queue

        if (adopted != null) {
            registry.removeAnimalById(adopted.getId());
            logger.info(STR."Adopted animal (FIFO): \{adopted.getName()} [ID: \{adopted.getId()}]");
        } else {
            logger.warning("No animal available for adoption (FIFO queue was empty).");
        }

        return adopted;
    }
}
