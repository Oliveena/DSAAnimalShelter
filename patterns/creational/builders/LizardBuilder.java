package patterns.creational.builders;

import models.animals.Lizard;
import models.animals.Species;

public class LizardBuilder extends AnimalBuilder<Lizard, LizardBuilder> {
    private boolean isVenomous;

    @Override
    protected LizardBuilder self() {
        return this;
    }

    public LizardBuilder setVenomous(boolean isVenomous) {
        this.isVenomous = isVenomous;
        return this;
    }

    @Override
    public Lizard build() {
        Lizard lizard = new Lizard(name, age, breed, isVenomous);
        lizard.setMedicalRecord(medicalRecord);
        return lizard;
    }
}
