package me.doyoung.studyeffectivejava.chapter5.item32;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

// 미묘한 힙 오염 발생 (193-194쪽)
public class PickTwo {
    // 코드 32-2 자신의 제네릭 매개변수 배열의 참조를 노출한다. - 안전하지 않다! (193쪽)
    static <T> T[] toArray(T... args) {
        return args;
    }

    static <T> T[] pickTwo(T a, T b, T c) {
        switch (ThreadLocalRandom.current().nextInt(3)) {
            case 0:
                final T[] q = toArray(a, b);
                return q;
            case 1:
                final T[] w = (T[]) toArray(a, b);
                return w;
            case 2:
                final T[] e = (T[]) toArray(a, b);
                return e;
        }
        throw new AssertionError(); // 도달할 수 없다.
    }

    public static void main(String[] args) { // (194쪽)
        String[] attributes = pickTwo("좋은", "빠른", "저렴한");
        System.out.println(Arrays.toString(attributes));
    }

}
