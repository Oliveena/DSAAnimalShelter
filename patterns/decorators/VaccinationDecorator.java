package patterns.decorators;

import models.Adoptable;
import models.animals.Animal;
import models.MedicalRecord;
import patterns.decorators.AnimalDecorator;
import java.util.logging.Logger;
import patterns.factories.LogFactory;

public class VaccinationDecorator extends AnimalDecorator {
    private static final Logger logger = LogFactory.getLogger(VaccinationDecorator.class);

    private final String vaccinationDetails;

    public VaccinationDecorator(Animal animal, String vaccinationDetails) {
        super(animal);
        this.vaccinationDetails = vaccinationDetails;
        logger.info(STR."Applied VaccinationDecorator to \{animal.getName()} with vaccination: \{vaccinationDetails}");
    }

    @Override
    public String getDetails() {
        String details = STR."\{decoratedAnimal.getDetails()} | Vaccination: \{vaccinationDetails}";
        logger.fine(STR."getDetails() called on VaccinationDecorator for \{decoratedAnimal.getName()}");
        return details;
    }

    @Override
    public String getSpecies() {
        return this.species;
    }

    @Override
    public String getBreed() {
        return this.breed;
    }

    @Override
    public void adopt() {
        logger.info(STR."\{decoratedAnimal.getName()} is being adopted (with vaccination info).");
        decoratedAnimal.adopt();
    }
}
