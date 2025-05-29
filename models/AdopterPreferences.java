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
        preferences.put("species", species != null ? species.toLowerCase() : null);
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
        preferences.put("breed", breed != null ? breed.toLowerCase() : null);
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
        if (maxAge != null) {
            preferences.put("maxage", maxAge.toString());
        } else {
            preferences.remove("maxage");
        }
    }

    private final Map<String, String> preferences = new HashMap<>();

    /**
     * Sets a preference by key and value.
     * Also updates the corresponding typed field if the key matches.
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
                // For other keys, just store in map
                break;
        }
    }

    public String getPreference(String key) {
        if (key == null) return null;
        return preferences.get(key.toLowerCase());
    }

    @Override
    public String toString() {
        return "AdopterPreferences{species='" + species + "', breed='" + breed + "', maxAge=" + maxAge + "}";
    }
}
