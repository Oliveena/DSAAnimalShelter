package patterns.structural.decorators;

import models.animals.Animal;

/**
 * Abstract base decorator for {@link Animal} instances.
 * <p>
 * Implements the Decorator pattern by wrapping an existing {@code Animal} object
 * and allowing subclasses to extend or modify its behavior dynamically without altering the original class.
 * <p>
 * This class delegates most calls to the wrapped {@link Animal} instance,
 * but subclasses can override methods to add new behavior or augment existing functionality.
 */
public abstract class AnimalDecorator extends Animal {
    /** The wrapped {@code Animal} instance being decorated. */
    protected Animal decoratedAnimal;

    /**
     * Constructs a new {@code AnimalDecorator} wrapping the specified {@link Animal}.
     *
     * @param animal the animal instance to decorate; must not be {@code null}
     */
    public AnimalDecorator(Animal animal) {
        super(animal.getName(), animal.getAge(), animal.getSpecies(), animal.getBreed());
        this.decoratedAnimal = animal;
    }

    /**
     * Returns the details of the decorated animal.
     * <p>
     * By default, delegates to the wrapped animal's {@link Animal#getDetails()} method.
     * Subclasses may override this to add or modify information.
     *
     * @return a string describing the animal's details
     */
    @Override
    public String getDetails() {
        return decoratedAnimal.getDetails();
    }

    /**
     * Returns the animal to the shelter.
     * <p>
     * This method delegates to the wrapped animal's {@link Animal#returnToShelter()} method,
     * allowing subclasses to add additional behavior if needed.
     */
    @Override
    public void returnToShelter() {
        decoratedAnimal.returnToShelter();
    }
}
