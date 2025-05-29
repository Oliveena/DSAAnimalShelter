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

    private AnimalService animalService;

    @BeforeEach
    public void setUp() {
        AnimalRegistry registry = new AnimalRegistry(1000);
        animalService = new AnimalService(registry);
        // No controller needed if you want pure synchronous test
    }

    @Test
    public void testAddManyAnimalsPerformance() {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 500; i++) {
            Dog dog = new Dog("Dog" + i, 2, "Mixed", true);
            animalService.addAnimal(dog);  // direct sync call
        }

        long end = System.currentTimeMillis();
        long duration = end - start;

        System.out.println("Performance test took " + duration + " ms");
        assertTrue(duration < 1000, "App took too long to add 500 animals");
    }
}
