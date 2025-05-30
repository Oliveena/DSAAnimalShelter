package models.animals;

import models.MedicalRecord;

/**
 * Represents a bird in the adoption system.
 * <p>
 * This class extends {@link Animal} and provides additional properties specific to birds,
 * such as breed and whether the bird can fly.
 */
public class Bird extends Animal {

    private boolean canFly;

    /**
     * Constructs a new bird with the given name, age, breed, and flying capability.
     * <p>
     * The animal's ID is automatically generated via the {@link Animal} constructor.
     *
     * @param name  the name of the bird
     * @param age   the age of the bird
     * @param breed the breed of the bird
     * @param canFly whether the bird can fly
     */
    public Bird(String name, int age, String breed, boolean canFly) {
        super(name, age, Species.BIRD, breed);
        this.canFly = canFly;
    }

    /**
     * Constructs a new bird with a medical record.
     *
     * @param name          the name of the bird
     * @param age           the age of the bird
     * @param breed         the breed of the bird
     * @param canFly        whether the bird can fly
     * @param medicalRecord the medical record of the bird
     */
    public Bird(String name, int age, String breed, boolean canFly, MedicalRecord medicalRecord) {
        this(name, age, breed, canFly);
        this.medicalRecord = medicalRecord;
    }

    /**
     * Returns detailed information about the bird, including breed and flying capability.
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
    public String getBreed() {
        return this.breed;
    }

    @Override
    public void adopt() {
        System.out.println(getName() + " has been adopted.");
    }

    @Override
    public void returnToShelter() {
        System.out.println(getName() + " the bird has been returned to the shelter.");
    }
}
