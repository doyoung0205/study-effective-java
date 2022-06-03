package study.heejin.chapter9.item62;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ThreadLocalTest {

    @Test
    void theadLocal() {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("first thread");

        Thread thread1 = new Thread(() -> {
            threadLocal.set("테스트 스레드 1");
            System.out.println("1) " + threadLocal.get());
        });
        thread1.start();
        Thread thread2 = new Thread(() -> {
            threadLocal.set("테스트 스레드 2");
            System.out.println("2) " + threadLocal.get());
        });
        thread2.start();

        assertThat(threadLocal.get()).isEqualTo("first thread");
    }
}