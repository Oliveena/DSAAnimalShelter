package builders;

import logic.Dog;

public class DogBuilder {
    private String name;
    private int age;
    private String breed;
    private boolean isTrained;

    public DogBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public DogBuilder setAge(int age) {
        this.age = age;
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

    public Dog build() {
        return new Dog(name, age, breed, isTrained);
    }
}
