package logic;

import java.util.UUID;

public abstract class Animal implements Adoptable {
    protected String id;
    protected String name;
    protected int age;

    public Animal(String name, int age) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.age = age;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }

    public abstract String getType();
    public abstract String getDetails();
}
