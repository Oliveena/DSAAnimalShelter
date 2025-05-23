package models;
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
     * @param furLength the length of the cat's fur (e.g., "Short", "Long")
     * @param isIndoor whether the cat is an indoor cat
     */
    public Cat(String name, int age, String furLength, boolean isIndoor) {
        super(name, age);  // Call the Animal constructor
        this.furLength = furLength;
        this.isIndoor = isIndoor;
    }

    /**
     * Returns the type of the animal.
     * <p>
     * In this case, it returns "Cat", as this is the specific type of the animal.
     *
     * @return the type of the cat
     */
    @Override
    public String getType() {
        return "Cat";
    }

    /**
     * Returns detailed information about the cat, including its fur length and indoor status.
     * <p>
     * This method overrides the {@link Animal#getDetails()} method to provide more specific details
     * about the cat, such as its fur length and whether it is indoor or not.
     *
     * @return a string representing the cat's details
     */
    @Override
    public String getDetails() {
        return String.format("Cat [ID: %s, Name: %s, Age: %d, Fur Length: %s, Indoor: %s]",
                id, name, age, furLength, isIndoor ? "Yes" : "No");
    }

    @Override
    public void adopt() {

    }

    @Override
    public void returnToShelter() {
        System.out.println(name + " the cat has been returned to the shelter.");
    }

}
