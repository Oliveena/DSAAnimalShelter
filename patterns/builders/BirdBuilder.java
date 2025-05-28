package patterns.builders;

import models.animals.Bird;

/**
 * Builder for creating {@link Bird} instances.
 * <p>
 * Extends the generic {@link AnimalBuilder} with bird-specific properties such as
 * breed and ability to fly.
 */
public class BirdBuilder extends AnimalBuilder<Bird, BirdBuilder> {
    private String breed;
    private boolean canFly;

    @Override
    protected BirdBuilder self() {
        return this;
    }

    /**
     * Sets the breed of the bird.
     *
     * @param breed the breed name
     * @return this builder instance for chaining
     */
    public BirdBuilder setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    /**
     * Sets whether the bird can fly.
     *
     * @param canFly true if the bird can fly, false otherwise
     * @return this builder instance for chaining
     */
    public BirdBuilder setCanFly(boolean canFly) {
        this.canFly = canFly;
        return this;
    }

    /**
     * Builds a new {@link Bird} instance with the configured properties.
     *
     * @return a new {@link Bird} object
     */
    @Override
    public Bird build() {
        return new Bird(name, age, breed, canFly, medicalRecord);
    }
}
