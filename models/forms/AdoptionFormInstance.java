package models.forms;

import models.Adopter;
import models.animals.Animal;

import java.time.LocalDate;

public class AdoptionFormInstance implements AdoptionForm {
    private Adopter adopter;
    private Animal animal;
    private LocalDate date;

    public AdoptionFormInstance(Adopter adopter, Animal animal, LocalDate date) {
        this.adopter = adopter;
        this.animal = animal;
        this.date = date;
    }

    @Override
    public void submit() {
        System.out.println("Adoption form submitted for " + adopter.getName());
    }

    @Override
    public String getDetails() {
        return "AdoptionForm: " + adopter.getName() + " adopted " + animal.getName() + " on " + date;
    }
}

