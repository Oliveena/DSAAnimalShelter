package decorators;

import logic.Animal;

public class VaccinationDecorator extends AnimalDecorator {
    private String vaccinationDetails;

    public VaccinationDecorator(Animal animal, String vaccinationDetails) {
        super(animal);  // Pass the decorated animal to the parent constructor
        this.vaccinationDetails = vaccinationDetails;
    }

    @Override
    public String getType() {
        return "";
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " | Vaccination: " + vaccinationDetails;
    }
}
