package me.doyoung.studyeffectivejava.chapter2.item13.code;

public class Parent implements Cloneable {

    @Override
    public Parent clone() {
        return new Parent();
    }
}
