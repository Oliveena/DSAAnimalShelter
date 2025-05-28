package models.employees;

import patterns.behavioral.observer.VolunteerObserver;

/**
 * Represents a volunteer registered with the animal shelter.
 * <p>
 * Volunteers receive updates via the Observer pattern (see {@link VolunteerObserver})
 * and are notified when relevant shelter events occur (e.g., animal intake, tasks, emergencies).
 */
public class Volunteer implements VolunteerObserver {
    private String name;

    /**
     * Constructs a new {@code Volunteer} with the specified name.
     *
     * @param name the volunteer's name
     */
    public Volunteer(String name) {
        this.name = name;
    }

    /**
     * Called when the volunteer is notified of an event.
     * <p>
     * This method is part of the Observer pattern and provides volunteers
     * with a message describing what happened in the shelter.
     *
     * @param event a message describing the event or update
     */
    @Override
    public void update(String event) {
        System.out.println("Volunteer " + name + " notified: " + event);
    }
}
