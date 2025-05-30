package models.animals;

import models.Adoptable;

/**
 * Represents a cat in the adoption system.
 * <p>
 * This class extends {@link Animal} and provides additional properties specific to cats,
 * such as fur length and whether the cat is indoor or not.
 */
public class Cat extends Animal implements Adoptable {
    private String furLength;
    private boolean isIndoor;

    /**
     * Constructs a new cat with the given name, age, fur length, and indoor status.
     * <p>
     * The animal's ID is automatically generated via the {@link Animal} constructor.
     *
     * @param name the name of the cat
     * @param age the age of the cat
     * @param breed the breed of the cat
     * @param furLength the length of the cat's fur (e.g., "Short", "Long")
     * @param isIndoor whether the cat is an indoor cat
     */
    public Cat(String name, int age, String breed, String furLength, boolean isIndoor) {
        super(name, age, Species.CAT, breed);
        this.furLength = furLength;
        this.isIndoor = isIndoor;
    }

    /**
     * Returns detailed information about the cat, including its fur length and indoor status.
     *
     * @return a string representing the cat's details
     */
    @Override
    public String getDetails() {
        return String.format("Cat [ID: %s, Name: %s, Age: %d, Fur Length: %s, Indoor: %s]",
                getId(), getName(), getAge(), furLength, isIndoor ? "Yes" : "No");
    }

    @Override
    public String getBreed() {
        return this.breed;
    }

    @Override
    public void adopt() {
        // TODO: implement adoption logic
        System.out.println(getName() + " the cat has been adopted.");
    }

    @Override
    public void returnToShelter() {
        System.out.println(getName() + " the cat has been returned to the shelter.");
    }
}
