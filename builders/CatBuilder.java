package builders;

import logic.Cat;

/**
 * Builder class for creating instances of {@link Cat}.
 * <p>
 * This builder uses a fluent interface, allowing chained method calls:
 *
 * <pre>{@code
 * Cat cat = new CatBuilder()
 *     .setName("Milo")
 *     .setAge(2)
 *     .setFurLength("Short")
 *     .setIndoor(true)
 *     .build();
 * }</pre>
 */
public class CatBuilder {
    private String name;
    private int age;
    private String furLength;
    private boolean isIndoor;

    /**
     * Sets the name of the cat.
     *
     * @param name the cat's name
     * @return the current builder instance
     */
    public CatBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the age of the cat.
     *
     * @param age the cat's age
     * @return the current builder instance
     */
    public CatBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    /**
     * Sets the fur length of the cat (e.g., "Short", "Long").
     *
     * @param furLength the length of the cat's fur
     * @return the current builder instance
     */
    public CatBuilder setFurLength(String furLength) {
        this.furLength = furLength;
        return this;
    }

    /**
     * Specifies whether the cat is an indoor cat.
     *
     * @param indoor true if the cat is indoor; false otherwise
     * @return the current builder instance
     */
    public CatBuilder setIndoor(boolean indoor) {
        this.isIndoor = indoor;
        return this;
    }

    /**
     * Builds and returns a new {@link Cat} instance using the current state.
     *
     * @return a new Cat object
     */
    public Cat build() {
        return new Cat(name, age, furLength, isIndoor);
    }
}
