package controllers;

import models.AnimalRegistry;
import models.MedicalRecord;
import models.animals.Animal;
import services.MedicalRecordService;

import java.util.List;
import java.util.Scanner;

public class VetController {
    private final AnimalRegistry registry;
    private final MedicalRecordService medicalService;
    private final Scanner scanner;

    public VetController(AnimalRegistry registry, MedicalRecordService medicalService, Scanner scanner) {
        this.registry = registry;
        this.medicalService = medicalService;
        this.scanner = scanner;
    }

    public void updateMedicalRecord() {
        Animal animal = findAnimalById();
        if (animal == null) return;

        MedicalRecord newRecord = medicalService.collectMedicalRecord(scanner);
        animal.setMedicalRecord(newRecord);
        System.out.println("Medical record updated.");
    }

    public void viewMedicalRecord() {
        Animal animal = findAnimalById();
        if (animal == null) return;

        medicalService.displayMedicalRecord(animal);
    }

    // Helper method to reduce duplication
    private Animal findAnimalById() {
        System.out.print("Enter animal ID: ");
        String id = scanner.nextLine();
        Animal animal = registry.findById(id);
        if (animal == null) {
            System.out.println("Animal not found.");
        }
        return animal;
    }

    // TODO: implement bulk medical record update for a batch of animals (e.g. vaccination clinic for ALL dogs, flea treatment for ALL cats, etc.)

    // Bulk update medical records for a list of animal IDs, mostly public health related, realistic user story
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

    // Bulk view medical records for a list of animal IDs
    public void viewMedicalRecordsBulk(List<String> animalIds) {
        animalIds.stream()
                .map(registry::findById)
                .filter(a -> a != null)
                .forEach(medicalService::displayMedicalRecord);
    }
}
