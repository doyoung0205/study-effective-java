package me.doyoung.studyeffectivejava.chapter1.item2;

import lombok.Builder;

import java.util.List;

public class SandwichWithBuilderAnnotation {
    private String name;
    private String breadType;
    private String fish;
    private String cheese;
    private String meat;
    private List<String> vegetables;

    @Builder
    public SandwichWithBuilderAnnotation(String name, String breadType, String fish, String cheese, String meat, List<String> vegetables) {
        this.name = name;
        this.breadType = breadType;
        this.fish = fish;
        this.cheese = cheese;
        this.meat = meat;
        this.vegetables = vegetables;
    }
}
