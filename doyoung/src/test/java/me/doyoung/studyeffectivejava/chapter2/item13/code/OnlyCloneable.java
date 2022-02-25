package me.doyoung.studyeffectivejava.chapter2.item13.code;

public class OnlyCloneable implements Cloneable {
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
