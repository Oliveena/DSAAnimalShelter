// test class created, but not runnable (wring repo structure, no Maven enabled, no Junit .jar files downloaded

//import data.AnimalRegistry;
//import logic.Animal;
//import logic.Cat;
//import logic.Dog;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class AnimalRegistryTest {
//
//    @Test
//    public void testAddAnimal() {
//        AnimalRegistry registry = new AnimalRegistry();
//        Animal dog = new Dog("Rex", 5, "Labrador", true);
//
//        registry.addAnimal(dog);
//
//        assertEquals(1, registry.getAllAnimals().size());
//        assertEquals("Rex", registry.getAllAnimals().get(0).getName());
//    }
//
//    @Test
//    public void testSearchByName() {
//        AnimalRegistry registry = new AnimalRegistry();
//        registry.addAnimal(new Cat("Mittens", 2, "short", true));
//        registry.addAnimal(new Cat("Whiskers", 3, "long", false));
//
//        var results = registry.searchByName("mitt");
//        assertFalse(results.isEmpty());
//        assertEquals("Mittens", results.get(0).getName());
//    }
//}