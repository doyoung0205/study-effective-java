package study.heejin.chapter9.item62;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class ThreadLocalStringTest {

    @Test
    void theadLocal() {
        Thread thread1 = new Thread(() -> {
            ThreadLocalString.set("thread1", "테스트 스레드 1");
            System.out.println("1) " + ThreadLocalString.get("thread1"));
        });
        thread1.start();
        Thread thread2 = new Thread(() -> {
            ThreadLocalString.set("thread2", "테스트 스레드 2");
            System.out.println("2) " + ThreadLocalString.get("thread2"));
        });
        thread2.start();
        Thread thread3 = new Thread(() -> {
            ThreadLocalString.set("thread1", "테스트 스레드 3");
            System.out.println("3) " + ThreadLocalString.get("thread1"));
        });
        thread3.start();

        assertThat(ThreadLocalString.get("thread1")).isEqualTo("테스트 스레드 3");
        assertThat(ThreadLocalString.get("thread2")).isEqualTo("테스트 스레드 2");
    }
}