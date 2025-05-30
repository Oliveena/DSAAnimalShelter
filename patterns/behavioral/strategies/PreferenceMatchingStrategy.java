package patterns.behavioral.strategies;

import models.Adopter;
import models.AdopterPreferences;
import models.AnimalRegistry;
import models.ShelterQueue;
import models.animals.Animal;

import java.util.List;
import java.util.Comparator;

/**
 * A strategy for selecting animals based on how well they match an adopter's preferences.
 * <p>
 * This strategy evaluates all available animals and assigns a score to each based on:
 * <ul>
 *     <li>Species match</li>
 *     <li>Breed match</li>
 *     <li>Maximum age requirement</li>
 * </ul>
 * The animal with the highest match score is selected.
 *
 * @see AnimalMatchingStrategy
 * @see AdopterPreferences
 */
public class PreferenceMatchingStrategy implements AnimalMatchingStrategy {

    /**
     * Selects the most suitable animal for the given adopter based on their preferences.
     *
     * @param adopter  the adopter whose preferences are considered
     * @param registry the registry containing all available animals
     * @param queue    the shelter's queue of animals (unused in this strategy)
     * @return the animal with the highest match score, or {@code null} if no match is found
     */
    @Override
    public Animal selectAnimal(Adopter adopter, AnimalRegistry registry, ShelterQueue queue) {
        List<Animal> animals = registry.getAllAnimals();
        return findBestMatch(adopter, animals);
    }

    /**
     * Finds the animal that best matches the adopter's preferences.
     *
     * @param adopter          the adopter
     * @param availableAnimals list of available animals
     * @return the best matching animal, or {@code null} if none match
     */
    private Animal findBestMatch(Adopter adopter, List<Animal> availableAnimals) {
        AdopterPreferences preferences = adopter.getPreferences();
        if (preferences == null || availableAnimals == null || availableAnimals.isEmpty()) {
            return null;
        }

        // Use parallel stream for efficiency in large shelters
        return availableAnimals.parallelStream()
                .max(Comparator.comparingInt(animal -> getScore(animal, preferences)))
                .orElse(null);
    }

    /**
     * Computes a match score between an animal and the adopter's preferences.
     * <p>
     * The score ranges from 0 to 3 based on how many criteria the animal satisfies.
     *
     * @param animal      the animal to evaluate
     * @param preferences the adopter's preferences
     * @return the match score
     */
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
