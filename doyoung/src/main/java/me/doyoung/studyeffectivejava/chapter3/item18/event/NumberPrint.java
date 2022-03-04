package me.doyoung.studyeffectivejava.chapter3.item18.event;

public class NumberPrint {
    private final int value;

    public NumberPrint(int value) {
        this.value = value;
    }

    public void printNumber() {
        System.out.println("[Number] value = " + value);
    }
}
