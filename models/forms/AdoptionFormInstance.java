package models.forms;

import models.Adopter;
import models.animals.Animal;

import java.time.LocalDate;

/**
 * Represents a concrete adoption form instance in the animal shelter system.
 * <p>
 * This class implements the {@link AdoptionForm} interface and encapsulates
 * the details of a specific adoption, including the adopter, the adopted animal,
 * and the adoption date.
 * </p>
 */
public class AdoptionFormInstance implements AdoptionForm {
    private Adopter adopter;
    private Animal animal;
    private LocalDate date;

    /**
     * Constructs a new {@code AdoptionFormInstance} with the specified adopter,
     * animal, and adoption date.
     *
     * @param adopter the adopter submitting the form
     * @param animal  the animal being adopted
     * @param date    the date of adoption
     */
    public AdoptionFormInstance(Adopter adopter, Animal animal, LocalDate date) {
        this.adopter = adopter;
        this.animal = animal;
        this.date = date;
    }

    /**
     * Submits the adoption form.
     * <p>
     * This implementation simply prints a confirmation message to the console.
     * In a real system, this method might save the form to a database or trigger
     * additional workflows.
     * </p>
     */
    @Override
    public void submit() {
        System.out.println("Adoption form submitted for " + adopter.getName());
    }

    /**
     * Returns a string containing the details of this adoption form.
     *
     * @return a string describing the adopter, the animal, and the adoption date
     */
    @Override
    public String getDetails() {
        return "AdoptionForm: " + adopter.getName() + " adopted " + animal.getName() + " on " + date;
    }
}
