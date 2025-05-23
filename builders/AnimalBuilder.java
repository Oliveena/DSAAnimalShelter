package builders;

import domain.Animal;
import domain.MedicalRecord;

public abstract class AnimalBuilder<T extends Animal, B extends AnimalBuilder<T, B>> {
    protected String name;
    protected int age;
    protected MedicalRecord medicalRecord;

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

    public abstract T build();
}
