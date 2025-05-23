package builders;

import domain.Lizard;

/**
 * Builder class for creating instances of {@link Lizard}.
 * <p>
 * This builder uses a fluent interface, allowing chained method calls:
 *
 * <pre>{@code
 * Lizard lizard = new LizardBuilder()
 *     .setName("Spike")
 *     .setAge(5)
 *     .setBreed("Monitor")
 *     .setVenomous(false)
 *     .build();
 * }</pre>
 */
public class LizardBuilder {
    private String name;
    private int age;
    private String breed;
    private boolean isVenomous;

    /**
     * Sets the name of the lizard.
     *
     * @param name the lizard's name
     * @return the current builder instance
     */
    public LizardBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the age of the lizard.
     *
     * @param age the lizard's age
     * @return the current builder instance
     */
    public LizardBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    /**
     * Sets the breed (species/type) of the lizard.
     *
     * @param breed the breed or type of the lizard
     * @return the current builder instance
     */
    public LizardBuilder setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    /**
     * Specifies whether the lizard is venomous.
     *
     * @param isVenomous true if the lizard is venomous; false otherwise
     * @return the current builder instance
     */
    public LizardBuilder setVenomous(boolean isVenomous) {
        this.isVenomous = isVenomous;
        return this;
    }

    /**
     * Builds and returns a new {@link Lizard} instance using the current builder state.
     *
     * @return a new Lizard object
     */
    public Lizard build() {
        return new Lizard(name, age, breed, isVenomous);
    }
}
