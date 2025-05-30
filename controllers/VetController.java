package controllers;

import models.AnimalRegistry;
import models.MedicalRecord;
import models.animals.Animal;
import services.MedicalRecordService;

import java.util.List;
import java.util.Scanner;

/**
 * Controller class for veterinary operations, including viewing and updating animal medical records.
 * Primarily interacts with {@link AnimalRegistry} and {@link MedicalRecordService}.
 */
public class VetController {

    private final AnimalRegistry registry;
    private final MedicalRecordService medicalService;
    private final Scanner scanner;

    /**
     * Constructs a new VetController.
     *
     * @param registry        The animal registry containing all shelter animals.
     * @param medicalService  Service used for collecting and displaying medical records.
     * @param scanner         Scanner for user input.
     */
    public VetController(AnimalRegistry registry, MedicalRecordService medicalService, Scanner scanner) {
        this.registry = registry;
        this.medicalService = medicalService;
        this.scanner = scanner;
    }

    /**
     * Updates the medical record of a specific animal selected by ID.
     * Prompts the user for new medical information via the console.
     */
    public void updateMedicalRecord() {
        Animal animal = findAnimalById();
        if (animal == null) return;

        MedicalRecord newRecord = medicalService.collectMedicalRecord(scanner);
        animal.setMedicalRecord(newRecord);
        System.out.println("Medical record updated.");
    }

    /**
     * Displays the medical record of a specific animal selected by ID.
     */
    public void viewMedicalRecord() {
        Animal animal = findAnimalById();
        if (animal == null) return;

        medicalService.displayMedicalRecord(animal);
    }

    /**
     * Helper method to prompt the user for an animal ID and return the corresponding animal.
     *
     * @return The {@link Animal} with the given ID, or {@code null} if not found.
     */
    private Animal findAnimalById() {
        System.out.print("Enter animal ID: ");
        String id = scanner.nextLine();
        Animal animal = registry.findById(id);
        if (animal == null) {
            System.out.println("Animal not found.");
        }
        return animal;
    }

    /**
     * Performs a bulk update of medical records for a list of animals, typically used
     * for public health tasks such as vaccinations or treatments.
     *
     * @param animalIds A list of animal IDs to update.
     */
    public void updateMedicalRecordsBulk(List<String> animalIds) {
        animalIds.stream()
                .map(registry::findById)
                .filter(a -> a != null)
                .forEach(animal -> {
                    System.out.println("Updating medical record for: " + animal.getName() + " (ID: " + animal.getId() + ")");
                    MedicalRecord newRecord = medicalService.collectMedicalRecord(scanner);
                    animal.setMedicalRecord(newRecord);
                    System.out.println("Updated successfully.\n");
                });
    }

    /**
     * Displays the medical records for a batch of animals by their IDs.
     *
     * @param animalIds A list of animal IDs whose records should be shown.
     */
    public void viewMedicalRecordsBulk(List<String> animalIds) {
        animalIds.stream()
                .map(registry::findById)
                .filter(a -> a != null)
                .forEach(medicalService::displayMedicalRecord);
    }
}