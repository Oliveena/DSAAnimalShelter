package builders;

import domain.Bird;

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
        Bird bird = new Bird(name, age, breed, canFly);
        bird.setMedicalRecord(medicalRecord);
        return bird;
    }
}
