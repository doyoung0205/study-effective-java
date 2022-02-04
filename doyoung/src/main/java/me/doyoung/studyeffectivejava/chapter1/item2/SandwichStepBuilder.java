package me.doyoung.studyeffectivejava.chapter1.item2;

import java.util.ArrayList;
import java.util.List;

public class SandwichStepBuilder {
    private String name;
    private String breadType;
    private String fish;
    private String cheese;
    private String meat;
    private List<String> vegetables;

    public SandwichStepBuilder(SandwichStepBuildSteps steps) {
        this.name = steps.name;
        this.breadType = steps.breadType;
        this.fish = steps.fish;
        this.cheese = steps.cheese;
        this.meat = steps.meat;
        this.vegetables = steps.vegetables;
    }

    private SandwichStepBuilder() {

    }

    public static FirstNameStep builder() {
        return new SandwichStepBuildSteps();
    }

    /**
     * First Builder Step in charge of the Panino name.
     * Next Step available : BreadTypeStep
     */
    public interface FirstNameStep {
        BreadTypeStep sandwichCalled(String name);
    }

    /**
     * This step is in charge of the BreadType.
     * Next Step available : MainFillingStep
     */
    public interface BreadTypeStep {
        MainFillingStep breadType(String breadType);
    }

    /**
     * This step is in charge of setting the main filling (meat or fish).
     * Meat choice : Next Step available : CheeseStep
     * Fish choice : Next Step available : VegetableStep
     */
    public interface MainFillingStep {
        CheeseStep meat(String meat);

        VegetableStep fish(String fish);
    }

    /**
     * This step is in charge of the cheese.
     * Next Step available : VegetableStep
     */
    public interface CheeseStep {
        VegetableStep noCheesePlease();

        VegetableStep withCheese(String cheese);
    }

    /**
     * This step is in charge of vegetables.
     * Next Step available : BuildStep
     */
    public interface VegetableStep {
        BuildStep noMoreVegetablesPlease();

        BuildStep noVegetablesPlease();

        VegetableStep addVegetable(String vegetable);
    }

    /**
     * This is the final step in charge of building the Panino Object.
     * Validation should be here.
     */
    public interface BuildStep {
        Sandwich build();
    }

    private static class SandwichStepBuildSteps implements FirstNameStep, BreadTypeStep, MainFillingStep, CheeseStep, VegetableStep, BuildStep {

        private String name;
        private String breadType;
        private String meat;
        private String fish;
        private String cheese;
        private final List<String> vegetables = new ArrayList<>();

        @Override
        public BreadTypeStep sandwichCalled(String name) {
            this.name = name;
            return this;
        }

        @Override
        public MainFillingStep breadType(String breadType) {
            this.breadType = breadType;
            return this;
        }

        @Override
        public CheeseStep meat(String meat) {
            this.meat = meat;
            return this;
        }

        @Override
        public VegetableStep fish(String fish) {
            this.fish = fish;
            return this;
        }

        @Override
        public BuildStep noMoreVegetablesPlease() {
            return this;
        }

        @Override
        public BuildStep noVegetablesPlease() {
            return this;
        }

        @Override
        public VegetableStep addVegetable(String vegetable) {
            this.vegetables.add(vegetable);
            return this;
        }

        @Override
        public VegetableStep noCheesePlease() {
            return this;
        }

        @Override
        public VegetableStep withCheese(String cheese) {
            this.cheese = cheese;
            return this;
        }

        @Override
        public Sandwich build() {
            return new Sandwich(new SandwichStepBuilder(this));
        }
    }

    public String getName() {
        return name;
    }

    public String getBreadType() {
        return breadType;
    }

    public String getFish() {
        return fish;
    }

    public String getCheese() {
        return cheese;
    }

    public String getMeat() {
        return meat;
    }

    public List<String> getVegetables() {
        return vegetables;
    }
}
