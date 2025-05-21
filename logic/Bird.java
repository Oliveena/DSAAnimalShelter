package logic;

/**
 * Represents a bird in the adoption system.
 * <p>
 * This class extends {@link Animal} and provides additional properties specific to birds,
 * such as breed and whether the bird can fly.
 */
public class Bird extends Animal {
    private String breed;
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
        super(name, age);  // Call the Animal constructor
        this.breed = breed;
        this.canFly = canFly;
    }

    /**
     * Returns the unique ID of the bird.
     * <p>
     * This method simply calls the {@link Animal#getId()} method to retrieve the ID.
     *
     * @return the ID of the bird
     */
    @Override
    public String getId() {
        return super.getId();
    }

    /**
     * Returns the name of the bird.
     * <p>
     * This method simply calls the {@link Animal#getName()} method to retrieve the name.
     *
     * @return the name of the bird
     */
    @Override
    public String getName() {
        return super.getName();
    }

    /**
     * Returns the age of the bird.
     * <p>
     * This method simply calls the {@link Animal#getAge()} method to retrieve the age.
     *
     * @return the age of the bird
     */
    @Override
    public int getAge() {
        return super.getAge();
    }

    /**
     * Returns the type of the animal.
     * <p>
     * In this case, returns "Bird", as this is the specific type of the animal.
     *
     * @return the type of the bird
     */
    @Override
    public String getType() {
        return "Bird";
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
        return "Breed: " + breed + " | Can Fly: " + (canFly ? "Yes" : "No");
    }

    /**
     * Returns a string representation of the bird, including its breed and flying capability.
     *
     * @return a string representation of the bird
     */
    @Override
    public String toString() {
        return "Bird{" +
                "breed='" + breed + '\'' +
                ", canFly=" + canFly +
                '}';
    }
}
