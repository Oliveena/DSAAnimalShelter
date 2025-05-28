package patterns.behavioral.strategies;

import models.Adopter;
import models.AdopterPreferences;
import models.AnimalRegistry;
import models.ShelterQueue;
import models.animals.Animal;

import java.util.List;

public interface AnimalMatchingStrategy {
    Animal selectAnimal(Adopter adopter, AnimalRegistry registry, ShelterQueue queue);
}
