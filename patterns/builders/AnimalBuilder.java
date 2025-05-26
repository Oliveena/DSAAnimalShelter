package patterns.builders;

import models.animals.Animal;
import models.MedicalRecord;
import java.util.logging.Logger;
import patterns.factories.LogFactory;

/**
 * Abstract base builder for constructing {@link Animal} objects using the Builder pattern.
 * <p>
 * Uses the Curiously Recurring Generic Pattern (CRGP) to allow method chaining
 * with concrete subclasses returning their own type.
 * <p>
 * Provides common properties for all animals, such as name, age, species, breed, and medical record.
 *
 * @param <T> the type of {@link Animal} this builder constructs
 * @param <B> the concrete builder type extending this class (used for fluent method chaining)
 */
public abstract class AnimalBuilder<T extends Animal, B extends AnimalBuilder<T, B>> {
    private static final Logger logger = LogFactory.getLogger(AnimalBuilder.class);

    protected String name;
    protected int age;
    protected MedicalRecord medicalRecord;
    protected String species;
    protected String breed;

    /**
     * Returns the concrete builder instance.
     * <p>
     * Used internally to enable fluent method chaining in subclasses.
     *
     * @return the builder instance of type B
     */
    protected abstract B self();

    /**
     * Sets the animal's name.
     *
     * @param name the name of the animal
     * @return this builder instance for chaining
     */
    public B setName(String name) {
        this.name = name;
        logger.fine(STR."Set name: \{name}");
        return self();
    }

    /**
     * Sets the animal's age.
     *
     * @param age the age of the animal in years
     * @return this builder instance for chaining
     */
    public B setAge(int age) {
        this.age = age;
        logger.fine(STR."Set age: \{age}");
        return self();
    }

    /**
     * Sets the animal's medical record.
     *
     * @param record the {@link MedicalRecord} object containing medical info
     * @return this builder instance for chaining
     */
    public B setMedicalRecord(MedicalRecord record) {
        this.medicalRecord = record;
        logger.fine("Set medical record");
        return self();
    }

    /**
     * Sets the species of the animal.
     *
     * @param species the species name (e.g., "Canine", "Feline")
     * @return this builder instance for chaining
     */
    public B setSpecies(String species) {
        this.species = species;
        logger.fine(STR."Set species: \{species}");
        return self();
    }

    /**
     * Sets the breed of the animal.
     *
     * @param breed the breed name (e.g., "Labrador", "Siamese")
     * @return this builder instance for chaining
     */
    public B setBreed(String breed) {
        this.breed = breed;
        logger.fine(STR."Set breed: \{breed}");
        return self();
    }

    /**
     * Builds and returns the constructed animal instance.
     * <p>
     * Concrete subclasses must implement this method.
     *
     * @return a new instance of T representing the built animal
     */
    public abstract T build();
}
