package me.doyoung.studyeffectivejava.chapter1.item2;

import java.util.List;

public class Sandwich {
    private String name; // 필수
    private String breadType; // 필수
    private String fish; // or meat
    private String meat; // or fish
    private String cheese; // 선택
    private List<String> vegetables; // 선택

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
