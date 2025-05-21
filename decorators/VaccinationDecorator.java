package decorators;

import logic.Animal;

/**
 * A concrete decorator that adds vaccination details to an {@link Animal}.
 * <p>
 * This class extends {@link AnimalDecorator} and adds vaccination-related information
 * to the original animal's details.
 * <p>
 * Example usage:
 * <pre>{@code
 * Animal vaccinatedCat = new VaccinationDecorator(new Cat("Whiskers", 3), "Rabies, Distemper");
 * System.out.println(vaccinatedCat.getDetails());
 * }</pre>
 */
public class VaccinationDecorator extends AnimalDecorator {
    private String vaccinationDetails;

    /**
     * Constructs a {@code VaccinationDecorator} that wraps the provided {@link Animal}
     * and adds vaccination details.
     *
     * @param animal the animal to decorate
     * @param vaccinationDetails the vaccination details to append to the animal's info
     */
    public VaccinationDecorator(Animal animal, String vaccinationDetails) {
        super(animal);  // Pass the decorated animal to the parent constructor
        this.vaccinationDetails = vaccinationDetails;
    }

    /**
     * Returns the type of the decorated animal.
     * <p>
     * This method delegates the call to the decorated animal's {@link Animal#getType()},
     * since the decorator is adding additional details, but the base type should still be preserved.
     *
     * @return a string indicating the animal's type
     */
    @Override
    public String getType() {
        return decoratedAnimal.getType();  // Delegating the call to the decorated animal
    }

    /**
     * Returns the details of the decorated animal, including the vaccination details.
     * <p>
     * This method overrides the base class's {@code getDetails} to append vaccination details
     * to the original animal's information.
     *
     * @return a string representing the animal's details, including vaccination information
     */
    @Override
    public String getDetails() {
        return super.getDetails() + " | Vaccination: " + vaccinationDetails;
    }
}
