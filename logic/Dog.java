package logic;

public class Dog extends Animal {
    private String breed;
    private boolean isTrained;

    public Dog(String name, int age, String breed, boolean isTrained) {
        super(name, age);
        this.breed = breed;
        this.isTrained = isTrained;
    }

    @Override
    public String getType() { return "Dog"; }

    @Override
    public String getDetails() {
        return String.format("Dog [ID: %s, Name: %s, Age: %d, Breed: %s, Trained: %s]",
                             id, name, age, breed, isTrained ? "Yes" : "No");
    }
}


