package domain;

import patterns.observer.VolunteerObserver;

public class Volunteer implements VolunteerObserver {
    private String name;

    public Volunteer(String name) {
        this.name = name;
    }

    @Override
    public void update(String event) {
        System.out.println("Volunteer " + name + " notified: " + event);
    }
}

