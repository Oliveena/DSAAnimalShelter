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

    public static Species fromString(String text) {
        for (Species s : Species.values()) {
            if (s.displayName.equalsIgnoreCase(text)) {
                return s;
            }
        }
        throw new IllegalArgumentException("No species with name " + text);
    }
}