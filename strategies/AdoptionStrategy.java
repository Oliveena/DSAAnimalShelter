package strategies;

import logic.Animal;
import data.AnimalRegistry;
import logic.ShelterQueue;

public interface AdoptionStrategy {
    Animal adopt(AnimalRegistry registry, ShelterQueue queue);
}