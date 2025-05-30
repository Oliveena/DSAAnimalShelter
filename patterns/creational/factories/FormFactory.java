package patterns.creational.factories;

import models.Adopter;
import models.animals.Animal;
import models.forms.AdoptionForm;
import models.forms.AdoptionFormInstance;
import models.forms.ReturnToShelterForm;
import models.forms.ReturnToShelterFormInstance;

import java.time.LocalDate;

/**
 * A factory class for creating form instances used in the animal shelter system.
 * <p>
 * This class centralizes the creation logic for various types of forms,
 * including adoption and return-to-shelter forms.
 *
 * <p>Usage Example:
 * <pre>{@code
 * AdoptionForm form = FormFactory.createAdoptionForm(adopter, animal, LocalDate.now());
 * }</pre>
 *
 * @see AdoptionForm
 * @see ReturnToShelterForm
 */
public class FormFactory {

    /**
     * Creates a new {@link AdoptionForm} instance with the given adopter, animal, and adoption date.
     *
     * @param adopter the person adopting the animal
     * @param animal  the animal being adopted
     * @param date    the date of the adoption
     * @return a new {@code AdoptionFormInstance}
     */
    public static AdoptionForm createAdoptionForm(Adopter adopter, Animal animal, LocalDate date) {
        return new AdoptionFormInstance(adopter, animal, date);
    }

    /**
     * Creates a new {@link ReturnToShelterForm} instance with the given animal, return date, and reason.
     *
     * @param animal the animal being returned to the shelter
     * @param date   the date of the return
     * @param reason the reason for the return
     * @return a new {@code ReturnToShelterFormInstance}
     */
    public static ReturnToShelterForm createReturnForm(Animal animal, LocalDate date, String reason) {
        return new ReturnToShelterFormInstance(animal, date, reason);
    }
}
