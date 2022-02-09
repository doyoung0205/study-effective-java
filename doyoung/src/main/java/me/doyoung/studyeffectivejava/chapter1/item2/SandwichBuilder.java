package me.doyoung.studyeffectivejava.chapter1.item2;

import java.util.List;

public class SandwichBuilder {

    private SandwichBuilder() {
    }

    private String name;
    private String breadType;
    private String fish;
    private String cheese;
    private String meat;
    private List<String> vegetables;

    public static SandwichBuilder builder() {
        return new SandwichBuilder();
    }

    public SandwichBuilder sandwichCalled(String name) {
        this.name = name;
        return this;
    }

    public SandwichBuilder breadType(String breadType) {
        this.breadType = breadType;
        return this;
    }

    public SandwichBuilder withFish(String fish) {
        this.fish = fish;
        return this;
    }

    public SandwichBuilder withCheese(String cheese) {
        this.cheese = cheese;
        return this;
    }

    public SandwichBuilder withMeat(String meat) {
        this.meat = meat;
        return this;
    }

    public SandwichBuilder withVegetable(String vegetable) {
        vegetables.add(vegetable);
        return this;
    }

    public Sandwich build() {
        return new Sandwich(this.name, this.breadType, this.fish, this.cheese, this.meat, this.vegetables);
    }

}
