package services;

import models.animals.Animal;
import models.MedicalRecord;
import patterns.creational.builders.MedicalRecordBuilder;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * Service class to handle creation and display of medical records for animals.
 */
public class MedicalRecordService {

    /**
     * Collects a medical record from user input via the provided Scanner.
     * Users can input vaccinations, treatments, and checkups, typing 'done' to finish each list.
     * @param scanner the Scanner to read user input
     * @return the constructed MedicalRecord
     */
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

    /**
     * Displays the medical record details of the given animal.
     * If no medical record exists, a message is shown.
     * @param animal the animal whose medical record to display
     */
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

    /**
     * Helper method to read entries from the scanner until the user types "done".
     * Each entry is passed to the given consumer.
     */
    private void collectEntries(Scanner scanner, Consumer<String> consumer) {
        while (true) {
            String input = scanner.nextLine().trim();
            if ("done".equalsIgnoreCase(input)) break;
            consumer.accept(input);
        }
    }

    /**
     * Formats a list of strings as a comma-separated string,
     * or returns "None" if the list is empty.
     */
    private String formatList(List<String> items) {
        return items.isEmpty() ? "None" : String.join(", ", items);
    }
}
