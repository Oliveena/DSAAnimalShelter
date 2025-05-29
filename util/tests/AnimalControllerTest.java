package util.tests;

import controllers.AnimalController;
import controllers.MedicalRecordController;
import models.AnimalRegistry;
import models.ShelterQueue;
import models.animals.Cat;
import models.animals.Dog;
import patterns.behavioral.observer.VolunteerManager;
import services.AnimalService;
import services.MedicalRecordService;
import services.ShelterService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalControllerTest {

    private AnimalController controller;
    private AnimalRegistry registry;
    private ShelterQueue queue;
    private AnimalService animalService;
    private ShelterService shelterService;
    private MedicalRecordController medicalController;
    private MedicalRecordService medicalService;
    private Scanner scanner;
    private VolunteerManager volunteerManager;

    @BeforeEach
    public void setUp() {
        registry = new AnimalRegistry();
        queue = new ShelterQueue();
        animalService = new AnimalService(registry);
        shelterService = new ShelterService(registry, volunteerManager);
        controller = new AnimalController(animalService, medicalController, scanner, registry, queue, shelterService);
    }

    @Test
    public void testListAnimalsEmpty() {
        controller.listAnimals(); // Should not throw
        assertEquals(0, registry.getAnimalCount());
    }

    @Test
    public void testSortAnimalsByName() {
        registry.addAnimal(new Cat("Zelda", 3, "British", "short", true));
        registry.addAnimal(new Dog("Ace", 5, "Beagle", true));
        // Simulate sort choice (manually sort to check)
        controller.sortAnimals(); // Will just default prompt (won’t sort properly without scanner input)
    }

    @Test
    public void testFindById() {
        Dog dog = new Dog("Max", 4, "Pug", true);
        registry.addAnimal(dog);
        AnimalController direct = controller; // if findAnimalById took ID param, this would be better
        assertNotNull(registry.findById(dog.getId()));
    }

    @Test
    public void testFindByName() {
        Cat cat = new Cat("Mittens", 2, "Persian", "long", true);
        registry.addAnimal(cat);
        assertFalse(registry.searchByName("mitt").isEmpty());
    }

    @Test
    public void testFindBySpecies() {
        Cat cat = new Cat("Fluffy", 2, "Persian", "long", true);
        registry.addAnimal(cat);
        assertEquals("CAT", cat.getSpecies().name());
    }

    @Test
    public void testRemoveAnimal() {
        Dog dog = new Dog("Rex", 5, "Husky", false);
        registry.addAnimal(dog);
        boolean removed = registry.removeAnimalById(dog.getId());
        assertTrue(removed);
        assertEquals(0, registry.getAnimalCount());
    }

}