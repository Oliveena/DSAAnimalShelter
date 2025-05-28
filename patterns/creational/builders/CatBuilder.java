package patterns.creational.builders;

import models.animals.Cat;

/**
 * Builder for creating {@link Cat} instances.
 * <p>
 * Extends {@link AnimalBuilder} with cat-specific properties such as fur length and indoor/outdoor status.
 */
public class CatBuilder extends AnimalBuilder<Cat, CatBuilder> {
    private String furLength;
    private boolean indoor;

    @Override
    protected CatBuilder self() {
        return this;
    }

    /**
     * Sets the fur length of the cat.
     *
     * @param furLength description of fur length (e.g., "short", "long")
     * @return this builder instance for chaining
     */
    public CatBuilder setFurLength(String furLength) {
        this.furLength = furLength;
        return this;
    }

    /**
     * Sets whether the cat is an indoor cat.
     *
     * @param indoor true if the cat stays indoors; false otherwise
     * @return this builder instance for chaining
     */
    public CatBuilder setIndoor(boolean indoor) {
        this.indoor = indoor;
        return this;
    }

    /**
     * Builds a new {@link Cat} instance with the configured properties.
     *
     * @return a new {@link Cat} object
     */
    @Override
    public Cat build() {
        Cat cat = new Cat(name, age, breed, furLength, indoor);
        cat.setMedicalRecord(medicalRecord);
        return cat;
    }
}
