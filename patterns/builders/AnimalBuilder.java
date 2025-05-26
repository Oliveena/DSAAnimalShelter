package patterns.builders;

import models.animals.Animal;
import models.MedicalRecord;
import java.util.logging.Logger;
import patterns.factories.LogFactory;

public abstract class AnimalBuilder<T extends Animal, B extends AnimalBuilder<T, B>> {
    private static final Logger logger = LogFactory.getLogger(AnimalBuilder.class);

    protected String name;
    protected int age;
    protected MedicalRecord medicalRecord;
    protected String species;
    protected String breed;

    protected abstract B self();

    public B setName(String name) {
        this.name = name;
        logger.fine(STR."Set name: \{name}");
        return self();
    }

    public B setAge(int age) {
        this.age = age;
        logger.fine(STR."Set age: \{age}");
        return self();
    }

    public B setMedicalRecord(MedicalRecord record) {
        this.medicalRecord = record;
        logger.fine("Set medical record");
        return self();
    }

    public B setSpecies(String species) {
        this.species = species;
        logger.fine(STR."Set species: \{species}");
        return self();
    }

    public B setBreed(String breed) {
        this.breed = breed;
        logger.fine(STR."Set breed: \{breed}");
        return self();
    }

    public abstract T build(); // Let subclasses log if build logic is complex
}
