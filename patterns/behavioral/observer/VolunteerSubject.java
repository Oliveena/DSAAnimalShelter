package patterns.behavioral.observer;

/**
 * Interface for subjects that manage and notify {@link VolunteerObserver} instances.
 * <p>
 * Defines methods to register, remove, and notify volunteers about events.
 */
public interface VolunteerSubject {
    /**
     * Registers a volunteer observer to receive event notifications.
     *
     * @param volunteer the volunteer observer to register
     */
    void registerVolunteer(VolunteerObserver volunteer);

    /**
     * Removes a volunteer observer so it no longer receives notifications.
     *
     * @param volunteer the volunteer observer to remove
     */
    void removeVolunteer(VolunteerObserver volunteer);

    /**
     * Notifies all registered volunteers of an event.
     *
     * @param event the event description to send to observers
     */
    void notifyVolunteers(String event);
}
