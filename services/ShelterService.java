package services;

import models.AnimalRegistry;
import models.animals.Animal;
import models.Adoptable;
import models.Task;
import patterns.behavioral.observer.VolunteerManager;
import patterns.behavioral.observer.VolunteerObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class that manages shelter operations such as
 * managing animals, volunteers, and shelter tasks.
 */
public class ShelterService {

    private final AnimalRegistry registry;
    private final VolunteerManager volunteerManager;
    private final List<Task> tasks = new ArrayList<>();

    /**
     * Constructs a ShelterService with the given animal registry and volunteer manager.
     *
     * @param registry         the animal registry managing animal records
     * @param volunteerManager the volunteer manager handling volunteer notifications
     */
    public ShelterService(AnimalRegistry registry, VolunteerManager volunteerManager) {
        this.registry = registry;
        this.volunteerManager = volunteerManager;
    }

    /** Adds a new animal to the registry and notifies volunteers. */
    public void addAnimal(Animal animal) {
        registry.addAnimal(animal);
        volunteerManager.notifyVolunteers("\nNew " + animal.getSpecies() + " arrived: " + animal.getName());
    }

    /** Removes an animal by ID from the registry. */
    public boolean removeAnimalById(String id) {
        return registry.removeAnimalById(id);
    }

    /** Finds an animal by its ID. */
    public Animal findById(String id) {
        return registry.findById(id);
    }

    /** Searches for animals by name (partial match). */
    public List<Animal> searchByName(String name) {
        return registry.searchByName(name);
    }

    /** Returns all animals currently in the registry. */
    public List<Animal> getAllAnimals() {
        return registry.getAllAnimals();
    }

    /** Checks if the shelter is at capacity. */
    public boolean isAtCapacity() {
        return registry.isAtCapacity();
    }

    /**
     * Marks an adoptable animal as adopted and removes it from the registry.
     * @param animal the adoptable animal to be adopted
     */
    public void adoptAnimal(Adoptable animal) {
        animal.adopt();
        removeAnimalById(((Animal) animal).getId());
    }

    /** Registers a volunteer observer to receive notifications. */
    public void registerVolunteer(VolunteerObserver volunteer) {
        volunteerManager.registerVolunteer(volunteer);
    }

    /** Removes a volunteer observer from notifications. */
    public void removeVolunteer(VolunteerObserver volunteer) {
        volunteerManager.removeVolunteer(volunteer);
    }

    /** Adds a new task and notifies volunteers. */
    public void addTask(Task task) {
        tasks.add(task);
        volunteerManager.notifyVolunteers("New task added:\n" + task.getDetails());
    }

    /** Returns a copy of all current tasks. */
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }
}
