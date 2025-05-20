package logic;

public class Bird extends Animal {
    private String breed;
    private boolean canFly;

    public Bird (String name, int age, String breed, boolean isTrained) {
        super(name, age);
        this.breed = breed;
        this.canFly= canFly;
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public int getAge() {
        return super.getAge();
    }

    @Override
    public String getType() {
        return "";
    }

    @Override
    public String getDetails() {
        return "";
    }

    @Override
    public String toString() {
        return "Bird{" +
                "breed='" + breed + '\'' +
                ", canFly=" + canFly +
                '}';
    }
}
