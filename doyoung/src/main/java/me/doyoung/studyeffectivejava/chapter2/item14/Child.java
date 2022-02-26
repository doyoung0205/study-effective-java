package me.doyoung.studyeffectivejava.chapter2.item14;

import static java.util.Comparator.comparing;

public class Child implements Comparable<Child> {
    Parent parent;
    PhoneNumberWithCompare phoneNumber;

    @Override
    public int compareTo(Child o) {
        return comparing((Child p) -> p.parent)
                .thenComparing(p -> p.phoneNumber)
                .compare(this, o);
    }
}
