package models;

/**
 * Represents a dog in the adoption system.
 * <p>
 * This class extends {@link Animal} and provides additional properties specific to dogs,
 * such as breed and whether the dog is trained.
 */
public class Dog extends Animal implements Adoptable {
    private String breed;
    private boolean isTrained;

    /**
     * Constructs a new dog with the given name, age, breed, and training status.
     * <p>
     * The animal's ID is automatically generated via the {@link Animal} constructor.
     *
     * @param name the name of the dog
     * @param age the age of the dog
     * @param breed the breed of the dog
     * @param isTrained whether the dog is trained
     */
    public Dog(String name, int age, String breed, boolean isTrained) {
        super(name, age);  // Call the Animal constructor
        this.breed = breed;
        this.isTrained = isTrained;
    }

    /**
     * Returns the type of the animal.
     * <p>
     * In this case, it returns "Dog", as this is the specific type of the animal.
     *
     * @return the type of the dog
     */
    @Override
    public String getType() {
        return "Dog";
    }

    /**
     * Returns detailed information about the dog, including its breed and training status.
     * <p>
     * This method overrides the {@link Animal#getDetails()} method to provide more specific details
     * about the dog, such as its breed and whether it is trained.
     *
     * @return a string representing the dog's details
     */
    @Override
    public String getDetails() {
        return String.format("Dog [ID: %s, Name: %s, Age: %d, Breed: %s, Trained: %s]",
                id, name, age, breed, isTrained ? "Yes" : "No");
    }

    @Override
    public void adopt() {

    }

    @Override
    public void returnToShelter() {
        System.out.println(name + " the dog has been returned to the shelter.");
    }

    @Override
    public void adopt() {

    }

}
