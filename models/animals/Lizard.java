package models.animals;

import models.MedicalRecord;

/**
 * Represents a lizard in the adoption system.
 */
public class Lizard extends Animal {
    private final boolean isVenomous;

    /**
     * Constructs a new lizard.
     *
     * @param name the name of the lizard
     * @param age the age of the lizard
     * @param breed the breed of the lizard
     * @param isVenomous whether the lizard is venomous
     */
    public Lizard(String name, int age, String breed, boolean isVenomous) {
        super(name, age, Species.LIZARD, breed);
        this.isVenomous = isVenomous;
    }

    @Override
    public String getDetails() {
        return String.format("Lizard [ID: %s, Name: %s, Age: %d, Breed: %s, Venomous: %s]",
                getId(), getName(), age, breed, isVenomous ? "Yes" : "No");
    }

    @Override
    public Species getSpecies() {
        return Species.LIZARD;
    }
    @Override
    public String getBreed() {
        return breed;
    }

    @Override
    public void adopt() {
        System.out.println(getName() + " the lizard has been adopted.");
    }

    @Override
    public void returnToShelter() {
        System.out.println(getName() + " the lizard has been returned to the shelter.");
    }
}
