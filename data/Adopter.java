package data;

import domain.Animal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The {@code Adopter} class represents a person who adopts animals from the shelter.
 * Each adopter has a name and a list of adopted animals.
 */
public class Adopter {
    private final String name;
    private final List<Animal> adoptedAnimals = new ArrayList<>();

    /**
     * Constructs a new {@code Adopter} with a given name.
     *
     * @param name the adopter's name
     * @throws IllegalArgumentException if name is null or empty
     */
    public Adopter(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Adopter name cannot be null or empty.");
        }
        this.name = name;
    }

    /**
     * Adds an animal to this adopter's list of adopted animals.
     *
     * @param animal the animal to adopt
     */
    public void adoptAnimal(Animal animal) {
        if (animal != null) {
            adoptedAnimals.add(animal);
        }
    }

    /**
     * Returns the adopter's name.
     *
     * @return the adopter's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns an unmodifiable list of adopted animals.
     *
     * @return list of adopted animals
     */
    public List<Animal> getAdoptedAnimals() {
        return Collections.unmodifiableList(adoptedAnimals);
    }

    /**
     * Searches for adopted animals by partial name match (case-insensitive).
     *
     * @param name part or full name of the animal
     * @return list of matching adopted animals
     */
    public List<Animal> searchAdoptedAnimalsByName(String name) {
        if (name == null || name.isEmpty()) return new ArrayList<>();

        return adoptedAnimals.stream()
                .filter(a -> a.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Returns the number of animals this adopter has adopted.
     *
     * @return count of adopted animals
     */
    public int getAdoptedAnimalCount() {
        return adoptedAnimals.size();
    }

    @Override
    public String toString() {
        return "Adopter{name='" + name + "', adoptedAnimalCount=" + adoptedAnimals.size() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Adopter)) return false;
        Adopter adopter = (Adopter) o;
        return name.equals(adopter.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
