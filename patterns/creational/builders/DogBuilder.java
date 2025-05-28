package patterns.creational.builders;

import models.animals.Dog;

/**
 * Builder for creating {@link Dog} instances.
 * <p>
 * Extends {@link AnimalBuilder} with dog-specific properties such as breed and training status.
 * <p>
 * Demonstrates use of the Curiously Recurring Generic Pattern (CRGP) to enable fluent chaining.
 */
public class DogBuilder extends AnimalBuilder<Dog, DogBuilder> {
    private String breed;
    private boolean isTrained;

    @Override
    protected DogBuilder self() {
        return this;
    }

    /**
     * Sets the breed of the dog.
     *
     * @param breed the breed name
     * @return this builder instance for chaining
     */
    public DogBuilder setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    /**
     * Sets whether the dog is trained.
     *
     * @param trained true if the dog is trained; false otherwise
     * @return this builder instance for chaining
     */
    public DogBuilder setTrained(boolean trained) {
        this.isTrained = trained;
        return this;
    }

    /**
     * Builds a new {@link Dog} instance with the configured properties.
     *
     * @return a new {@link Dog} object
     */
    @Override
    public Dog build() {
        Dog dog = new Dog(name, age, species, breed, isTrained);
        dog.setMedicalRecord(medicalRecord);
        return dog;
    }
}
