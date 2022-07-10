package chapter11;

import chapter11.item78.NumberGenerator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/07/10
 * description :
 */
public class Item78Test {

    @Disabled
    @RepeatedTest(50)
    @DisplayName("안전 실패를 일으키는 증가 연산자 테스트")
    public void numberGeneratorTest() throws InterruptedException {
        // given
        final ArrayList<Integer> numbers = new ArrayList<>();
        NumberGenerator.init();

        // when
        final ExecutorService executor = Executors.newFixedThreadPool(1_000);
        final CyclicBarrier barrier = new CyclicBarrier(1_000);

        // then
        for (int i = 0; i < 1_000; i++) {
            executor.submit(() -> {
                barrier.await();
                numbers.add(NumberGenerator.generateSerialNumber());
                return null;
            });
        }

        executor.shutdown();
        executor.awaitTermination(2, TimeUnit.SECONDS);

        // then
        System.out.println(numbers);
        assertThat(numbers.contains(0)).isTrue();
        assertThat(numbers.contains(999)).isTrue()
                .as("synchronized 로 해결 할 수 있다");
    }

    @RepeatedTest(50)
    @DisplayName("스레드 안정성 numberGenerator 테스트")
    public void numberGeneratorAtomicLongTest() throws InterruptedException {
        // given
        final ArrayList<Long> numbers = new ArrayList<>();
        NumberGenerator.init();

        // when
        final ExecutorService executor = Executors.newFixedThreadPool(1_000);
        final CyclicBarrier barrier = new CyclicBarrier(1_000);

        // then
        for (int i = 0; i < 1_000; i++) {
            executor.submit(() -> {
                barrier.await();
                numbers.add(NumberGenerator.generateSerialNum());
                return null;
            });
        }

        executor.shutdown();
        executor.awaitTermination(2, TimeUnit.SECONDS);

        // then
        System.out.println(numbers);
        assertThat(numbers.contains(0L)).isTrue();
        assertThat(numbers.contains(999L)).isTrue();
    }
}
