package controllers;

import models.animals.Animal;
import services.MedicalRecordService;

import java.util.List;
import java.util.Scanner;

public class MedicalRecordController {
    private final MedicalRecordService medicalService;
    private final Scanner scanner;

    public MedicalRecordController(MedicalRecordService medicalService, Scanner scanner) {
        this.medicalService = medicalService;
        this.scanner = scanner;
    }

    // Collect medical record for a single animal
    public void collectMedicalRecord(Animal animal) {
        animal.setMedicalRecord(medicalService.collectMedicalRecord(scanner));
    }

    // Display medical record for a single animal
    public void displayMedicalRecord(Animal animal) {
        medicalService.displayMedicalRecord(animal);
    }

    // Collect medical records for multiple animals using streams
    public void collectMedicalRecords(List<Animal> animals) {
        animals.stream()
                .forEach(this::collectMedicalRecord);  // Method ref for brevity
    }

    // Display medical records for multiple animals using streams
    public void displayMedicalRecords(List<Animal> animals) {
        animals.stream()
                .forEach(this::displayMedicalRecord);
    }

    // Example method that might gather input for adding records manually (adjust as needed)
    public void addMedicalRecord() {
        System.out.println("Please enter the medical record info:");
        String recordInfo = scanner.nextLine();
        // You can decide what to do with recordInfo here, e.g., create a MedicalRecord object or pass it on.
    }
}
