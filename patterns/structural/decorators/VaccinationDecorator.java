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
 */
public class VaccinationDecorator extends AnimalDecorator {
    private static final Logger logger = LogFactory.getLogger(VaccinationDecorator.class);

    private final String vaccinationDetails;

    public VaccinationDecorator(Animal animal, String vaccinationDetails) {
        super(animal);
        if (vaccinationDetails == null || vaccinationDetails.isBlank()) {
            throw new IllegalArgumentException("Vaccination details must not be null or empty");
        }
        this.vaccinationDetails = vaccinationDetails;
        logger.info("Applied VaccinationDecorator to " + animal.getName() + " with vaccination: " + vaccinationDetails);
    }

    @Override
    public String getDetails() {
        logger.fine("getDetails() called on VaccinationDecorator for " + decoratedAnimal.getName());
        return decoratedAnimal.getDetails() + " | Vaccination: " + vaccinationDetails;
    }

    public String getVaccinationDetails() {
        return vaccinationDetails;
    }

    @Override
    public Species getSpecies() {
        return decoratedAnimal.getSpecies();
    }

    @Override
    public String getBreed() {
        return decoratedAnimal.getBreed();
    }

    @Override
    public void adopt() {
        logger.info(decoratedAnimal.getName() + " is being adopted (with vaccination info).");
        decoratedAnimal.adopt();
    }

    @Override
    public String toString() {
        return decoratedAnimal.toString() + " | Vaccination: " + vaccinationDetails;
    }
}
