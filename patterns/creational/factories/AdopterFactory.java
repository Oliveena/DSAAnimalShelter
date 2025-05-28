package patterns.creational.factories;

import models.Adopter;

/**
 * A factory class for creating instances of {@link Adopter}.
 * <p>
 * This factory encapsulates object creation logic for {@code Adopter} instances,
 * making it easier to manage default values, validations, or future enhancements
 * in one place without affecting calling code.
 */
public class AdopterFactory {

    /**
     * Creates a new {@link Adopter} with the given name.
     *
     * @param name the name of the adopter
     * @return a new {@code Adopter} instance
     * @throws IllegalArgumentException if the name is null or empty
     */
    public static Adopter createAdopter(String name) {
        return new Adopter(name);
    }
}
