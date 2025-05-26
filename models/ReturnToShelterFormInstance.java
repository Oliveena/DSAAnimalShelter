package models;

import models.animals.Animal;

import java.time.LocalDate;

public class ReturnToShelterFormInstance implements ReturnToShelterForm {
    private Animal animal;
    private LocalDate date;
    private String reason;

    public ReturnToShelterFormInstance(Animal animal, LocalDate date, String reason) {
        this.animal = animal;
        this.date = date;
        this.reason = reason;
    }

    @Override
    public void submit() {
        System.out.println(STR."Return form submitted for \{animal.getName()}");
    }

    @Override
    public String getDetails() {
        return STR."ReturnForm: \{animal.getName()} returned on \{date} due to \{reason}";
    }
}
