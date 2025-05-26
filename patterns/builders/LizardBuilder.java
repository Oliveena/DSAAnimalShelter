package patterns.builders;

import models.Lizard;

public class LizardBuilder extends AnimalBuilder<Lizard, LizardBuilder> {
    private String breed;
    private boolean isVenomous;

    @Override
    protected LizardBuilder self() {
        return this;
    }

    public LizardBuilder setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    public LizardBuilder setVenomous(boolean isVenomous) {
        this.isVenomous = isVenomous;
        return this;
    }

    @Override
    public Lizard build() {
        Lizard lizard = new Lizard(name, age, species, breed, isVenomous);
        lizard.setMedicalRecord(medicalRecord);
        return lizard;
    }
}
