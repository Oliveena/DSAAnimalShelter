package services;

import models.animals.Animal;
import models.AnimalRegistry;
import patterns.observer.VolunteerManager;

import java.util.List;
import java.util.Optional;

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
        return Optional.ofNullable(registry.findById(id))
                .map(List::of)
                .orElse(List.of());
    }

    public List<Animal> findByName(String name) {
        return registry.searchByName(name);
    }

    public List<Animal> findBySpecies(String species) {
        return registry.getAllAnimals().stream()
                .filter(a -> a.getSpecies().equalsIgnoreCase(species))
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
}