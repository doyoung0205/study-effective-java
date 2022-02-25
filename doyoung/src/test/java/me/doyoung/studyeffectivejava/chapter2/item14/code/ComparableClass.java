package me.doyoung.studyeffectivejava.chapter2.item14.code;

public class ComparableClass implements Comparable<ComparableClass> {

    private int id;

    public ComparableClass(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(ComparableClass o) {
        return Integer.compare(id, o.id);
    }

    @Override
    public String toString() {
        return "[" + id + ']';
    }
}
