package controllers;

import models.animals.Animal;
import services.MedicalRecordService;

import java.util.Scanner;

public class MedicalRecordController {
    private MedicalRecordService medicalService;
    private Scanner scanner;

    public MedicalRecordController(MedicalRecordService medicalService, Scanner scanner) {
        this.medicalService = medicalService;
        this.scanner = scanner;
    }

    public void collectMedicalRecord(Animal animal) {
        animal.setMedicalRecord(medicalService.collectMedicalRecord(scanner));
    }

    public void displayMedicalRecord(Animal animal) {
        medicalService.displayMedicalRecord(animal);
    }
}
