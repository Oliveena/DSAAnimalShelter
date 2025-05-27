package patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class VolunteerManager implements VolunteerSubject {
    private List<VolunteerObserver> volunteers = new ArrayList<>();

    @Override
    public void registerVolunteer(VolunteerObserver volunteer) {
        volunteers.add(volunteer);
    }

    @Override
    public void removeVolunteer(VolunteerObserver volunteer) {
        volunteers.remove(volunteer);
    }

    @Override
    public void notifyVolunteers(String event) {
        for (VolunteerObserver v : volunteers) {
            v.update(event);
        }
    }
}

