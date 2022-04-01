package me.doyoung.studyeffectivejava.chapter4;

import java.util.Arrays;
import java.util.List;

public class HeapPollution {
    public static void main(String[] args) {
        List<String> strings1 = List.of("첫 요소");
        List<String> strings2 = List.of("첫 요소");
        doSomething(strings1, strings2);
    }

    private static void doSomething(List<String>... stringLists) {
        List<Integer> intList = List.of(42);
        Object[] objects = stringLists;
        objects[0] = intList; // 힙 오염 발생
        String s = stringLists[0].get(0); // ClassCastException
    }
}
