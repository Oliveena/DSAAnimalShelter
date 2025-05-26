package patterns.builders;

import models.animals.Cat;

public class CatBuilder extends AnimalBuilder<Cat, CatBuilder> {
    private String furLength;
    private boolean indoor;

    @Override
    protected CatBuilder self() {
        return this;
    }

    public CatBuilder setFurLength(String furLength) {
        this.furLength = furLength;
        return this;
    }

    public CatBuilder setIndoor(boolean indoor) {
        this.indoor = indoor;
        return this;
    }

    @Override
    public Cat build() {
        Cat cat = new Cat(name, age, species, breed, furLength, indoor);
        cat.setMedicalRecord(medicalRecord);
        return cat;
    }
}
