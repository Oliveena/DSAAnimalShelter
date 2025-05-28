package controllers;

import services.ShelterService;
import controllers.AnimalController;
import controllers.AdoptionController;

public class AdminController {
    private final AnimalController animalController;
    private final AdoptionController adoptionController;
    private final ShelterService shelterService;

    public AdminController(AnimalController animalController,
                           AdoptionController adoptionController,
                           ShelterService shelterService) {
        this.animalController = animalController;
        this.adoptionController = adoptionController;
        this.shelterService = shelterService;
    }

    public void addAnimal() {
        animalController.addAnimal();
    }

    public void listAnimals() {
        animalController.listAnimals();
    }

    public void findByName() {
        animalController.findAnimalByName();
    }

    public void findById() {
        animalController.findAnimalById();
    }

    public void findBySpecies() {
        animalController.findAnimalsBySpecies();
    }

    public void sortAnimals() {
        animalController.sortAnimals();
    }

    public void removeAnimal() {
        animalController.removeAnimal();
    }

    public void adoptAnimal() {
        adoptionController.adoptAnimal();
    }

    public void clearQueue() {
        adoptionController.clearQueue();
    }

    public void peekNextAnimal() {
        adoptionController.peekNextAnimal();
    }
}
