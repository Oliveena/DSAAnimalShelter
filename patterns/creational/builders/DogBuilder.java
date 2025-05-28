package patterns.creational.builders;

import models.animals.Dog;
import models.animals.Species;

public class DogBuilder extends AnimalBuilder<Dog, DogBuilder> {
    private boolean isTrained;

    @Override
    protected DogBuilder self() {
        return this;
    }

    public DogBuilder setTrained(boolean trained) {
        this.isTrained = trained;
        return this;
    }

    @Override
    public Dog build() {
        Dog dog = new Dog(name, age, breed, isTrained);  // match Dog constructor
        dog.setMedicalRecord(medicalRecord);
        return dog;
    }
}
