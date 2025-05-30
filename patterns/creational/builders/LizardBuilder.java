package patterns.creational.builders;

import models.animals.Lizard;

/**
 * Builder for creating instances of {@link Lizard} with fluent configuration.
 * <p>
 * Supports setting common animal attributes inherited from {@link AnimalBuilder}
 * as well as specific properties like venomous status.
 */
public class LizardBuilder extends AnimalBuilder<Lizard, LizardBuilder> {
    private boolean isVenomous;

    /**
     * Returns the current builder instance for method chaining.
     *
     * @return this {@link LizardBuilder} instance
     */
    @Override
    protected LizardBuilder self() {
        return this;
    }

    /**
     * Sets whether the lizard is venomous.
     *
     * @param isVenomous true if the lizard is venomous; false otherwise
     * @return this {@link LizardBuilder} instance for chaining
     */
    public LizardBuilder setVenomous(boolean isVenomous) {
        this.isVenomous = isVenomous;
        return this;
    }

    /**
     * Builds and returns a {@link Lizard} instance with the configured properties.
     * Medical records, if set, will be attached to the lizard.
     *
     * @return a new {@link Lizard} instance
     */
    @Override
    public Lizard build() {
        Lizard lizard = new Lizard(name, age, breed, isVenomous);
        lizard.setMedicalRecord(medicalRecord);
        return lizard;
    }
}
