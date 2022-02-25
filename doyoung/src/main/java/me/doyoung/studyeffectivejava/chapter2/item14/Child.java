package me.doyoung.studyeffectivejava.chapter2.item14;

public class Child implements Comparable<Child> {
    Parent parent;
    PhoneNumberWithCompare phoneNumber;

    @Override
    public int compareTo(Child o) {
        return 0;
    }
}
