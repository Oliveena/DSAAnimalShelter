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

    public void addAnimal() {
        executor.submit(animalController::addAnimal);
    }

    public void listAnimals() {
        executor.submit(animalController::listAnimals);
    }

    public void findByName() {
        executor.submit(animalController::findAnimalByName);
    }

    public void findById() {
        executor.submit(animalController::findAnimalById);
    }

    public void findBySpecies() {
        executor.submit(animalController::findAnimalsBySpecies);
    }

    public void sortAnimals() {
        executor.submit(animalController::sortAnimals);
    }

    public void removeAnimal() {
        executor.submit(animalController::removeAnimal);
    }

    // === Adoption Variants ===
    public void adoptAnimalFIFO() {
        executor.submit(adoptionController::adoptAnimalOfTheMonth);
    }

    public void adoptAnimalByPreference() {
        executor.submit(adoptionController::preferenceBasedAdoption);
    }

    // thrid option: adopting directly from displayed animal list
//    public void adoptAnimalManually() {
//        executor.submit(adoptionController::adoptAnimal);
//    }

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
