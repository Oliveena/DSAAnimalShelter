package patterns.builders;

import models.Dog;
/*This is a classic case for using a generic self-referential (curiously recurring) generic pattern (CRGP) in Java patterns.builders.
This lets the base builder return the concrete subclass builder type from all setter methods, enabling smooth chaining.
* */

public class DogBuilder extends AnimalBuilder<Dog, DogBuilder> {
    private String breed;
    private boolean isTrained;

    @Override
    protected DogBuilder self() {
        return this;
    }

    public DogBuilder setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    public DogBuilder setTrained(boolean trained) {
        this.isTrained = trained;
        return this;
    }

    @Override
    public Dog build() {
        Dog dog = new Dog(name, age, species, breed, isTrained);
        dog.setMedicalRecord(medicalRecord);
        return dog;
    }
}
