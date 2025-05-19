package data;

import java.util.ArrayList;
import java.util.List;
import logic.Animal;

public class Adopter {
    private String name;
    private List<Animal> adoptedAnimals = new ArrayList<>();

    public Adopter(String name, String phoneNumber) {
        this.name = name;
    }

    public void adoptAnimal(Animal animal) {
        adoptedAnimals.add(animal);
    }

    public String getName() {
        return name;
    }

    public List<Animal> getAdoptedAnimals() {
        return adoptedAnimals;
    }
}

