package builders;

import logic.Cat;

public class CatBuilder {
    private String name;
    private int age;
    private String furLength;
    private boolean isIndoor;

    public CatBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CatBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    public CatBuilder setFurLength(String furLength) {
        this.furLength = furLength;
        return this;
    }

    public CatBuilder setIndoor(boolean indoor) {
        this.isIndoor = indoor;
        return this;
    }

    public Cat build() {
        return new Cat(name, age, furLength, isIndoor);
    }
}
