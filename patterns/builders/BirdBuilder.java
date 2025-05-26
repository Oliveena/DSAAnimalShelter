package patterns.builders;

import models.animals.Bird;

public class BirdBuilder extends AnimalBuilder<Bird, BirdBuilder> {
    private String breed;
    private boolean canFly;

    @Override
    protected BirdBuilder self() {
        return this;
    }


    public BirdBuilder setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    public BirdBuilder setCanFly(boolean canFly) {
        this.canFly = canFly;
        return this;
    }

    @Override
    public Bird build() {
        return new Bird(name, age, breed, canFly, medicalRecord);  // match new constructor
    }
}
