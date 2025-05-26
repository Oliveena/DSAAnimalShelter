package patterns.observer;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Logger;
import patterns.factories.LogFactory;

public class VolunteerManager implements VolunteerSubject {
    private static final Logger logger = LogFactory.getLogger(VolunteerManager.class);

    private List<VolunteerObserver> volunteers = new ArrayList<>();

    @Override
    public void registerVolunteer(VolunteerObserver volunteer) {
        volunteers.add(volunteer);
        logger.fine("Registered new volunteer: " + volunteer);
    }

    @Override
    public void removeVolunteer(VolunteerObserver volunteer) {

        volunteers.remove(volunteer);
        logger.fine("Removed volunteer: " + volunteer);
    }

    @Override
    public void notifyVolunteers(String event) {
        logger.info("Notifying " + volunteers.size() + " volunteer(s) of event: " + event);
        for (VolunteerObserver v : volunteers) {
            v.update(event);
        }
    }
}
