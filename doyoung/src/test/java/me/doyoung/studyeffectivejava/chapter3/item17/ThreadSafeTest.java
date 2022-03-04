package me.doyoung.studyeffectivejava.chapter3.item17;

import me.doyoung.studyeffectivejava.chapter3.item17.code.ImmutableClass;
import me.doyoung.studyeffectivejava.chapter3.item17.code.MutableClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ThreadSafeTest {

    @DisplayName("가변객체 차례대로 증가한다.")
    @Test
    void mutableClass() {
        StringBuilder hello = new StringBuilder("hello");
        for (int i = 0; i < 4; i++) {
            new MutableClass(hello).run();
        }
    }

    @DisplayName("가변 객체는 매 실행마다 다른 결과가 나온다.")
    @Test
    void mutableClassWithConcurrency() throws InterruptedException {
        final StringBuilder hello = new StringBuilder("hello");
        runWithConcurrency(4, () -> {
            new MutableClass(hello).run();
        });
    }


    @DisplayName("불변 객체는 매 실행마다 같은 결과가 나온다.")
    @Test
    void immutableClass() throws InterruptedException {
        final String hello = "hello";
        final ImmutableClass immutableClass = new ImmutableClass(hello);
        runWithConcurrency(4, () -> {
            immutableClass.run();
        });
    }

    private void runWithConcurrency(int numberOfThreads, Runnable runnable) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            service.submit(() -> {
                runnable.run();
                latch.countDown();
            });
        }
        latch.await();
    }
}


