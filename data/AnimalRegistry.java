package data;

import domain.Animal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The {@code AnimalRegistry} class maintains a registry of all animals in the shelter.
 * It supports adding, removing, searching, and retrieving animals.
 * <p>
 * Capacity is enforced, and various search methods are provided.
 */
public class AnimalRegistry {

    private final List<Animal> animalList = new ArrayList<>();
    private final int maxCapacity;

    /**
     * Constructs a new {@code AnimalRegistry} with the default maximum capacity of 20.
     */
    public AnimalRegistry() {
        this.maxCapacity = 20;
    }

    /**
     * Constructs a new {@code AnimalRegistry} with a specified capacity.
     *
     * @param maxCapacity the maximum number of animals allowed in the registry
     * @throws IllegalArgumentException if the specified capacity is not positive
     */
    public AnimalRegistry(int maxCapacity) {
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
        this.maxCapacity = maxCapacity;
    }

    /**
     * Adds a new animal to the registry.
     *
     * @param animal the animal to add
     * @throws IllegalStateException if the shelter is at full capacity
     */
    public void addAnimal(Animal animal) {
        if (animal == null) {
            System.out.println("Attempted to add a null animal.");
            return;
        }

        if (isAtCapacity()) {
            throw new IllegalStateException("Shelter is at full capacity (" + maxCapacity + ").");
        }

        animalList.add(animal);
    }

    /**
     * Removes an animal by its unique ID.
     *
     * @param id the ID of the animal to remove
     * @return true if the animal was found and removed; false otherwise
     */
    public boolean removeAnimalById(String id) {
        if (id == null || id.isEmpty()) return false;
        return animalList.removeIf(a -> a.getId().equals(id));
    }

    /**
     * Searches animals by name (case-insensitive, partial match).
     *
     * @param name the name or partial name to search for
     * @return a list of animals matching the name
     */
    public List<Animal> searchByName(String name) {
        if (name == null || name.isEmpty()) return new ArrayList<>();

        return animalList.stream()
                .filter(a -> a.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Returns a copy of all animals in the registry.
     *
     * @return a list of all registered animals
     */
    public List<Animal> getAllAnimals() {
        return animalList;
    }

    /**
     * Finds an animal by its exact ID using binary search (requires sorted list).
     *
     * @param id the ID to search for
     * @return the animal if found; null otherwise
     */
    public Animal findById(String id) {
        // if no animals in a shelter
        if (id == null || id.isEmpty()) return null;

        // sorting the animal list with stream before binary search
        List<Animal> sorted = animalList.stream()
                .sorted(Comparator.comparing(Animal::getId))
                .collect(Collectors.toList());

        // attribute an index to each sorted element
        int index = binarySearchById(sorted, id);
        // if no animals, nothing to sort, no indexes
        return (index >= 0) ? sorted.get(index) : null;
    }

    // binary search in the ordered colelction
    private int binarySearchById(List<Animal> sortedList, String id) {
        int low = 0;
        int high = sortedList.size() - 1;

        while (low <= high) {
            // start with middle
            int middle = (low + high) >>> 1;
            Animal midAnimal = sortedList.get(middle);
            int cmp = midAnimal.getId().compareTo(id);
            if (cmp == 0) return middle;
            if (cmp < 0) low = middle + 1;
            else high = middle - 1;
        }

        return -1;
    }

    /**
     * Checks whether the registry is empty.
     *
     * @return true if no animals are registered; false otherwise
     */
    public boolean isEmpty() {
        return animalList.isEmpty();
    }

    /**
     * Returns the maximum capacity of the shelter.
     *
     * @return the maximum number of animals allowed
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * Checks if the shelter is at full capacity.
     *
     * @return true if shelter is full; false otherwise
     */
    public boolean isAtCapacity() {
        return animalList.size() >= maxCapacity;
    }

    /**
     * Returns the current number of animals in the registry.
     *
     * @return the number of registered animals
     */
    public int getAnimalCount() {
        return animalList.size();
    }
}
