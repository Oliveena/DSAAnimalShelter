package decorators;

import domain.Animal;

/**
 * An abstract decorator class for adding additional functionality to {@link Animal}.
 * <p>
 * This class implements the decorator pattern by wrapping an existing {@code Animal}
 * and allowing subclasses to add or modify behavior without altering the original class.
 */
public abstract class AnimalDecorator extends Animal {
    protected Animal decoratedAnimal;

    /**
     * Constructs a new {@code AnimalDecorator} that wraps the specified {@link Animal}.
     *
     * @param animal the animal to be decorated
     */
    public AnimalDecorator(Animal animal) {
        super(animal.getName(), animal.getAge());
        this.decoratedAnimal = animal;
    }

    /**
     * Returns the details of the decorated animal.
     * <p>
     * This method calls the original {@link Animal#getDetails()} method,
     * and subclasses can override it to add more information or modify the output.
     *
     * @return a string representing the animal's details
     */
    @Override
    public String getDetails() {
        return decoratedAnimal.getDetails();
    }

    @Override
    public void returnToShelter(){
        decoratedAnimal.returnToShelter();
    };
}
