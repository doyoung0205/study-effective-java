package me.doyoung.studyeffectivejava.chapter3.item18.event;

import java.util.ArrayList;
import java.util.List;

public class NumberPrintListener {
    private final List<Event> eventGroup = new ArrayList<>();

    public void addEvent(Event event) {
//        System.out.println("NumberPrintListener.addEvent");
//        System.out.println(event.getClass());
        eventGroup.add(event);
    }

    public void publish() {
        System.out.println("[EVENT PUBLISH START]");
        for (Event event : eventGroup) {
            event.call();
        }
        System.out.println("[EVENT PUBLISH END]");
    }

    public int getEventSize() {
        return this.eventGroup.size();
    }
}
