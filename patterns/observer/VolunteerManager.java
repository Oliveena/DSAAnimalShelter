package patterns.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import patterns.factories.LogFactory;

/**
 * Manages a list of volunteers implementing the {@link VolunteerObserver} interface.
 * <p>
 * This class acts as the {@code Subject} in the Observer design pattern,
 * maintaining a list of observers (volunteers) and notifying them of events.
 * It supports registering, removing, and notifying volunteers.
 */
public class VolunteerManager implements VolunteerSubject {
    private static final Logger logger = LogFactory.getLogger(VolunteerManager.class);

    /** The list of registered volunteers to be notified of events. */
    private List<VolunteerObserver> volunteers = new ArrayList<>();

    /**
     * Registers a new volunteer observer to receive event notifications.
     *
     * @param volunteer the volunteer observer to register; must not be null
     */
    @Override
    public void registerVolunteer(VolunteerObserver volunteer) {
        volunteers.add(volunteer);
        logger.fine("Registered new volunteer: " + volunteer);
    }

    /**
     * Removes a previously registered volunteer observer.
     *
     * @param volunteer the volunteer observer to remove
     */
    @Override
    public void removeVolunteer(VolunteerObserver volunteer) {
        volunteers.remove(volunteer);
        logger.fine("Removed volunteer: " + volunteer);
    }

    /**
     * Notifies all registered volunteers of the specified event.
     *
     * @param event a string describing the event to notify about
     */
    @Override
    public void notifyVolunteers(String event) {
        logger.info("Notifying " + volunteers.size() + " volunteer(s) of event: " + event);
        for (VolunteerObserver v : volunteers) {
            v.update(event);
        }
    }
}

