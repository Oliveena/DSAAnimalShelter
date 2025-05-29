package controllers;

import services.ShelterService;
import controllers.AnimalController;
import controllers.AdoptionController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AdminController {
    private final AnimalController animalController;
    private final AdoptionController adoptionController;
    private final ShelterService shelterService;
    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    public AdminController(AnimalController animalController,
                           AdoptionController adoptionController,
                           ShelterService shelterService) {
        this.animalController = animalController;
        this.adoptionController = adoptionController;
        this.shelterService = shelterService;
    }

    // Interactive methods — run directly, no executor.submit
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

    // Adoption operations — can be async if they don't involve user input
    public void adoptAnimalFIFO() {
        executor.submit(adoptionController::adoptAnimalOfTheMonth);
    }

    public void adoptAnimalByPreference() {
        executor.submit(adoptionController::preferenceBasedAdoption);
    }

    // public void adoptAnimalManually() {
    //     executor.submit(adoptionController::adoptAnimal);
    // }

    public void clearQueue() {
        executor.submit(adoptionController::clearQueue);
    }

    public void peekNextAnimal() {
        executor.submit(adoptionController::peekNextAnimal);
    }

    public void shutdown() {
        executor.shutdown();
    }
}
