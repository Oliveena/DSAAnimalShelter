package models.animals;

public enum Species {
    DOG("Dog"),
    CAT("Cat"),
    BIRD("Bird"),
    LIZARD("Lizard");

    private final String displayName;

    Species(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
