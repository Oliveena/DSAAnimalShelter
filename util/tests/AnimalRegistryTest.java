package util.tests;

import models.AnimalRegistry;
import models.animals.Animal;
import models.animals.Cat;
import models.animals.Dog;
import org.junit.jupiter.api.Test;

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
}