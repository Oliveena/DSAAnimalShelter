package data;

import logic.Animal;

import java.util.ArrayList;
import java.util.List;

/**
 * AnimalRegistry maintains a registry of all animals in the shelter.
 * Supports adding, removing, searching, and retrieving animals.
 */
public class AnimalRegistry {

    private final List<Animal> animalList = new ArrayList<>();

    /**
     * Adds a new animal to the registry.
     *
     * @param animal the animal to add
     */
    public void addAnimal(Animal animal) {
        if (animal != null) {
            animalList.add(animal);
        } else {
            System.out.println("Attempted to add a null animal.");
        }
    }

    /**
     * Removes an animal by its unique ID.
     *
     * @param id the ID of the animal to remove
     * @return true if the animal was found and removed, false otherwise
     */
    public boolean removeAnimalById(String id) {
        if (id == null || id.isEmpty()) return false;
        return animalList.removeIf(a -> a.getId().equals(id));
    }

    /**
     * Searches animals by name (case-insensitive, partial match).
     *
     * @param name the name or partial name to search
     * @return a list of animals matching the name
     */
    public List<Animal> searchByName(String name) {
        List<Animal> results = new ArrayList<>();
        if (name == null || name.isEmpty()) return results;

        for (Animal a : animalList) {
            if (a.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(a);
            }
        }
        return results;
    }

    /**
     * Returns a copy of all animals in the registry.
     *
     * @return a list of all registered animals
     */
    public List<Animal> getAllAnimals() {
        return new ArrayList<>(animalList); // defensive copy
    }

    /**
     * Finds an animal by its exact ID.
     *
     * @param id the ID to search for
     * @return the animal if found, otherwise null
     */
    public Animal findById(String id) {
        for (Animal a : animalList) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        return null;
    }

    /**
     * Checks whether the registry is empty.
     *
     * @return true if no animals are registered, false otherwise
     */
    public boolean isEmpty() {
        return animalList.isEmpty();
    }
}
