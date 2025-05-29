package patterns.structural.decorators;

import models.MedicalRecord;
import models.animals.Animal;
import models.animals.Species;

/**
 * Abstract base decorator for {@link Animal} instances.
 * <p>
 * Implements the Decorator pattern by wrapping an existing {@code Animal} object
 * and allowing subclasses to extend or modify its behavior dynamically without altering the original class.
 */
public abstract class AnimalDecorator extends Animal {
    /** The wrapped {@code Animal} instance being decorated. */
    protected final Animal decoratedAnimal;

    /**
     * Constructs a new {@code AnimalDecorator} wrapping the specified {@link Animal}.
     *
     * @param animal the animal instance to decorate; must not be {@code null}
     */
    public AnimalDecorator(Animal animal) {
        super(animal.getName(), animal.getAge(), animal.getSpecies(), animal.getBreed());
        if (animal == null) {
            throw new IllegalArgumentException("Decorated animal must not be null.");
        }
        this.decoratedAnimal = animal;
    }

    @Override
    public String getDetails() {
        return decoratedAnimal.getDetails();
    }

    @Override
    public void returnToShelter() {
        decoratedAnimal.returnToShelter();
    }

    @Override
    public void adopt() {
        decoratedAnimal.adopt();
    }

    @Override
    public Species getSpecies() {
        return decoratedAnimal.getSpecies();
    }

    @Override
    public String getBreed() {
        return decoratedAnimal.getBreed();
    }

    @Override
    public void addMedicalRecord(MedicalRecord record) {
        decoratedAnimal.addMedicalRecord(record);
    }

    @Override
    public MedicalRecord getMedicalRecord() {
        return decoratedAnimal.getMedicalRecord();
    }

    public Animal getDecoratedAnimal() {
        return decoratedAnimal;
    }

    @Override
    public String toString() {
        return decoratedAnimal.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return decoratedAnimal.equals(obj);
    }

    @Override
    public int hashCode() {
        return decoratedAnimal.hashCode();
    }
}
