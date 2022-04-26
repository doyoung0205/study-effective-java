package study.heejin.chapter5.item32;

import java.util.concurrent.ThreadLocalRandom;

public class PickTwo {

    public static <T> T[] toArray(T... args) {
        return args;
    }

    public static <T> T[] pickTwo(T a, T b, T c) {
        switch (ThreadLocalRandom.current().nextInt(3)) {
            case 0: return toArray(a, b);
            case 1: return toArray(a, c);
            case 2: return toArray(b, c);
        }
        throw new AssertionError(); // 도달할 수 없다.
    }

    public static void main(String[] args) {
        // pickTwo의 반환 타입이 Object[] 이기 때문에 String[]로 형변환 시 ClassCastException 발생
        String[] attributes = pickTwo("좋은", "빠른", "저렴한");
    }
}
