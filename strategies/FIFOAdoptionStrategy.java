package strategies;

import data.AnimalRegistry;
import logic.Animal;
import logic.ShelterQueue;

public class FIFOAdoptionStrategy implements AdoptionStrategy {
    @Override
    public Animal adopt(AnimalRegistry registry, ShelterQueue queue) {
        Animal adopted = queue.dequeue();
        if (adopted != null) {
            registry.removeAnimalById(adopted.getId());
        }
        return adopted;
    }
}