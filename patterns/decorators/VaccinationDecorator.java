package patterns.decorators;

import models.Adoptable;
import models.Animal;
import models.MedicalRecord;
import patterns.decorators.AnimalDecorator;

public class VaccinationDecorator extends AnimalDecorator {
    private final String vaccinationDetails;

    public VaccinationDecorator(Animal animal, String vaccinationDetails) {
        super(animal);
        this.vaccinationDetails = vaccinationDetails;
    }

    @Override
    public String getDetails() {
        return decoratedAnimal.getDetails() + " | Vaccination: " + vaccinationDetails;
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
        decoratedAnimal.adopt(); // optional delegation
    }
}
