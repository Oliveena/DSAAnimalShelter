package patterns.structural.decorators;

import models.animals.Animal;
import java.util.logging.Logger;

import models.animals.Species;
import patterns.creational.factories.LogFactory;

/**
 * A concrete decorator that adds vaccination information to an {@link Animal}.
 * <p>
 * This decorator augments the wrapped animal's details with vaccination data,
 * and logs adoption events including vaccination info.
 * <p>
 * Usage example:
 * <pre>
 *     Animal vaccinatedDog = new VaccinationDecorator(dog, "Rabies, Distemper");
 *     System.out.println(vaccinatedDog.getDetails());
 *     vaccinatedDog.adopt();
 * </pre>
 */
public class VaccinationDecorator extends AnimalDecorator {
    private static final Logger logger = LogFactory.getLogger(VaccinationDecorator.class);

    private final String vaccinationDetails;

    /**
     * Constructs a VaccinationDecorator wrapping the specified animal
     * with given vaccination details.
     *
     * @param animal The animal to decorate.
     * @param vaccinationDetails Details of the vaccination(s); must not be null or empty.
     * @throws IllegalArgumentException if vaccinationDetails is null or blank.
     */
    public VaccinationDecorator(Animal animal, String vaccinationDetails) {
        super(animal);
        if (vaccinationDetails == null || vaccinationDetails.isBlank()) {
            throw new IllegalArgumentException("Vaccination details must not be null or empty");
        }
        this.vaccinationDetails = vaccinationDetails;
        logger.info("Applied VaccinationDecorator to " + animal.getName() + " with vaccination: " + vaccinationDetails);
    }

    /**
     * Returns the detailed description of the animal, including vaccination information.
     *
     * @return Combined details string.
     */
    @Override
    public String getDetails() {
        logger.fine("getDetails() called on VaccinationDecorator for " + decoratedAnimal.getName());
        return decoratedAnimal.getDetails() + " | Vaccination: " + vaccinationDetails;
    }

    /**
     * Returns the vaccination details.
     *
     * @return The vaccination information string.
     */
    public String getVaccinationDetails() {
        return vaccinationDetails;
    }

    /**
     * Returns the species of the decorated animal.
     *
     * @return The animal species.
     */
    @Override
    public Species getSpecies() {
        return decoratedAnimal.getSpecies();
    }

    /**
     * Returns the breed of the decorated animal.
     *
     * @return The animal breed.
     */
    @Override
    public String getBreed() {
        return decoratedAnimal.getBreed();
    }

    /**
     * Performs the adoption operation, logging the event with vaccination info.
     */
    @Override
    public void adopt() {
        logger.info(decoratedAnimal.getName() + " is being adopted (with vaccination info).");
        decoratedAnimal.adopt();
    }

    /**
     * Returns the string representation of the animal, including vaccination details.
     *
     * @return String including base animal info and vaccination details.
     */
    @Override
    public String toString() {
        return decoratedAnimal.toString() + " | Vaccination: " + vaccinationDetails;
    }
}
