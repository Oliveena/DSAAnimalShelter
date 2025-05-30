package patterns.creational.builders;

import models.animals.Lizard;

/**
 * A builder class for creating {@link Lizard} instances using the fluent builder pattern.
 * <p>
 * This class extends {@link AnimalBuilder} to support attributes specific to lizards,
 * such as whether the lizard is venomous.
 *
 * <pre>{@code
 * Lizard lizard = new LizardBuilder()
 *     .setName("Iggy")
 *     .setAge(2)
 *     .setBreed("Green Iguana")
 *     .setVenomous(false)
 *     .setMedicalRecord(record)
 *     .build();
 * }</pre>
 *
 * @see Lizard
 * @see AnimalBuilder
 */
public class LizardBuilder extends AnimalBuilder<Lizard, LizardBuilder> {

    private boolean isVenomous;

    /**
     * Returns the current instance of the builder for fluent method chaining.
     *
     * @return the current {@code LizardBuilder} instance
     */
    @Override
    protected LizardBuilder self() {
        return this;
    }

    /**
     * Sets whether the lizard is venomous.
     *
     * @param isVenomous {@code true} if the lizard is venomous, {@code false} otherwise
     * @return the current builder instance
     */
    public LizardBuilder setVenomous(boolean isVenomous) {
        this.isVenomous = isVenomous;
        return this;
    }

    /**
     * Constructs and returns a new {@link Lizard} using the configured builder values.
     * <p>
     * The medical record (if set) is assigned after the lizard is created.
     *
     * @return a fully configured {@code Lizard} object
     */
    @Override
    public Lizard build() {
        Lizard lizard = new Lizard(name, age, breed, isVenomous);
        lizard.setMedicalRecord(medicalRecord);
        return lizard;
    }
}
