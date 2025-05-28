package patterns.observer;

/**
 * Interface for observers that receive notifications from a {@link VolunteerSubject}.
 * <p>
 * Implementing classes represent volunteers that are notified about shelter events.
 */
public interface VolunteerObserver {
    /**
     * Called when the subject has an event update.
     *
     * @param event a string describing the event that occurred
     */
    void update(String event);
}
