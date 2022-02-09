package me.doyoung.studyeffectivejava.chapter1.item6;

import java.util.HashMap;
import java.util.Map;

// 코드 6-3 끔찍이 느리다! 객체가 만들어지는 위치를 찾았는가? (34쪽)
public class Sum {
    public static long sumWithAutoBoxing() {
        Long sum = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++)
            sum += i;
        return sum;
    }
    public static long sumWithPrimitive() {
        long sum = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++)
            sum += i;
        return sum;
    }
}
