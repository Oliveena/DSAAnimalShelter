package patterns.creational.factories;

import models.Adopter;
import models.animals.Animal;
import models.forms.AdoptionForm;
import models.forms.AdoptionFormInstance;
import models.forms.ReturnToShelterForm;
import models.forms.ReturnToShelterFormInstance;

import java.time.LocalDate;

/**
 * Factory class for creating form instances related to animal adoption and returns.
 * <p>
 * Uses static factory methods to encapsulate the creation logic of various forms,
 * allowing clients to obtain form instances without coupling to concrete implementations.
 */
public class FormFactory {

    /**
     * Creates a new adoption form instance for the given adopter and animal on the specified date.
     *
     * @param adopter the adopter submitting the adoption form
     * @param animal  the animal being adopted
     * @param date    the adoption date
     * @return a new {@link AdoptionForm} instance
     */
    public static AdoptionForm createAdoptionForm(Adopter adopter, Animal animal, LocalDate date) {
        return new AdoptionFormInstance(adopter, animal, date);
    }

    /**
     * Creates a new return-to-shelter form instance for the given animal, return date, and reason.
     *
     * @param animal the animal being returned
     * @param date   the return date
     * @param reason the reason for the return
     * @return a new {@link ReturnToShelterForm} instance
     */
    public static ReturnToShelterForm createReturnForm(Animal animal, LocalDate date, String reason) {
        return new ReturnToShelterFormInstance(animal, date, reason);
    }
}
