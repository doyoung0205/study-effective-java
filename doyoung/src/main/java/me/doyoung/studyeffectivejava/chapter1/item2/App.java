package me.doyoung.studyeffectivejava.chapter1.item2;

public class App {
    public static void main(String[] args) {
        final Sandwich sandwich = SandwichStepBuilder.builder()
                .sandwichCalled("subway sandwich")
                .breadType("baguette")
                .fish("sole")
                .addVegetable("tomato")
                .addVegetable("lettece")
                .noMoreVegetablesPlease()
                .build();

        SandwichStepBuilder.builder()
                .sandwichCalled("subway sandwich")
                .breadType("")
                .meat("")
                .withCheese("모짜렐라")
                .noVegetablesPlease()
                .build();

        System.out.println("sandwich = " + sandwich);

        final Sandwich sandwichWithBuilder = SandwichBuilder.builder()
                .breadType("ㅁㄴㅇ")
                .withCheese("모짜렐라")
                .withMeat("")
                .build();

        SandwichWithBuilderAnnotation.builder();
    }
}
