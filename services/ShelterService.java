package services;

import models.AnimalRegistry;
import models.animals.Animal;
import models.Adoptable;
import models.Task;
import patterns.observer.VolunteerManager;
import patterns.observer.VolunteerObserver;

import java.util.ArrayList;
import java.util.List;

public class ShelterService  {
    private final AnimalRegistry registry;
    private final VolunteerManager volunteerManager;

    public ShelterService(AnimalRegistry registry, VolunteerManager volunteerManager) {
        this.registry = new AnimalRegistry(50); // customizable capacity
        this.volunteerManager = new VolunteerManager();
    }

    // Delegated methods
    public void addAnimal(Animal animal) {
        registry.addAnimal(animal);
        volunteerManager.notifyVolunteers("\nNew " + animal.getSpecies() + " arrived: " + animal.getName());
    }

    public boolean removeAnimalById(String id) {
        return registry.removeAnimalById(id);
    }

    public Animal findById(String id) {
        return registry.findById(id);
    }

    public List<Animal> searchByName(String name) {
        return registry.searchByName(name);
    }

    public List<Animal> getAllAnimals() {
        return registry.getAllAnimals();
    }

    public boolean isAtCapacity() {
        return registry.isAtCapacity();
    }

    public void adoptAnimal(Adoptable animal) {
        animal.adopt();
        removeAnimalById(((Animal) animal).getId());
    }

    public void registerVolunteer(VolunteerObserver volunteer) {
        volunteerManager.registerVolunteer(volunteer);
    }

    public void removeVolunteer(VolunteerObserver volunteer) {
        volunteerManager.removeVolunteer(volunteer);
    }

    private final List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
        volunteerManager.notifyVolunteers("new task added,\n" + task.getDetails());
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

}

