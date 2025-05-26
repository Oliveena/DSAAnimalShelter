package models.animals;

import models.Adoptable;
import models.MedicalRecord;

import java.util.UUID;

/**
 * Represents a generic animal in the adoption system.
 * <p>
 * This abstract class implements the {@link Adoptable} interface and provides basic
 * properties and functionality for animals, such as ID, name, and age. Specific
 * types of animals (e.g., Cat, Dog) should extend this class and implement the
 * {@code getSpecies()} and {@code getDetails()} methods.
 * <p>
 * This class also implements {@link Comparable} to allow sorting of animals by their names.
 */
public abstract class Animal implements Adoptable, Comparable<Animal> {

    protected String id;
    protected String name;
    protected int age;
    // TODO: turn species into an ENUM
    protected String species;
    protected String breed;
    protected MedicalRecord medicalRecord;


    /**
     * Constructs a new animal with the given name and age.
     * <p>
     * The animal's ID is automatically generated as a random UUID.
     *
     * @param name the name of the animal
     * @param age the age of the animal
     */
    public Animal(String name, int age, String species, String breed) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.age = age;
        this.species = species;
        this.breed = breed;
    }

    /**
     * Returns the unique ID of the animal.
     *
     * @return the ID of the animal
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the animal.
     *
     * @return the name of the animal
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the age of the animal.
     *
     * @return the age of the animal
     */
    public int getAge() {
        return age;
    }

    /**
     * Abstract method to get detailed information about the animal.
     * <p>
     * This method must be implemented by subclasses to return a string with additional
     * details about the animal, such as breed, health status, and adoption status.
     *
     * @return the details of the animal
     */
    public abstract String getDetails();

    /**
     * Compares the current animal with another animal based on their names.
     * <p>
     * This implementation performs a case-insensitive comparison of the animal names.
     *
     * @param other the other animal to compare to
     * @return a negative integer, zero, or a positive integer as this animal's name
     *         is lexicographically less than, equal to, or greater than the specified animal's name
     */

    @Override
    public int compareTo(Animal other) {
        return this.name.compareToIgnoreCase(other.name);  // default sort with name
    }

    public void setMedicalRecord(MedicalRecord record) {
        this.medicalRecord = record;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public abstract String getSpecies();

    public abstract String getBreed();
}
