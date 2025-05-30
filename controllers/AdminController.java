package controllers;

import services.ShelterService;
import controllers.AnimalController;
import controllers.AdoptionController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The {@code AdminController} coordinates administrative operations
 * related to animals and adoptions in the shelter.
 * <p>
 * It delegates actions to {@link AnimalController} and {@link AdoptionController},
 * and uses a thread pool to asynchronously execute non-interactive tasks.
 * </p>
 *
 * <p>
 * Interactive operations are executed directly, while background tasks such as
 * adoptions are offloaded to a thread pool to avoid blocking the main thread.
 * </p>
 * */
public class AdminController {

    private final AnimalController animalController;
    private final AdoptionController adoptionController;
    private final ShelterService shelterService;
    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    /**
     * Constructs an AdminController with the required dependencies.
     *
     * @param animalController    Handles operations related to animals.
     * @param adoptionController  Handles adoption operations.
     * @param shelterService      Provides core shelter-related services.
     */
    public AdminController(AnimalController animalController,
                           AdoptionController adoptionController,
                           ShelterService shelterService) {
        this.animalController = animalController;
        this.adoptionController = adoptionController;
        this.shelterService = shelterService;
    }

    // ========================== Animal Operations ==========================

    /**
     * Initiates the process to add a new animal (interactive).
     */
    public void addAnimal() {
        animalController.addAnimal();
    }

    /**
     * Lists all animals currently in the shelter.
     */
    public void listAnimals() {
        animalController.listAnimals();
    }

    /**
     * Searches for an animal by name.
     */
    public void findByName() {
        animalController.findAnimalByName();
    }

    /**
     * Searches for an animal by its ID.
     */
    public void findById() {
        animalController.findAnimalById();
    }

    /**
     * Filters animals by species.
     */
    public void findBySpecies() {
        animalController.findAnimalsBySpecies();
    }

    /**
     * Sorts the list of animals by a given criterion.
     */
    public void sortAnimals() {
        animalController.sortAnimals();
    }

    /**
     * Removes an animal from the shelter records.
     */
    public void removeAnimal() {
        animalController.removeAnimal();
    }

    // ========================== Adoption Operations ==========================

    /**
     * Initiates a FIFO-based adoption (non-interactive).
     * <p>
     * This adoption strategy selects the oldest animal available.
     * </p>
     */
    public void adoptAnimalFIFO() {
        executor.submit(adoptionController::adoptAnimalOfTheMonth);
    }

    /**
     * Initiates a preference-based adoption (non-interactive).
     * <p>
     * This adoption strategy matches animals based on user preferences.
     * </p>
     */
    public void adoptAnimalByPreference() {
        executor.submit(adoptionController::preferenceBasedAdoption);
    }

    /*
     * Initiates a manual adoption process (currently disabled).
     * Intended for fully interactive manual selections.
     */
    // public void adoptAnimalManually() {
    //     executor.submit(adoptionController::adoptAnimal);
    // }

    /**
     * Clears the adoption queue.
     * <p>
     * Typically used for administrative resets.
     * </p>
     */
    public void clearQueue() {
        executor.submit(adoptionController::clearQueue);
    }

    /**
     * Peeks at the next animal in the adoption queue without removing it.
     */
    public void peekNextAnimal() {
        executor.submit(adoptionController::peekNextAnimal);
    }

    /**
     * Gracefully shuts down the executor service used for background operations.
     */
    public void shutdown() {
        executor.shutdown();
    }
}
