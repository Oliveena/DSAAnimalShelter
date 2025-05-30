package models.forms;

import models.animals.Animal;

import java.time.LocalDate;

/**
 * Represents an instance of a return-to-shelter form.
 * <p>
 * This class implements {@link ReturnToShelterForm} and captures details
 * about an animal being returned to the shelter, including the date and reason.
 * </p>
 */
public class ReturnToShelterFormInstance implements ReturnToShelterForm {

    private Animal animal;
    private LocalDate date;
    private String reason;

    /**
     * Constructs a new return form instance for a specific animal.
     *
     * @param animal the animal being returned
     * @param date the date of the return
     * @param reason the reason for returning the animal
     */
    public ReturnToShelterFormInstance(Animal animal, LocalDate date, String reason) {
        this.animal = animal;
        this.date = date;
        this.reason = reason;
    }

    /**
     * Submits the return form.
     * <p>
     * Typically triggers any processing required when an animal is returned.
     * </p>
     */
    @Override
    public void submit() {
        System.out.println("Return form submitted for " + animal.getName());
    }

    /**
     * Returns details about the return form.
     *
     * @return a string describing the return including animal name, date, and reason
     */
    @Override
    public String getDetails() {
        return "ReturnForm: " + animal.getName() + " returned on " + date + " due to " + reason;
    }
}
