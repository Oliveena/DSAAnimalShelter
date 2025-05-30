package models;

import models.animals.Animal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents an individual who adopts animals from the shelter.
 * <p>
 * An adopter can:
 * <ul>
 *   <li>Adopt multiple animals</li>
 *   <li>Store and retrieve their adoption preferences</li>
 *   <li>Search through their adopted animals</li>
 * </ul>
 *
 * @see AdopterPreferences
 */
public class Adopter {
    private final String name;
    private final List<Animal> adoptedAnimals = new ArrayList<>();
    private AdopterPreferences preferences;

    /**
     * Constructs a new {@code Adopter} with the given name.
     *
     * @param name the adopter's name
     * @throws IllegalArgumentException if {@code name} is null or empty
     */
    public Adopter(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Adopter name cannot be null or empty.");
        }
        this.name = name;
    }

    /**
     * Sets a preference key-value pair for this adopter.
     * <p>If preferences are not yet initialized, this method initializes them.</p>
     *
     * @param key   the preference key
     * @param value the preference value
     */
    public void setPreference(String key, String value) {
        if (preferences == null) {
            preferences = new AdopterPreferences();
        }
        preferences.setPreference(key, value);
    }

    /**
     * Adds an animal to the list of animals adopted by this adopter.
     *
     * @param animal the animal to adopt; if {@code null}, the method does nothing
     */
    public void adoptAnimal(Animal animal) {
        if (animal != null) {
            adoptedAnimals.add(animal);
        }
    }

    /**
     * Returns the adopter's name.
     *
     * @return the adopter's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns an unmodifiable list of animals adopted by this adopter.
     *
     * @return an unmodifiable {@link List} of adopted {@link Animal} objects
     */
    public List<Animal> getAdoptedAnimals() {
        return Collections.unmodifiableList(adoptedAnimals);
    }

    /**
     * Searches adopted animals by partial name match, ignoring case.
     *
     * @param name part or full name of the animal to search for
     * @return a list of adopted animals whose names contain the search string;
     *         returns an empty list if {@code name} is null or empty
     */
    public List<Animal> searchAdoptedAnimalsByName(String name) {
        if (name == null || name.isEmpty()) {
            return new ArrayList<>();
        }
        String lowerName = name.toLowerCase();
        return adoptedAnimals.stream()
                .filter(a -> a.getName().toLowerCase().contains(lowerName))
                .collect(Collectors.toList());
    }

    /**
     * Returns the total number of animals adopted by this adopter.
     *
     * @return the count of adopted animals
     */
    public int getAdoptedAnimalCount() {
        return adoptedAnimals.size();
    }

    /**
     * Returns a string representation of this adopter,
     * including the adopter's name and number of adopted animals.
     *
     * @return a string describing the adopter
     */
    @Override
    public String toString() {
        return "Adopter{name='" + name + "', adoptedAnimalCount=" + adoptedAnimals.size() + "}";
    }

    /**
     * Checks if this adopter is equal to another object.
     * <p>
     * Two adopters are considered equal if their names are equal (case-sensitive).
     *
     * @param o the object to compare with
     * @return {@code true} if the objects are equal, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Adopter)) return false;
        Adopter adopter = (Adopter) o;
        return name.equals(adopter.name);
    }

    /**
     * Returns a hash code value for the adopter based on the adopter's name.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Returns the adopter's preferences.
     *
     * @return the adopter's preferences, or {@code null} if none are set
     */
    public AdopterPreferences getPreferences() {
        return preferences;
    }

    /**
     * Sets the adopter's preferences.
     *
     * @param preferences the preferences to set
     */
    public void setPreferences(AdopterPreferences preferences) {
        this.preferences = preferences;
    }
}
