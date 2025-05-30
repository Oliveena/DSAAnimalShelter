package patterns.structural.decorators;

import models.MedicalRecord;
import models.animals.Animal;
import models.animals.Species;

/**
 * Abstract base decorator for {@link Animal} instances.
 * <p>
 * Implements the Decorator pattern by wrapping an existing {@code Animal} object
 * and allowing subclasses to extend or modify its behavior dynamically without altering the original class.
 * <p>
 * This class delegates all method calls to the wrapped {@code Animal} instance by default,
 * but subclasses can override specific methods to add new behavior.
 */
public abstract class AnimalDecorator extends Animal {
    /** The wrapped {@code Animal} instance being decorated. */
    protected final Animal decoratedAnimal;

    /**
     * Constructs a new {@code AnimalDecorator} wrapping the specified {@link Animal}.
     *
     * @param animal the animal instance to decorate; must not be {@code null}
     * @throws IllegalArgumentException if {@code animal} is {@code null}
     */
    public AnimalDecorator(Animal animal) {
        super(animal.getName(), animal.getAge(), animal.getSpecies(), animal.getBreed());
        if (animal == null) {
            throw new IllegalArgumentException("Decorated animal must not be null.");
        }
        this.decoratedAnimal = animal;
    }

    /**
     * Returns detailed information about the animal.
     * Delegates to the wrapped animal by default.
     *
     * @return details string of the animal
     */
    @Override
    public String getDetails() {
        return decoratedAnimal.getDetails();
    }

    /**
     * Returns the animal to the shelter.
     * Delegates to the wrapped animal by default.
     */
    @Override
    public void returnToShelter() {
        decoratedAnimal.returnToShelter();
    }

    /**
     * Marks the animal as adopted.
     * Delegates to the wrapped animal by default.
     */
    @Override
    public void adopt() {
        decoratedAnimal.adopt();
    }

    /**
     * Returns the species of the animal.
     * Delegates to the wrapped animal by default.
     *
     * @return the animal's species
     */
    @Override
    public Species getSpecies() {
        return decoratedAnimal.getSpecies();
    }

    /**
     * Returns the breed of the animal.
     * Delegates to the wrapped animal by default.
     *
     * @return the animal's breed
     */
    @Override
    public String getBreed() {
        return decoratedAnimal.getBreed();
    }

    /**
     * Adds a medical record to the animal.
     * Delegates to the wrapped animal by default.
     *
     * @param record the medical record to add
     */
    @Override
    public void addMedicalRecord(MedicalRecord record) {
        decoratedAnimal.addMedicalRecord(record);
    }

    /**
     * Retrieves the animal's medical record.
     * Delegates to the wrapped animal by default.
     *
     * @return the medical record
     */
    @Override
    public MedicalRecord getMedicalRecord() {
        return decoratedAnimal.getMedicalRecord();
    }

    /**
     * Returns the underlying wrapped {@link Animal} instance.
     *
     * @return the decorated animal instance
     */
    public Animal getDecoratedAnimal() {
        return decoratedAnimal;
    }

    /**
     * Returns a string representation of the animal.
     * Delegates to the wrapped animal by default.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return decoratedAnimal.toString();
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Delegates to the wrapped animal by default.
     *
     * @param obj the reference object with which to compare
     * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        return decoratedAnimal.equals(obj);
    }

    /**
     * Returns a hash code value for the object.
     * Delegates to the wrapped animal by default.
     *
     * @return hash code value
     */
    @Override
    public int hashCode() {
        return decoratedAnimal.hashCode();
    }
}
