package patterns.observer;

public interface VolunteerSubject {
    void registerVolunteer(VolunteerObserver volunteer);
    void removeVolunteer(VolunteerObserver volunteer);
    void notifyVolunteers(String event);
}

