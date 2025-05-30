package services;

import models.Task;
import models.employees.Volunteer;
import patterns.behavioral.observer.VolunteerManager;

import java.time.LocalDate;

/**
 * Service class responsible for volunteer-related operations
 * such as registering volunteers and assigning tasks.
 */
public class VolunteerService {

    private final VolunteerManager volunteerManager;
    private final ShelterService shelterService;

    /**
     * Constructs a VolunteerService with the given VolunteerManager and ShelterService.
     *
     * @param volunteerManager the manager handling volunteer registrations and notifications
     * @param shelterService the service managing shelter-related tasks (must not be null)
     * @throws IllegalArgumentException if shelterService is null
     */
    public VolunteerService(VolunteerManager volunteerManager, ShelterService shelterService) {
        if (shelterService == null) {
            throw new IllegalArgumentException("ShelterService cannot be null");
        }
        this.volunteerManager = volunteerManager;
        this.shelterService = shelterService;
    }

    /**
     * Registers a new volunteer by name.
     *
     * @param name the name of the volunteer to register
     */
    public void registerVolunteer(String name) {
        volunteerManager.registerVolunteer(new Volunteer(name));
    }

    /**
     * Adds a task to the shelter service.
     *
     * @param description description of the task
     * @param dueDate the due date for the task
     */
    public void addTask(String description, LocalDate dueDate) {
        shelterService.addTask(new Task(description, dueDate));
    }
}
