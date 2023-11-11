package org.example.Data;

public class Toy {
    private int id;
    private String name;
    private int numberOfToys;
    private int chance;

        public Toy(int id, String name, int numberOfToys, int chance) {
        this.id = id;
        this.name = name;
        this.numberOfToys = numberOfToys;
        this.chance = chance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfToys() {
        return numberOfToys;
    }

    public int getChance() {
        return chance;
    }

    public void setChance(int chance) {
        System.out.println("Частота выпадения игрушки " + this.name + " равна " + chance);
        this.chance = chance;
    }
}
