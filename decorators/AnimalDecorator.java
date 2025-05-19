package decorators;
import logic.Animal;

public abstract class AnimalDecorator extends Animal {
    protected Animal decoratedAnimal;

    public AnimalDecorator(Animal animal) {
        super(animal.getName(), animal.getAge());
        this.decoratedAnimal = animal;
    }

    @Override
    public String getDetails() {
        return decoratedAnimal.getDetails();
    }
}