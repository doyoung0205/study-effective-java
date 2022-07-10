package me.doyoung.studyeffectivejava.chapter11.item82;

import java.util.concurrent.CompletableFuture;

public class TestLock {

    public static void main(String[] args) throws InterruptedException {
        final Foo foo = new Foo();

        // 쓰레드 A 먼저
        final Runnable runnable = () -> {
            System.out.println("스레드 1");
            synchronized (foo.lock) { // lock 변수를 읽고 있는 상태
                try {
                    Thread.sleep(10000);
                    System.out.println("냐옹");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        // 스레드 B 2 여러명
        final Runnable runnable2 = () -> {
//            foo.foo();
            synchronized (foo.lock) {
                System.out.println(foo.lock);
            }
        };

        final CompletableFuture<Runnable> future1 = CompletableFuture.supplyAsync(() -> {
            runnable.run();
            return null;
        });
        final CompletableFuture<Runnable> future2 = CompletableFuture.supplyAsync(() -> {
            runnable2.run();
            return null;
        });
        final CompletableFuture<Runnable> future3 = CompletableFuture.supplyAsync(() -> {
            runnable2.run();
            return null;
        });
        CompletableFuture.allOf(future1, future2, future3).join();
    }

    public static class Foo {
        public final Object lock = new Object(); // 아래 비공개 락 객체

        public void foo() {
            System.out.println("스레드 2");
            synchronized (lock) { // lock 필드 접근을 동기화
                System.out.println("foo");
            }
        }

        public void foo2() {
            synchronized (lock) {
                System.out.println("foo2");
            }
        }

    }
}
