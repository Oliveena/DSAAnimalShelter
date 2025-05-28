package util.matching;

import models.Adopter;
import models.AdopterPreferences;
import models.animals.Animal;

import java.util.List;

/**
 * Utility class responsible for finding the best-matching animal for an adopter
 * based on their declared preferences.
 * <p>
 * Matching is done using a simple scoring heuristic:
 * <ul>
 *   <li>+1 if species matches</li>
 *   <li>+1 if breed matches</li>
 *   <li>+1 if age is within maximum age</li>
 * </ul>
 * The animal with the highest score is returned.
 */
public class PreferenceMatcher {

    /**
     * Finds the most suitable animal from the available list based on the adopter's preferences.
     *
     * @param adopter the adopter with defined preferences
     * @param availableAnimals list of animals currently available for adoption
     * @return the best-matching animal, or {@code null} if no suitable match is found
     */
    public static Animal findBestMatch(Adopter adopter, List<Animal> availableAnimals) {
        AdopterPreferences preferences = adopter.getPreferences();
        if (preferences == null || availableAnimals == null || availableAnimals.isEmpty()) {
            return null;
        }

        Animal bestMatch = null;
        int highestScore = Integer.MIN_VALUE;

        for (Animal animal : availableAnimals) {
            int score = getScore(animal, preferences);

            if (score > highestScore) {
                highestScore = score;
                bestMatch = animal;
            }
        }

        return bestMatch;
    }

    private static int getScore(Animal animal, AdopterPreferences preferences) {
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
