package models.animals;

public class Dog extends Animal {

    private final boolean isTrained;

    /**
     * Constructs a new dog.
     *
     * @param name the name of the dog
     * @param age the age of the dog
     * @param breed the breed of the dog
     * @param isTrained whether the dog is trained
     */
    public Dog(String name, int age, String breed, boolean isTrained) {
        super(name, age, Species.DOG, breed);
        this.isTrained = isTrained;
    }

    @Override
    public String getDetails() {
        return String.format("Dog [ID: %s, Name: %s, Age: %d, Breed: %s, Trained: %s]",
                getId(), getName(), getAge(), getBreed(), isTrained ? "Yes" : "No");
    }

    @Override
    public String getBreed() {
        return breed;
    }

    @Override
    public void adopt() {
        System.out.println(getName() + " the dog has been adopted.");
    }

    @Override
    public void returnToShelter() {
        System.out.println(getName() + " the dog has been returned to the shelter.");
    }
}