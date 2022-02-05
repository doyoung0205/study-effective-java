package me.doyoung.studyeffectivejava.chapter1.item2;

import java.util.List;

public class Sandwich {
    private String name;
    private String breadType;
    private String fish;
    private String cheese;
    private String meat;
    private List<String> vegetables;

    private Sandwich() {
    }

    public Sandwich(SandwichStepBuilder builder) {
        this.name = builder.getName();
        this.breadType = builder.getBreadType();
        this.fish = builder.getFish();
        this.cheese = builder.getCheese();
        this.meat = builder.getMeat();
        this.vegetables = builder.getVegetables();
    }

    public Sandwich(String name, String breadType, String fish, String cheese, String meat, List<String> vegetables) {
        this.name = name;
        this.breadType = breadType;
        this.fish = fish;
        this.cheese = cheese;
        this.meat = meat;
        this.vegetables = vegetables;
    }

}
