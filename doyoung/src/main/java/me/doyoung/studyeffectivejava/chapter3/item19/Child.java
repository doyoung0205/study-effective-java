package me.doyoung.studyeffectivejava.chapter3.item19;

public class Child extends Parent{
    @Override
    public Long getId() {
        return 9L;
    }

    @Override
    protected Long calc() {
        return super.calc();
    }
}
