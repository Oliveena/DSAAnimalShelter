package builders;

import domain.Bird;

/**
 * Builder class for creating instances of {@link Bird}.
 * This class allows incremental construction of a Bird object.
 */
public class BirdBuilder {
    private String name;
    private int age;
    private String breed;
    private boolean canFly;

    /**
     * Constructs a new {@code BirdBuilder} with initial values.
     *
     * @param name   the name of the bird
     * @param age    the age of the bird
     * @param breed  the breed of the bird
     * @param canFly whether the bird can fly
     */
    public BirdBuilder(String name, int age, String breed, boolean canFly) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.canFly = canFly;
    }

    /**
     * Returns the name of the bird being built.
     *
     * @return the bird's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the bird.
     *
     * @param name the bird's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the age of the bird.
     *
     * @return the bird's age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the bird.
     *
     * @param age the bird's age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Returns the breed of the bird.
     *
     * @return the bird's breed
     */
    public String getBreed() {
        return breed;
    }

    /**
     * Sets the breed of the bird.
     *
     * @param breed the bird's breed
     */
    public void setBreed(String breed) {
        this.breed = breed;
    }

    /**
     * Returns whether the bird can fly.
     *
     * @return true if the bird can fly; false otherwise
     */
    public boolean getCanFly() {
        return canFly;
    }

    /**
     * Sets whether the bird can fly.
     *
     * @param canFly true if the bird can fly; false otherwise
     */
    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }

    /**
     * Builds and returns a new {@link Bird} object using the current builder state.
     *
     * @return a new Bird instance
     */
    public Bird build() {
        return new Bird(name, age, breed, canFly);
    }
}
