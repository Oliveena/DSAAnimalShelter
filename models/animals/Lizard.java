package models.animals;

import models.Adoptable;

/**
 * Represents a lizard in the adoption system.
 * <p>
 * This class extends {@link Animal} and provides additional properties specific to lizards,
 * such as breed and whether the lizard is venomous.
 */
public class Lizard extends Animal implements Adoptable {
    private boolean isVenomous;

    /**
     * Constructs a new lizard with the given name, age, breed, and venomous status.
     * <p>
     * The animal's ID is automatically generated via the {@link Animal} constructor.
     *
     * @param name the name of the lizard
     * @param age the age of the lizard
     * @param breed the breed of the lizard
     * @param isVenomous whether the lizard is venomous
     */
    public Lizard(String name, int age, String breed, String species, boolean isVenomous) {
        super(name, age, "Lizard", species);
        this.breed = breed;
        this.isVenomous = isVenomous;
    }

    /**
     * Returns detailed information about the lizard, including its breed and venomous status.
     * <p>
     * This method overrides the {@link Animal#getDetails()} method to provide more specific details
     * about the lizard, such as its breed and whether it is venomous.
     *
     * @return a string representing the lizard's details
     */
    @Override
    public String getDetails() {
        return String.format("Lizard [ID: %s, Name: %s, Age: %d, Breed: %s, Venomous: %s]",
                id, name, age, breed, isVenomous ? "Yes" : "No");
    }

    @Override
    public String getSpecies() {
        return "Lizard";
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


}
