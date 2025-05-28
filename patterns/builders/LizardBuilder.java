package patterns.builders;

import models.animals.Lizard;

/**
 * Builder for creating {@link Lizard} instances.
 * <p>
 * Extends {@link AnimalBuilder} with lizard-specific properties such as breed and venomous status.
 */
public class LizardBuilder extends AnimalBuilder<Lizard, LizardBuilder> {
    private String breed;
    private boolean isVenomous;

    @Override
    protected LizardBuilder self() {
        return this;
    }

    /**
     * Sets the breed of the lizard.
     *
     * @param breed the breed name
     * @return this builder instance for chaining
     */
    public LizardBuilder setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    /**
     * Sets whether the lizard is venomous.
     *
     * @param isVenomous true if venomous; false otherwise
     * @return this builder instance for chaining
     */
    public LizardBuilder setVenomous(boolean isVenomous) {
        this.isVenomous = isVenomous;
        return this;
    }

    /**
     * Builds a new {@link Lizard} instance with the configured properties.
     *
     * @return a new {@link Lizard} object
     */
    @Override
    public Lizard build() {
        Lizard lizard = new Lizard(name, age, species, breed, isVenomous);
        lizard.setMedicalRecord(medicalRecord);
        return lizard;
    }
}
