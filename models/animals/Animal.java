package models.animals;

import models.Adoptable;
import models.MedicalRecord;
import models.Treatable;

import java.util.UUID;

/**
 * Represents a generic animal in the adoption system.
 * <p>
 * This abstract class implements the {@link Adoptable} and {@link Treatable} interfaces,
 * providing basic properties such as ID, name, age, species, and breed.
 * Specific animal types (e.g., {@code Cat}, {@code Dog}) should extend this class and implement
 * the abstract methods {@link #getSpecies()}, {@link #getDetails()}, and {@link #getBreed()}.
 * <p>
 * Animals are comparable by their names (case-insensitive) to support sorting.
 */
public abstract class Animal implements Adoptable, Treatable, Comparable<Animal> {

    protected String id;
    protected String name;
    protected int age;
    // TODO: add gender field
    protected Species species;
    protected String breed;
    protected MedicalRecord medicalRecord;

    /**
     * Constructs a new {@code Animal} instance with the specified name, age, species, and breed.
     * The ID is automatically generated as a random UUID string.
     *
     * @param name    the animal's name
     * @param age     the animal's age
     * @param species the species of the animal
     * @param breed   the breed of the animal
     */
    public Animal(String name, int age, Species species, String breed) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.age = age;
        this.species = species;
        this.breed = breed;
    }

    /**
     * Returns the unique identifier (UUID string) of the animal.
     *
     * @return the animal's ID
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the animal.
     *
     * @return the animal's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the age of the animal.
     *
     * @return the animal's age
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns detailed information about the animal.
     * <p>
     * Subclasses must override this method to provide additional details such as breed,
     * health status, and adoption status.
     *
     * @return a string representing detailed information about the animal
     */
    public abstract String getDetails();

    /**
     * Compares this animal with the specified animal for order based on their names,
     * ignoring case considerations.
     *
     * @param other the animal to be compared
     * @return a negative integer, zero, or a positive integer as this animal's
     *         name is lexicographically less than, equal to, or greater than the
     *         specified animal's name.
     */
    @Override
    public int compareTo(Animal other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    /**
     * Sets the medical record for this animal.
     *
     * @param record the medical record to associate with this animal
     */
    @Override
    public void addMedicalRecord(MedicalRecord record) {
        this.medicalRecord = record;
    }

    /**
     * Sets the medical record for this animal.
     *
     * @param record the medical record to set
     */
    public void setMedicalRecord(MedicalRecord record) {
        this.medicalRecord = record;
    }

    /**
     * Returns the medical record of this animal.
     *
     * @return the animal's medical record, or {@code null} if none exists
     */
    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    /**
     * Returns the species of the animal.
     *
     * @return the animal's species
     */
    @Override
    public Species getSpecies() {
        return this.species;
    }

    /**
     * Returns the breed of the animal.
     *
     * @return the animal's breed
     */
    public abstract String getBreed();
}
