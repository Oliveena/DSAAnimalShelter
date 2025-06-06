package services;

import models.animals.Animal;
import models.AnimalRegistry;
import patterns.creational.builders.BirdBuilder;
import patterns.creational.builders.CatBuilder;
import patterns.creational.builders.DogBuilder;
import patterns.creational.builders.LizardBuilder;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Service class for managing animals in the shelter.
 */
public class AnimalService {
    private final AnimalRegistry registry;

    public AnimalService(AnimalRegistry registry) {
        this.registry = registry;
    }

    public boolean isAtCapacity() {
        return registry.isAtCapacity();
    }

    public void addAnimal(Animal animal) {
        registry.addAnimal(animal);
    }

    public boolean removeAnimalById(String id) {
        return registry.removeAnimalById(id);
    }

    public List<Animal> getAllAnimals() {
        return registry.getAllAnimals();
    }

    public List<Animal> findById(String id) {
        // Returns a list with one animal or empty list if not found
        return Optional.ofNullable(registry.findById(id))
                .map(List::of)
                .orElse(List.of());
    }

    public List<Animal> findByName(String name) {
        return registry.searchByName(name);
    }

    public List<Animal> findBySpecies(String species) {
        // Case-insensitive species matching
        return registry.getAllAnimals().stream()
                .filter(a -> a.getSpecies().name().equalsIgnoreCase(species))
                .toList();
    }

    public int getAnimalCount() {
        return registry.getAnimalCount();
    }

    public int getMaxCapacity() {
        return registry.getMaxCapacity();
    }

    public boolean isEmpty() {
        return registry.isEmpty();
    }

    /**
     * Constructs and adds an animal based on UI input parameters.
     * Uses builders for each supported animal type.
     *
     * @param name   Animal name
     * @param age    Animal age
     * @param type   Animal type (dog, cat, bird, lizard)
     * @param extras Map of extra properties (breed, trained, etc.)
     * @throws IllegalArgumentException if animal type is unsupported
     */
    public void addAnimalFromUI(String name, int age, String type, Map<String, String> extras) {
        Animal animal = switch (type.toLowerCase()) {
            case "dog" -> new DogBuilder()
                    .setName(name)
                    .setAge(age)
                    .setBreed(extras.getOrDefault("breed", "Unknown"))
                    .setTrained(Boolean.parseBoolean(extras.getOrDefault("trained", "false")))
                    .build();
            case "cat" -> new CatBuilder()
                    .setName(name)
                    .setAge(age)
                    .setBreed(extras.getOrDefault("breed", "Unknown"))
                    .setFurLength(extras.getOrDefault("furLength", "Short"))
                    .setIndoor(Boolean.parseBoolean(extras.getOrDefault("indoor", "false")))
                    .build();
            case "bird" -> new BirdBuilder()
                    .setName(name)
                    .setAge(age)
                    .setBreed(extras.getOrDefault("breed", "Unknown"))
                    .setCanFly(Boolean.parseBoolean(extras.getOrDefault("canFly", "true")))
                    .build();
            case "lizard" -> new LizardBuilder()
                    .setName(name)
                    .setAge(age)
                    .setBreed(extras.getOrDefault("breed", "Unknown"))
                    .setVenomous(Boolean.parseBoolean(extras.getOrDefault("venomous", "false")))
                    .build();
            default -> throw new IllegalArgumentException("Unsupported animal type: " + type);
        };

        addAnimal(animal);
    }
}
