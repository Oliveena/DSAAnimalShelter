package patterns.behavioral.strategies;

import models.Adopter;
import models.AdopterPreferences;
import models.AnimalRegistry;
import models.ShelterQueue;
import models.animals.Animal;

import java.util.List;
import java.util.Comparator;

public class PreferenceMatchingStrategy implements AnimalMatchingStrategy {
    @Override
    public Animal selectAnimal(Adopter adopter, AnimalRegistry registry, ShelterQueue queue) {
        List<Animal> animals = registry.getAllAnimals();
        return findBestMatch(adopter, animals);
    }

    private Animal findBestMatch(Adopter adopter, List<Animal> availableAnimals) {
        AdopterPreferences preferences = adopter.getPreferences();
        if (preferences == null || availableAnimals == null || availableAnimals.isEmpty()) {
            return null;
        }

        // Stream to find animal with max score, parallel stream used because animal list could be very large
                return availableAnimals.parallelStream()
                .max(Comparator.comparingInt(animal -> getScore(animal, preferences)))
                .orElse(null);
    }

    private int getScore(Animal animal, AdopterPreferences preferences) {
        int score = 0;

        if (preferences.getSpecies() != null && animal.getSpecies() != null &&
                preferences.getSpecies().equalsIgnoreCase(animal.getSpecies().name())) {
            score++;
        }

        if (preferences.getBreed() != null && animal.getBreed() != null &&
                preferences.getBreed().equalsIgnoreCase(animal.getBreed())) {
            score++;
        }

        if (preferences.getMaxAge() != null && animal.getAge() <= preferences.getMaxAge()) {
            score++;
        }

        return score;
    }
}