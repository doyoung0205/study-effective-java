package me.doyoung.studyeffectivejava.chapter3.item18.event;

public class NumberPrintEvent implements Event {

    private final NumberPrint numberPrint;

    public NumberPrintEvent(NumberPrint numberPrint, NumberPrintListener listener) {
        this.numberPrint = numberPrint;
//        System.out.println("this :: " + this);
        listener.addEvent(this);
    }

    @Override
    public void call() {
        System.out.println("NumberPrintComposition.call");
        printNumber();
    }

    private void printNumber() {
        numberPrint.printNumber();
    }
}
