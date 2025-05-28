package services;

import models.animals.Animal;
import models.MedicalRecord;
import patterns.builders.MedicalRecordBuilder;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class MedicalRecordService {

    public MedicalRecord collectMedicalRecord(Scanner scanner) {
        MedicalRecordBuilder builder = new MedicalRecordBuilder();

        System.out.println("Enter vaccinations (type 'done' to finish):");
        collectEntries(scanner, builder::addVaccination);
        System.out.println("Enter treatments (type 'done' to finish):");
        collectEntries(scanner, builder::addTreatment);
        System.out.println("Enter checkups (type 'done' to finish):");
        collectEntries(scanner, builder::addCheckup);

        return builder.build();
    }

    public void displayMedicalRecord(Animal animal) {
        MedicalRecord record = animal.getMedicalRecord();
        if (record == null) {
            System.out.println("No medical record.");
            return;
        }

        System.out.println("Medical Record:");
        System.out.println("  Vaccinations: " + formatList(record.getVaccinations()));
        System.out.println("  Treatments: " + formatList(record.getTreatments()));
        System.out.println("  Checkups: " + formatList(record.getCheckups()));
    }

    private void collectEntries(Scanner scanner, Consumer<String> consumer) {
        while (true) {
            String input = scanner.nextLine().trim();
            if ("done".equalsIgnoreCase(input)) break;
            consumer.accept(input);
        }
    }

    private String formatList(List<String> items) {
        return items.isEmpty() ? "None" : String.join(", ", items);
    }
}
