package controllers;

import models.animals.Animal;
import services.MedicalRecordService;

import java.util.List;
import java.util.Scanner;

/**
 * Controller class that manages medical records for animals in the shelter.
 * Delegates operations to the {@link MedicalRecordService} and handles user interaction for medical record input.
 */
public class MedicalRecordController {

    private final MedicalRecordService medicalService;
    private final Scanner scanner;

    /**
     * Constructs a MedicalRecordController with the specified service and scanner.
     *
     * @param medicalService The service responsible for medical record logic.
     * @param scanner        Scanner used for user input.
     */
    public MedicalRecordController(MedicalRecordService medicalService, Scanner scanner) {
        this.medicalService = medicalService;
        this.scanner = scanner;
    }

    /**
     * Collects a medical record for a given animal by prompting the user.
     *
     * @param animal The animal for which the medical record is being collected.
     */
    public void collectMedicalRecord(Animal animal) {
        animal.setMedicalRecord(medicalService.collectMedicalRecord(scanner));
    }

    /**
     * Displays the medical record for a specific animal.
     *
     * @param animal The animal whose medical record should be displayed.
     */
    public void displayMedicalRecord(Animal animal) {
        medicalService.displayMedicalRecord(animal);
    }

    /**
     * Collects medical records for a list of animals using a stream-based approach.
     *
     * @param animals The list of animals for which to collect records.
     */
    public void collectMedicalRecords(List<Animal> animals) {
        animals.stream()
                .forEach(this::collectMedicalRecord);
    }

    /**
     * Displays medical records for a list of animals.
     *
     * @param animals The list of animals whose records should be displayed.
     */
    public void displayMedicalRecords(List<Animal> animals) {
        animals.stream()
                .forEach(this::displayMedicalRecord);
    }

    /**
     * Example method to demonstrate capturing additional medical record input.
     * Can be extended to create and store medical records manually.
     */
    public void addMedicalRecord() {
        System.out.println("Please enter the medical record info:");
        String recordInfo = scanner.nextLine();
        // TODO: Use recordInfo to create or update a medical record
    }
}

