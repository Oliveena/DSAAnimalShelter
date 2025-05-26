package util.matching;

import models.Adopter;
import models.AdopterPreferences;
import models.animals.Animal;

import java.util.List;

public class PreferenceMatcher {

    /**
     * Finds the best matching animal for the adopter based on their preferences.
     * Uses a simple scoring system: +1 for species, +1 for breed, +1 if age <= maxAge.
     * Returns the animal with highest score, or null if none found.
     */
    public static Animal findBestMatch(Adopter adopter, List<Animal> availableAnimals) {
        AdopterPreferences preferences = adopter.getPreferences();
        if (preferences == null || availableAnimals == null || availableAnimals.isEmpty()) {
            return null;
        }

        Animal bestMatch = null;
        int highestScore = Integer.MIN_VALUE;

        for (Animal animal : availableAnimals) {
            int score = 0;

            if (preferences.getSpecies() != null && animal.getSpecies() != null &&
                    preferences.getSpecies().equalsIgnoreCase(animal.getSpecies())) {
                score++;
            }

            if (preferences.getBreed() != null && animal.getBreed() != null &&
                    preferences.getBreed().equalsIgnoreCase(animal.getBreed())) {
                score++;
            }

            if (preferences.getMaxAge() != null && animal.getAge() <= preferences.getMaxAge()) {
                score++;
            }

            if (score > highestScore) {
                highestScore = score;
                bestMatch = animal;
            }
        }

        return bestMatch;
    }
}
