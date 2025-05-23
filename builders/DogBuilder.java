package builders;

import domain.Dog;

/**
 * Builder class for creating instances of {@link Dog}.
 * <p>
 * This builder uses a fluent interface, allowing method chaining:
 *
 * <pre>{@code
 * Dog dog = new DogBuilder()
 *     .setName("Buddy")
 *     .setAge(3)
 *     .setBreed("Golden Retriever")
 *     .setTrained(true)
 *     .build();
 * }</pre>
 */
public class DogBuilder {
    private String name;
    private int age;
    private String breed;
    private boolean isTrained;

    /**
     * Sets the name of the dog.
     *
     * @param name the dog's name
     * @return the current builder instance
     */
    public DogBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the age of the dog.
     *
     * @param age the dog's age
     * @return the current builder instance
     */
    public DogBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    /**
     * Sets the breed of the dog.
     *
     * @param breed the dog's breed
     * @return the current builder instance
     */
    public DogBuilder setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    /**
     * Specifies whether the dog is trained.
     *
     * @param trained true if the dog is trained; false otherwise
     * @return the current builder instance
     */
    public DogBuilder setTrained(boolean trained) {
        this.isTrained = trained;
        return this;
    }

    /**
     * Builds and returns a new {@link Dog} instance using the current state.
     *
     * @return a new Dog object
     */
    public Dog build() {
        return new Dog(name, age, breed, isTrained);
    }
}
