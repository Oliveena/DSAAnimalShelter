package logic;


public class Cat extends Animal {
    private String furLength;
    private boolean isIndoor;

    public Cat(String name, int age, String furLength, boolean isIndoor) {
        super(name, age);
        this.furLength = furLength;
        this.isIndoor = isIndoor;
    }

    @Override
    public String getType() { return "Cat"; }

    @Override
    public String getDetails() {
        return String.format("Cat [ID: %s, Name: %s, Age: %d, Fur Length: %s, Indoor: %s]",
                             id, name, age, furLength, isIndoor ? "Yes" : "No");
    }
}
