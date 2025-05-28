package models;

import models.animals.Species;

/**
 * Interface representing any entity that can be adopted from the shelter system.
 * <p>
 * Most commonly implemented by animal classes (e.g., Dog, Cat), this interface provides
 * the necessary metadata for adoption processing and matching.
 */
public interface Adoptable {

    /**
     * Returns the unique identifier of the adoptable entity.
     *
     * @return unique ID
     */
    String getId();

    /**
     * Returns the name of the adoptable entity.
     *
     * @return entity name
     */
    String getName();

    /**
     * Returns the current age of the adoptable entity.
     *
     * @return age in years
     */
    int getAge();

    /**
     * Returns the species of the adoptable entity (e.g., Dog, Cat).
     *
     * @return species name
     */
    Species getSpecies();

    /**
     * Returns the breed of the adoptable entity.
     *
     * @return breed name
     */
    String getBreed();

    /**
     * Returns a detailed description of the adoptable entity.
     * Could include personality traits, medical conditions, etc.
     *
     * @return full detail string
     */
    String getDetails();

    /**
     * Marks the adoptable entity as adopted (e.g., update internal state or flags).
     */
    void adopt();

    /**
     * Returns the adoptable entity back to the shelter system.
     */
    void returnToShelter();
}
