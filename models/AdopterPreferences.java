package models;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an adopter's preferences used for matching with adoptable animals.
 * <p>
 * Preferences can include:
 * <ul>
 *     <li>Desired species (e.g., Dog, Cat)</li>
 *     <li>Preferred breed</li>
 *     <li>Maximum age of the animal</li>
 * </ul>
 * These preferences are used in systems like {@link util.matching.PreferenceMatcher}
 * to determine the best available match.
 */
public class AdopterPreferences {
    private String species;
    private String breed;
    private Integer maxAge;

    public AdopterPreferences() {}

    public AdopterPreferences(String species, String breed, Integer maxAge) {
        this.species = species;
        this.breed = breed;
        this.maxAge = maxAge;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

        private final Map<String, String> preferences = new HashMap<>();

        public void setPreference(String key, String value) {
            preferences.put(key.toLowerCase(), value.toLowerCase());
        }

        public String getPreference(String key) {
            return preferences.get(key.toLowerCase());
        }


    @Override
    public String toString() {
        return "AdopterPreferences{species='" + species + "', breed='" + breed + "', maxAge=" + maxAge + "}";
    }
}
