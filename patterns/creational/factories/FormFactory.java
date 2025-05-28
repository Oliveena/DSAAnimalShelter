package patterns.creational.factories;

import models.*;
import models.animals.Animal;
import models.forms.AdoptionForm;
import models.forms.AdoptionFormInstance;
import models.forms.ReturnToShelterForm;
import models.forms.ReturnToShelterFormInstance;

import java.time.LocalDate;

public class FormFactory {

    public static AdoptionForm createAdoptionForm(Adopter adopter, Animal animal, LocalDate date) {
        return new AdoptionFormInstance(adopter, animal, date);
    }

    public static ReturnToShelterForm createReturnForm(Animal animal, LocalDate date, String reason) {
        return new ReturnToShelterFormInstance(animal, date, reason);
    }
}
