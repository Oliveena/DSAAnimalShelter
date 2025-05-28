package patterns.structural.decorators;

import models.animals.Animal;
import java.util.logging.Logger;
import patterns.creational.factories.LogFactory;

/**
 * A concrete decorator that adds vaccination information to an {@link Animal}.
 * <p>
 * This decorator augments the wrapped animal's details with vaccination data,
 * and logs adoption events including vaccination info.
 */
public class VaccinationDecorator extends AnimalDecorator {
    private static final Logger logger = LogFactory.getLogger(VaccinationDecorator.class);

    /** The vaccination details associated with this animal. */
    private final String vaccinationDetails;

    /**
     * Constructs a new {@code VaccinationDecorator} wrapping the specified {@link Animal}
     * and associating it with vaccination details.
     *
     * @param animal the animal to decorate
     * @param vaccinationDetails details about the vaccination administered
     */
    public VaccinationDecorator(Animal animal, String vaccinationDetails) {
        super(animal);
        this.vaccinationDetails = vaccinationDetails;
        logger.info("Applied VaccinationDecorator to " + animal.getName() + " with vaccination: " + vaccinationDetails);
    }

    /**
     * Returns the decorated animal's details with appended vaccination information.
     *
     * @return a string describing the animal's details including vaccination info
     */
    @Override
    public String getDetails() {
        logger.fine("getDetails() called on VaccinationDecorator for " + decoratedAnimal.getName());
        return decoratedAnimal.getDetails() + " | Vaccination: " + vaccinationDetails;
    }

    /**
     * Returns the species of the animal.
     * <p>
     * Delegates to the base class property.
     *
     * @return the species string
     */
    @Override
    public String getSpecies() {
        return this.species;
    }

    /**
     * Returns the breed of the animal.
     * <p>
     * Delegates to the base class property.
     *
     * @return the breed string
     */
    @Override
    public String getBreed() {
        return this.breed;
    }

    /**
     * Marks the animal as adopted.
     * <p>
     * Logs the adoption event including vaccination information, then delegates
     * the adoption action to the wrapped animal.
     */
    @Override
    public void adopt() {
        logger.info(decoratedAnimal.getName() + " is being adopted (with vaccination info).");
        decoratedAnimal.adopt();
    }
}
