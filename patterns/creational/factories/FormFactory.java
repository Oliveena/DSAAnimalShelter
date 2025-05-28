package patterns.creational.factories;

import models.*;
import models.animals.Animal;

import java.time.LocalDate;

public class FormFactory {

    public static AdoptionForm createAdoptionForm(Adopter adopter, Animal animal, LocalDate date) {
        return new AdoptionFormInstance(adopter, animal, date);
    }

    public static ReturnToShelterForm createReturnForm(Animal animal, LocalDate date, String reason) {
        return new ReturnToShelterFormInstance(animal, date, reason);
    }
}
