package study.heejin.chapter9.item61;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class AutoBoxingSpeedTest {

    @Test
    @DisplayName("박싱과 언박싱이 있는 연산")
    void autoBoxing_sum() {
        long st = System.currentTimeMillis();

        Long sum = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE;  i++) {
            sum += 1;
        }
        System.out.println("autoBoxing sum = " + sum);

        long et = System.currentTimeMillis();
        System.out.println((et - st) + " ms"); // 3172 ms
    }

    @Test
    @DisplayName("박싱과 언박싱이 없는 연산")
    void sum() {
        long st = System.currentTimeMillis();

        long sum = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE;  i++) {
            sum += 1;
        }
        System.out.println("sum = " + sum);

        long et = System.currentTimeMillis();
        System.out.println((et - st) + " ms"); // 707 ms
    }
}