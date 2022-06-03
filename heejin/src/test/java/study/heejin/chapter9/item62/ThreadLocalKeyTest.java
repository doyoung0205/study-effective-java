package study.heejin.chapter9.item62;

import org.junit.jupiter.api.Test;
import study.heejin.chapter9.item62.ThreadLocalKey.Key;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ThreadLocalKeyTest {

    @Test
    void theadLocal() throws InterruptedException {
        Key key1 = new Key();
        Key key2 = new Key();

        Thread thread1 = new Thread(() -> {
            ThreadLocalKey.set(key1, "테스트 스레드 1");
            System.out.println("1) " + ThreadLocalKey.get(key1));
        });
        thread1.start();
        Thread thread2 = new Thread(() -> {
            ThreadLocalKey.set(key2, "테스트 스레드 2");
            System.out.println("2) " + ThreadLocalKey.get(key2));
        });
        thread2.start();

        Thread.sleep(3);

        Thread thread3 = new Thread(() -> {
            ThreadLocalKey.set(key1, "테스트 스레드 3");
            System.out.println("3) " + ThreadLocalKey.get(key1));
        });
        thread3.start();

        assertThat(ThreadLocalKey.get(key1)).isEqualTo("테스트 스레드 1");
        assertThat(ThreadLocalKey.get(key2)).isEqualTo("테스트 스레드 2");
    }
}