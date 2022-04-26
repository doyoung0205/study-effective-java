package study.heejin.chapter5.item32;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SafePickTwo {

    public static <T> List<T> pickTwo(T a, T b, T c) {
        switch (ThreadLocalRandom.current().nextInt(3)) {
            case 0: return List.of(a, b);
            case 1: return List.of(a, c);
            case 2: return List.of(b, c);
        }
        throw new AssertionError();
    }

    public static void main(String[] args) {
        List<String> attributes = pickTwo("좋은", "빠른", "저렴한");
        System.out.println("attributes = " + attributes);
    }
}
