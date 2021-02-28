package ua.knu.csc.entity;

public class Monk {
    private String name;
    private int strength;

    private String monastery;

    public Monk(String name, int strength, String monastery) {
        this.name = name;
        this.strength = strength;

        this.monastery = monastery;
    }

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }

    public String getMonastery() {
        return monastery;
    }

    @Override
    public String toString() {
        return "[name: " + name + ", strength: " + strength + ", monastery: " + monastery + "]";
    }
}
