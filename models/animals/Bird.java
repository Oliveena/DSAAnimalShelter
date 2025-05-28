package models.animals;

import models.Adoptable;
import models.MedicalRecord;

/**
 * Represents a bird in the adoption system.
 * <p>
 * This class extends {@link Animal} and provides additional properties specific to birds,
 * such as breed and whether the bird can fly.
 */
public class Bird extends Animal implements Adoptable {
    private boolean canFly;

    /**
     * Constructs a new bird with the given name, age, breed, and flying capability.
     * <p>
     * The animal's ID is automatically generated via the {@link Animal} constructor.
     *
     * @param name the name of the bird
     * @param age the age of the bird
     * @param breed the breed of the bird
     * @param canFly whether the bird can fly
     */
    public Bird(String name, int age, String breed, boolean canFly) {
        super(name, age, Species.BIRD, breed);
        this.canFly = canFly;
    }

    /**
     * Returns detailed information about the bird, including breed and flying capability.
     * <p>
     * This method overrides the {@link Animal#getDetails()} method to provide more specific details
     * about the bird, including breed and whether it can fly.
     *
     * @return a string representing the bird's details
     */
    @Override
    public String getDetails() {
        return "Name: " + getName() +
                " | Age: " + getAge() +
                " | Type: " + getSpecies() +
                " | Breed: " + getBreed() +
                " | Can Fly: " + (canFly ? "Yes" : "No");
    }

    @Override
    public Species getSpecies() {
        return Species.BIRD;
    }

    @Override
    public String getBreed() {
        return this.breed;
    }

    @Override
    public void adopt() {

    }

    @Override
    public void returnToShelter() {
        System.out.println(name + " the bird has been returned to the shelter.");
    }


    /**
     * Returns a string representation of the bird, including its breed and flying capability.
     *
     * @return a string representation of the bird
     */
    public Bird(String name, int age, String breed, boolean canFly, MedicalRecord medicalRecord) {
        super(name, age, Species.BIRD, breed);
        this.canFly = canFly;
    }
}
