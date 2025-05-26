package patterns.builders;

import models.Animal;
import models.MedicalRecord;

public abstract class AnimalBuilder<T extends Animal, B extends AnimalBuilder<T, B>> {
    protected String name;
    protected int age;
    protected MedicalRecord medicalRecord;
    protected String species;
    protected String breed;

    // Cast "this" to B, so subclasses get their own builder type returned
    protected abstract B self();

    public B setName(String name) {
        this.name = name;
        return self();
    }

    public B setAge(int age) {
        this.age = age;
        return self();
    }

    public B setMedicalRecord(MedicalRecord record) {
        this.medicalRecord = record;
        return self();
    }

    public B setSpecies(String species) {
        this.species = species;
        return self();
    }

    public B setBreed(String breed) {
        this.breed = breed;
        return self();
    }

    public abstract T build();
}
