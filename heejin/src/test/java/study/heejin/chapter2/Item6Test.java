package study.heejin.chapter2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.heejin.chapter2.item6.AutoBoxing;

import java.util.concurrent.TimeUnit;

class Item6Test {

    @Test
    @DisplayName("오토박싱 성능 테스트")
    void autoBoxing() throws InterruptedException {
        long start = System.currentTimeMillis();

        long autoBoxingSum = AutoBoxing.autoBoxingSum();
        System.out.println("autoBoxingSum took " + (System.currentTimeMillis() - start)  + " ms");

        TimeUnit.SECONDS.sleep(1);

        start = System.currentTimeMillis();
        long sum = AutoBoxing.sum();
        System.out.println("sum took " + (System.currentTimeMillis() - start) + " ms");
    }
}
