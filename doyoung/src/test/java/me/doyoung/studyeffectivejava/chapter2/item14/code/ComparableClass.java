package me.doyoung.studyeffectivejava.chapter2.item14.code;

import java.util.Comparator;

import static java.util.Comparator.comparingInt;

public class ComparableClass implements Comparable<ComparableClass> {

    private final int id;

    public ComparableClass(int id) {
        this.id = id;
    }


    public static final Comparator<ComparableClass> COMPARATOR =
            comparingInt(cc -> cc.id);


    @Override
    public int compareTo(ComparableClass target) {
        return COMPARATOR.reversed().compare(this, target);
    }

    @Override
    public String toString() {
        return "[" + id + ']';
    }

    public int getId() {
        return id;
    }
}
