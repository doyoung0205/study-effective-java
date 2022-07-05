package me.doyoung.studyeffectivejava.chapter11.item81;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

// ConcurrentMap으로 구현한 동시성 정규화 맵
public class Intern {
    // 코드 81-1 ConcurrentMap으로 구현한 동시성 정규화 맵 - 최적은 아니다. (432쪽)
    private static final ConcurrentMap<String, String> map =
            new ConcurrentHashMap<>();

//    public static String intern(String s) {
//        String previousValue = map.putIfAbsent(s, s);
//        return previousValue == null ? s : previousValue;
//    }

    // 코드 81-2 ConcurrentMap으로 구현한 동시성 정규화 맵 - 더 빠르다! (432쪽)
    public static String intern(String s) {
        String result = map.get(s);
        if (result == null) {
            result = map.putIfAbsent(s, s);
            if (result == null)
                result = s;
        }
        return result;
    }
}
