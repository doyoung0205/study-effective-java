package me.doyoung.studyeffectivejava.chapter3.item18.event;

public class NumberPrintEventCounterWithInheritance extends NumberPrintEvent {

    private int printCount;

    public NumberPrintEventCounterWithInheritance(NumberPrint numberPrint, NumberPrintListener listener) {
        super(numberPrint, listener);
    }

    @Override
    public void call() {
        System.out.println("NumberPrintWrapper.call");
        printCount++;
        super.call();
    }

    public int getPrintCount() {
        return printCount;
    }
}
