package me.doyoung.studyeffectivejava.chapter1.item6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static me.doyoung.studyeffectivejava.chapter1.item6.RomanNumerals.isRomanNumeralFast;
import static me.doyoung.studyeffectivejava.chapter1.item6.RomanNumerals.isRomanNumeralSlow;

class RomanNumeralsTest {

    @Test
    @DisplayName("매번 Pattern 을 생성하여 성능이 좋지 않은 예시")
    void isRomanNumeralSlowTest() {
        int numReps = 100;
        boolean b = false;

        long start = System.nanoTime();
        for (int j = 0; j < numReps; j++) {
            b ^= isRomanNumeralSlow("MCMLXXVI");
        }
        long end = System.nanoTime();
        System.out.println(((end - start) / (1_000. * numReps)) + " μs.");

        // VM이 최적화하지 못하게 막는 코드
        if (!b)
            System.out.println();

    }

    @Test
    @DisplayName("Pattern 을 정적 초기화 하여 성능이 좋은 예시")
    void isRomanNumeralFastTest() {

        int numReps = 100;
        boolean b = false;

        long start = System.nanoTime();
        for (int j = 0; j < numReps; j++) {
            b ^= isRomanNumeralFast("MCMLXXVI");
        }
        long end = System.nanoTime();
        System.out.println(((end - start) / (1_000. * numReps)) + " μs.");

        // VM이 최적화하지 못하게 막는 코드
        if (!b)
            System.out.println();
    }
}
