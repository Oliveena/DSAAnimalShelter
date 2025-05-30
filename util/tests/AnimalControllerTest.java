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

/**
 * Unit tests for the {@link AnimalController}, validating interactions
 * with the {@link AnimalRegistry}, {@link ShelterQueue}, and related services.
 */
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

    /**
     * Initializes the test environment before each test by setting up
     * all required service and model instances.
     */
    @BeforeEach
    public void setUp() {
        registry = new AnimalRegistry();
        queue = new ShelterQueue();
        animalService = new AnimalService(registry);
        volunteerManager = new VolunteerManager(); // Ensure it's instantiated
        shelterService = new ShelterService(registry, volunteerManager);
        scanner = new Scanner(System.in); // Just a placeholder, no input used
        medicalController = new MedicalRecordController(new MedicalRecordService(),scanner);
        controller = new AnimalController(animalService, medicalController, scanner, registry, queue, shelterService);
    }

    /**
     * Tests that listing animals when the registry is empty does not throw an exception.
     */
    @Test
    public void testListAnimalsEmpty() {
        controller.listAnimals(); // Should not throw
        assertEquals(0, registry.getAnimalCount());
    }

    /**
     * Tests sorting animals by name.
     * Currently calls controller logic that may depend on console input.
     * This serves more as an integration placeholder.
     */
    @Test
    public void testSortAnimalsByName() {
        registry.addAnimal(new Cat("Zelda", 3, "British", "short", true));
        registry.addAnimal(new Dog("Ace", 5, "Beagle", true));
        controller.sortAnimals(); // Console-driven; no assertions here
    }

    /**
     * Tests that an animal can be located by its ID after being added.
     */
    @Test
    public void testFindById() {
        Dog dog = new Dog("Max", 4, "Pug", true);
        registry.addAnimal(dog);
        assertNotNull(registry.findById(dog.getId()));
    }

    /**
     * Tests that searching by a partial name returns the expected animal.
     */
    @Test
    public void testFindByName() {
        Cat cat = new Cat("Mittens", 2, "Persian", "long", true);
        registry.addAnimal(cat);
        assertFalse(registry.searchByName("mitt").isEmpty());
    }

    /**
     * Tests that the species information of an animal is returned correctly.
     */
    @Test
    public void testFindBySpecies() {
        Cat cat = new Cat("Fluffy", 2, "Persian", "long", true);
        registry.addAnimal(cat);
        assertEquals("CAT", cat.getSpecies().name());
    }

    /**
     * Tests that an animal can be removed from the registry by its ID.
     */
    @Test
    public void testRemoveAnimal() {
        Dog dog = new Dog("Rex", 5, "Husky", false);
        registry.addAnimal(dog);
        boolean removed = registry.removeAnimalById(dog.getId());
        assertTrue(removed);
        assertEquals(0, registry.getAnimalCount());
    }
}
