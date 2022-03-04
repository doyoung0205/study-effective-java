package me.doyoung.studyeffectivejava.chapter3.item17.code;

public class MutableClass extends Thread {
    private final StringBuilder value;

    public MutableClass(StringBuilder value) {
        this.value = value;
    }

    @Override
    public void run() {
        value.append(" world");
        System.out.println(Thread.currentThread().getId() + ":: " + new StringBuilder(value));
    }

    public StringBuilder getValue() {
        return new StringBuilder(value);
    }
}


