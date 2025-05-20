package builders;

import logic.Bird;

public class BirdBuilder {
    private String name;
    private int age;
    private String breed;
    private boolean  canFly;

    public BirdBuilder(String name, int age, String breed, boolean canFly) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.canFly =  true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public boolean  getCanFly() {
        return canFly;
    }

    public void setCanFly(boolean canFly) {
        canFly = true;
    }

    public Bird build() {
        return new Bird(name, age, breed, canFly);
    }

}

