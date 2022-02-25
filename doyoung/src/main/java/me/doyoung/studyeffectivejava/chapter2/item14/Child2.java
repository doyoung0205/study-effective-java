package me.doyoung.studyeffectivejava.chapter2.item14;

public class Child2 extends Parent {

    PhoneNumberWithCompare phoneNumber;

    @Override
    public int compareTo(Parent o) {
        // 매개변수에서 phoneNumber 를 꺼내서 비교할 수 없음..
//        comparing((Parent p) -> p)
//                .thenComparingInt(p -> p.phoneNumber)
//                .compare(this, o);
        return 0;
    }
}
