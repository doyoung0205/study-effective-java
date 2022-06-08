package me.doyoung.studyeffectivejava.chapter9.item64;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Long> list = List.of(1L);
        test(list);
        System.out.println("list = " + list);
    }

    public static void test(
            final List<Long> list
    ) {
//        list = new ArrayList();
//        list.add(3L);
        return;
    }

}
