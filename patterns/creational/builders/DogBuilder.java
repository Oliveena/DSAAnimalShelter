package patterns.creational.builders;

import models.animals.Dog;
import models.animals.Species;

/**
 * A builder class for constructing {@link Dog} instances using the fluent builder pattern.
 * <p>
 * This builder extends {@link AnimalBuilder} and adds support for dog-specific attributes,
 * such as training status.
 *
 * <pre>{@code
 * Dog dog = new DogBuilder()
 *     .setName("Buddy")
 *     .setAge(3)
 *     .setBreed("Labrador")
 *     .setTrained(true)
 *     .setMedicalRecord(record)
 *     .build();
 * }</pre>
 *
 * @see Dog
 * @see AnimalBuilder
 */
public class DogBuilder extends AnimalBuilder<Dog, DogBuilder> {

    private boolean isTrained;

    /**
     * Returns the current instance of the builder for fluent method chaining.
     *
     * @return the current builder instance
     */
    @Override
    protected DogBuilder self() {
        return this;
    }

    /**
     * Sets whether the dog is trained.
     *
     * @param trained {@code true} if the dog is trained, {@code false} otherwise
     * @return the current builder instance
     */
    public DogBuilder setTrained(boolean trained) {
        this.isTrained = trained;
        return this;
    }

    /**
     * Builds a new {@link Dog} instance using the configured attributes.
     * <p>
     * The medical record (if set) is also assigned after object construction.
     *
     * @return a fully constructed {@code Dog} object
     */
    @Override
    public Dog build() {
        Dog dog = new Dog(name, age, breed, isTrained);
        dog.setMedicalRecord(medicalRecord);
        return dog;
    }
}
