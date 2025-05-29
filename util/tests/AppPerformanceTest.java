package util.tests;

import controllers.AnimalController;
import models.AnimalRegistry;
import models.ShelterQueue;
import models.animals.Dog;
import patterns.behavioral.observer.VolunteerManager;
import services.AnimalService;
import services.ShelterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppPerformanceTest {

    private AnimalController controller;

    @BeforeEach
    public void setUp() {
        AnimalRegistry registry = new AnimalRegistry(1000); // Increase capacity to avoid limit
        ShelterQueue queue = new ShelterQueue();
        VolunteerManager volunteerManager = new VolunteerManager();
        AnimalService animalService = new AnimalService(registry);
        ShelterService shelterService = new ShelterService(registry, volunteerManager);

        controller = new AnimalController(animalService, null, null, registry, queue, shelterService);
    }

    @Test
    public void testAddManyAnimalsPerformance() {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 500; i++) {
            Dog dog = new Dog("Dog" + i, 2, "Mixed", true);
            controller.addAnimalDirectly(dog);
        }

        long end = System.currentTimeMillis();
        long duration = end - start;

        System.out.println("Performance test took " + duration + " ms");
        assertTrue(duration < 1000, "App took too long to add 500 animals");
    }
}
