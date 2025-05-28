package controllers;

import models.AnimalRegistry;
import models.MedicalRecord;
import models.animals.Animal;
import services.MedicalRecordService;

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
        System.out.print("Enter animal ID: ");
        String id = scanner.nextLine();
        Animal animal = registry.findById(id);
        if (animal == null) {
            System.out.println("Animal not found.");
            return;
        }

        MedicalRecord newRecord = medicalService.collectMedicalRecord(scanner);
        animal.setMedicalRecord(newRecord);
        System.out.println("Medical record updated.");
    }

    public void viewMedicalRecord() {
        System.out.print("Enter animal ID: ");
        String id = scanner.nextLine();
        Animal animal = registry.findById(id);
        if (animal == null) {
            System.out.println("Animal not found.");
            return;
        }

        medicalService.displayMedicalRecord(animal);
    }
}
