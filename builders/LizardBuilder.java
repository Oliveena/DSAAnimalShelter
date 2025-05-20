package builders;

import logic.Lizard;

public class LizardBuilder {
    private String name;
    private int age;
    private String breed;
    private boolean isVenomous;

    public LizardBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public LizardBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    public LizardBuilder setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    public LizardBuilder setVenomous(boolean isVenomous) {
        this.isVenomous = isVenomous;
        return this;
    }

    public Lizard build() {
        return new Lizard(name, age, breed, isVenomous);
    }
}