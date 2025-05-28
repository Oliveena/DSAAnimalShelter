package services;

import models.Task;
import models.Volunteer;
import patterns.behavioral.observer.VolunteerManager;

import java.time.LocalDate;

public class VolunteerService {
    private final VolunteerManager volunteerManager;
    private ShelterService shelterService;

    public VolunteerService(VolunteerManager volunteerManager, ShelterService shelterService) {
        if (shelterService == null) {
            throw new IllegalArgumentException("ShelterService cannot be null");
        }
        this.volunteerManager = volunteerManager;
        this.shelterService = shelterService;
    }

    public void registerVolunteer(String name) {
        volunteerManager.registerVolunteer(new Volunteer(name));
    }

    public void addTask(String description, LocalDate dueDate) {
        shelterService.addTask(new Task(description, dueDate));
    }
}
