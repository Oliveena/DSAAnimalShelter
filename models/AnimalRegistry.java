package models;

import models.animals.Animal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The {@code AnimalRegistry} class maintains a thread-safe registry of all animals in the shelter.
 * <p>
 * It supports basic operations such as adding, removing, retrieving, and searching animals.
 * The registry enforces a maximum capacity to ensure the shelter does not exceed its limit.
 * </p>
 * <p>
 * Animals are stored in a synchronized list to support concurrent access.
 * </p>
 *
 * @see models.animals.Animal
 */
public class AnimalRegistry {
    // Thread-safe list to hold animals
    private final List<Animal> animalList = Collections.synchronizedList(new ArrayList<>());
    private int maxCapacity;

    /**
     * Constructs a new {@code AnimalRegistry} with a default maximum capacity of 20.
     */
    public AnimalRegistry() {
        this.maxCapacity = 20;
    }

    /**
     * Constructs a new {@code AnimalRegistry} with the specified maximum capacity.
     *
     * @param maxCapacity the maximum number of animals allowed in the registry
     * @throws IllegalArgumentException if {@code maxCapacity} is less than or equal to zero
     */
    public AnimalRegistry(int maxCapacity) {
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
        this.maxCapacity = maxCapacity;
    }

    /**
     * Sets the maximum capacity of the shelter registry.
     *
     * @param capacity the new maximum capacity
     * @throws IllegalArgumentException if {@code capacity} is less than or equal to zero
     */
    public void setMaxCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
        this.maxCapacity = capacity;
    }

    /**
     * Adds a new animal to the registry if capacity allows.
     *
     * @param animal the animal to add
     * @throws IllegalStateException if the registry is already at full capacity
     */
    public synchronized void addAnimal(Animal animal) {
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
     * Removes an animal from the registry by its unique ID.
     *
     * @param id the unique identifier of the animal to remove
     * @return {@code true} if the animal was found and removed; {@code false} otherwise
     */
    public boolean removeAnimalById(String id) {
        if (id == null || id.isEmpty()) return false;
        return animalList.removeIf(a -> a.getId().equals(id));
    }

    /**
     * Searches for animals by partial or full name (case-insensitive).
     *
     * @param name the name or part of the name to search for
     * @return a list of animals whose names contain the search string
     */
    public List<Animal> searchByName(String name) {
        if (name == null || name.isEmpty()) return new ArrayList<>();

        return animalList.stream()
                .filter(a -> a.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of all animals currently registered in the shelter.
     *
     * @return list of all registered animals
     */
    public List<Animal> getAllAnimals() {
        return animalList;
    }

    /**
     * Finds an animal by its exact ID using binary search.
     * <p>
     * The list is sorted by ID before performing the search.
     * </p>
     *
     * @param id the unique ID of the animal to find
     * @return the {@code Animal} if found, or {@code null} if no animal matches the ID
     */
    public Animal findById(String id) {
        if (id == null || id.isEmpty()) return null;

        List<Animal> sorted = animalList.stream()
                .sorted(Comparator.comparing(Animal::getId))
                .collect(Collectors.toList());

        int index = binarySearchById(sorted, id);
        return (index >= 0) ? sorted.get(index) : null;
    }

    /**
     * Performs a binary search on a sorted list of animals by their ID.
     *
     * @param sortedList the sorted list of animals
     * @param id         the ID to search for
     * @return the index of the animal if found, or -1 if not found
     */
    private int binarySearchById(List<Animal> sortedList, String id) {
        int low = 0;
        int high = sortedList.size() - 1;

        while (low <= high) {
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
     * Checks if the registry currently has no animals.
     *
     * @return {@code true} if no animals are registered, {@code false} otherwise
     */
    public boolean isEmpty() {
        return animalList.isEmpty();
    }

    /**
     * Returns the maximum number of animals allowed in the registry.
     *
     * @return the maximum capacity
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * Checks whether the registry has reached or exceeded its maximum capacity.
     *
     * @return {@code true} if the registry is at or above capacity, {@code false} otherwise
     */
    public boolean isAtCapacity() {
        return animalList.size() >= maxCapacity;
    }

    /**
     * Returns the current count of animals in the registry.
     *
     * @return the number of animals registered
     */
    public int getAnimalCount() {
        return animalList.size();
    }

    /**
     * Returns a list of animals filtered by a given species (case-insensitive).
     *
     * @param species the species to filter by
     * @return a list of animals matching the specified species
     */
    public List<Animal> getAnimalsBySpecies(String species) {
        return animalList.stream()
                .filter(animal -> animal.getSpecies().name().equalsIgnoreCase(species))
                .collect(Collectors.toList());
    }
}
