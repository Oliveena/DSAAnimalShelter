package models;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an adopter's preferences used for matching with adoptable animals.
 * <p>
 * Preferences can include desired species, preferred breed, and maximum age of the animal.
 * This class stores preferences both as typed fields and in a flexible key-value map.
 * When a preference is updated via the map, the corresponding typed field is also updated, and vice versa.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>
 *     AdopterPreferences prefs = new AdopterPreferences();
 *     prefs.setSpecies("Dog");
 *     prefs.setBreed("Beagle");
 *     prefs.setMaxAge(5);
 *     prefs.setPreference("color", "brown");  // additional preference stored only in map
 * </pre>
 *
 * @see models.Adopter
 */
public class AdopterPreferences {
    private String species;
    private String breed;
    private Integer maxAge;

    private final Map<String, String> preferences = new HashMap<>();

    /**
     * Default constructor initializing empty preferences.
     */
    public AdopterPreferences() {}

    /**
     * Constructs an {@code AdopterPreferences} with given species, breed, and maximum age.
     *
     * @param species the desired species (e.g., "Dog", "Cat")
     * @param breed the preferred breed
     * @param maxAge the maximum age of the desired animal
     */
    public AdopterPreferences(String species, String breed, Integer maxAge) {
        this.species = species;
        this.breed = breed;
        this.maxAge = maxAge;
    }

    /**
     * Returns the preferred species.
     *
     * @return species preference, or {@code null} if not set
     */
    public String getSpecies() {
        return species;
    }

    /**
     * Sets the preferred species and updates the internal preferences map.
     *
     * @param species the species to prefer (case insensitive)
     */
    public void setSpecies(String species) {
        this.species = species;
        preferences.put("species", species != null ? species.toLowerCase() : null);
    }

    /**
     * Returns the preferred breed.
     *
     * @return breed preference, or {@code null} if not set
     */
    public String getBreed() {
        return breed;
    }

    /**
     * Sets the preferred breed and updates the internal preferences map.
     *
     * @param breed the breed to prefer (case insensitive)
     */
    public void setBreed(String breed) {
        this.breed = breed;
        preferences.put("breed", breed != null ? breed.toLowerCase() : null);
    }

    /**
     * Returns the maximum preferred age.
     *
     * @return maximum age preference, or {@code null} if not set
     */
    public Integer getMaxAge() {
        return maxAge;
    }

    /**
     * Sets the maximum preferred age and updates the internal preferences map.
     *
     * @param maxAge the maximum age to prefer
     */
    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
        if (maxAge != null) {
            preferences.put("maxage", maxAge.toString());
        } else {
            preferences.remove("maxage");
        }
    }

    /**
     * Sets a preference by key and value.
     * <p>
     * If the key matches one of the main typed fields ("species", "breed", "maxage"),
     * the corresponding setter is invoked to keep fields and map in sync.
     * Other keys are stored only in the map.
     * </p>
     *
     * @param key   the preference key (case insensitive)
     * @param value the preference value (case insensitive), or {@code null} to remove the preference
     */
    public void setPreference(String key, String value) {
        if (key == null) return;
        String lowerKey = key.toLowerCase();

        if (value != null) {
            preferences.put(lowerKey, value.toLowerCase());
        } else {
            preferences.remove(lowerKey);
        }

        switch (lowerKey) {
            case "species":
                setSpecies(value);
                break;
            case "breed":
                setBreed(value);
                break;
            case "maxage":
                try {
                    if (value != null) {
                        setMaxAge(Integer.parseInt(value));
                    } else {
                        setMaxAge(null);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Warning: Invalid maxAge value: " + value);
                }
                break;
            default:
                // No action needed for unknown keys
                break;
        }
    }

    /**
     * Retrieves the preference value associated with the given key.
     *
     * @param key the preference key (case-insensitive)
     * @return the preference value, or {@code null} if not set
     */
    public String getPreference(String key) {
        if (key == null) return null;
        return preferences.get(key.toLowerCase());
    }

    /**
     * Returns a string representation of the adopter preferences.
     *
     * @return string describing species, breed, and max age preferences
     */
    @Override
    public String toString() {
        return "AdopterPreferences{species='" + species + "', breed='" + breed + "', maxAge=" + maxAge + "}";
    }
}
