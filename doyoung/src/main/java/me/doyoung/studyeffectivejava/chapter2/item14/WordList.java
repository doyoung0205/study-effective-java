package me.doyoung.studyeffectivejava.chapter2.item14;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

// Comparable 구현 시의 이점 (87쪽)
public class WordList {
    public static void main(String[] args) {
        Set<String> s = new TreeSet<>();
        Collections.addAll(s, args);
        System.out.println(s);
    }
}
