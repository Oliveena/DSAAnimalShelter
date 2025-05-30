package patterns.creational.builders;

import models.animals.Dog;

/**
 * Builder for creating instances of {@link Dog} with fluent configuration.
 * <p>
 * Supports setting common animal attributes inherited from {@link AnimalBuilder}
 * as well as specific properties like training status.
 */
public class DogBuilder extends AnimalBuilder<Dog, DogBuilder> {
    private boolean isTrained;

    /**
     * Returns the current builder instance for method chaining.
     *
     * @return this {@link DogBuilder} instance
     */
    @Override
    protected DogBuilder self() {
        return this;
    }

    /**
     * Sets whether the dog is trained.
     *
     * @param trained true if the dog is trained; false otherwise
     * @return this {@link DogBuilder} instance for chaining
     */
    public DogBuilder setTrained(boolean trained) {
        this.isTrained = trained;
        return this;
    }

    /**
     * Builds and returns a {@link Dog} instance with the configured properties.
     * Medical records, if set, will be attached to the dog.
     *
     * @return a new {@link Dog} instance
     */
    @Override
    public Dog build() {
        Dog dog = new Dog(name, age, breed, isTrained);
        dog.setMedicalRecord(medicalRecord);
        return dog;
    }
}
