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


        System.out.println("sandwich = " + sandwich);


        SandwichBuilder.builder()
                .breadType("baguette")
                .build();

    }
}
