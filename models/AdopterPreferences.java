package models;

public class AdopterPreferences {
    private String species;
    private String breed;
    private Integer maxAge;

    public AdopterPreferences() {}

    public AdopterPreferences(String species, String breed, Integer maxAge) {
        this.species = species;
        this.breed = breed;
        this.maxAge = maxAge;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    @Override
    public String toString() {
        return STR."AdopterPreferences{species='\{species}', breed='\{breed}', maxAge=\{maxAge}}";
    }
}
