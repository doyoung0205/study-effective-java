package me.doyoung.studyeffectivejava.chapter7.item42;

import java.io.Serializable;
import java.util.Comparator;

public class MultipleBounds {
    public static void main(String[] args) {
        Comparator<Number> newComparator = (Comparator<Number> & Serializable) (o1, o2) -> 0;
        System.out.println(newComparator);
    }
}
