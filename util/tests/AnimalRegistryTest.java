package util.tests;

import models.*;
import models.animals.Animal;
import models.animals.Cat;
import models.animals.Dog;
import org.junit.jupiter.api.Test;
import patterns.creational.builders.MedicalRecordBuilder;
import patterns.creational.factories.FormFactory;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class AnimalRegistryTest {

    @Test
    public void testAddAnimal() {
        AnimalRegistry registry = new AnimalRegistry();
        Animal dog = new Dog("Rex", 5, "Dog", "Pug", false);

        registry.addAnimal(dog);

        assertEquals(1, registry.getAllAnimals().size());
        assertEquals("Rex", registry.getAllAnimals().get(0).getName());
    }

    @Test
    public void testSearchByName() {
        AnimalRegistry registry = new AnimalRegistry();
        registry.addAnimal(new Cat("Mittens", 2, "short", "Siamese", "short", true));
        registry.addAnimal(new Cat("Whiskers", 3, "long", "Persian", "long", true));


        var results = registry.searchByName("mitt");
        assertFalse(results.isEmpty());
        assertEquals("Mittens", results.get(0).getName());
    }

    @Test
    public void testRemoveAnimalById() {
        AnimalRegistry registry = new AnimalRegistry();
        Animal cat = new Cat("Mimi", 2, "short", "Siamese", "short", true);
        registry.addAnimal(cat);

        boolean removed = registry.removeAnimalById(cat.getId());

        assertTrue(removed);
        assertEquals(0, registry.getAnimalCount());
    }

    @Test
    public void testFindByIdReturnsNullIfNotFound() {
        AnimalRegistry registry = new AnimalRegistry();
        assertNull(registry.findById("nonexistent-id"));
    }

    @Test
    public void testFIFOOrder() {
        ShelterQueue queue = new ShelterQueue();
        Animal cat = new Cat("Luna", 3, "short", "Tabby", "short", true);
        Animal dog = new Dog("Max", 5, "Dog", "Labrador", false);

        queue.addAnimal(cat);
        queue.addAnimal(dog);

        assertEquals(cat, queue.peekNext());
    }

    @Test
    public void testMedicalRecordConstruction() {
        MedicalRecord record = new MedicalRecordBuilder()
                .addVaccination("Rabies")
                .addTreatment("Deworming")
                .addCheckup("Annual Exam")
                .build();

        assertEquals(List.of("Rabies"), record.getVaccinations());
        assertEquals(List.of("Deworming"), record.getTreatments());
        assertEquals(List.of("Annual Exam"), record.getCheckups());
    }

    @Test
    public void testFormSubmissionLogs() {
        Adopter adopter = new Adopter("Alice");
        Animal cat = new Cat("Whiskers", 2, "long", "Persian", "long", true);
        AdoptionForm form = new FormFactory().createAdoptionForm(adopter, cat, LocalDate.now());

        assertNotNull(form);
        form.submit();
    }
}