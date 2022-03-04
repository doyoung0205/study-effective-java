package me.doyoung.studyeffectivejava.chapter3.item18.event;

public class NumberPrintEventCounterWithComposition implements Event {

    private int printCount;
    private NumberPrintEvent event;

    public NumberPrintEventCounterWithComposition(NumberPrint numberPrint, NumberPrintListener listener) {
        this.event = new NumberPrintEvent(numberPrint, listener);
    }

    @Override
    public void call() {
        System.out.println("NumberPrintWrapper.call");
        printCount++;
        event.call();
    }

    public int getPrintCount() {
        return printCount;
    }
}
