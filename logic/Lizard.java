package logic;

public class Lizard extends Animal {
    private String breed;
    private boolean isVenomous;

    public Lizard(String name, int age, String breed, boolean isVenomous) {
        super(name, age);
        this.breed = breed;
        this.isVenomous = isVenomous;
    }

    @Override
    public String getType() {
        return "Lizard";
    }

    @Override
    public String getDetails() {
        return String.format("Lizard [ID: %s, Name: %s, Age: %d, Breed: %s, Venomous: %s]",
                id, name, age, breed, isVenomous ? "Yes" : "No");
    }
}
