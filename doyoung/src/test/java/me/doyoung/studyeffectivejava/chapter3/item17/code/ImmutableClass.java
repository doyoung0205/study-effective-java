package me.doyoung.studyeffectivejava.chapter3.item17.code;

public class ImmutableClass extends Thread {
    private String value;

    public ImmutableClass(String value) {
        this.value = value;
    }

    @Override
    public void run() {
        value += " world";
        System.out.println(Thread.currentThread().getId() + ":: " + value);
    }

    public String getValue() {
        return value;
    }
}
