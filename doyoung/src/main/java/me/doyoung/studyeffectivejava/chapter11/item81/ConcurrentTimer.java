package me.doyoung.studyeffectivejava.chapter11.item81;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 코드 81-3 동시 실행 시간을 재는 간단한 프레임워크 (433-434쪽)
public class ConcurrentTimer {
    private ConcurrentTimer() {
    } // 인스턴스 생성 불가

    public static void main(String[] args) throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(1);
        final long result = time(executorService, 1, () -> System.out.println("RUN"));
        System.out.println("result = " + result);
        executorService.shutdown();
    }

    public static long time(Executor executor, int concurrency,
                            Runnable action) throws InterruptedException {
        CountDownLatch ready = new CountDownLatch(concurrency); // 3
        CountDownLatch start = new CountDownLatch(1); // 1
        CountDownLatch done = new CountDownLatch(concurrency); // 3


        for (int i = 0; i < concurrency; i++) {
            executor.execute(() -> {
//                System.out.println(Thread.currentThread().getName() + " :: ready.countDown() 전");
                ready.countDown(); //2. 타이머에게 준비를 마쳤음을 알린다.
                System.out.println(Thread.currentThread().getName() + " :: ready.countDown() 후");
                try {

//                    System.out.println(Thread.currentThread().getName() + " :: start.await() 전");
                    start.await(); // 4. 모든 작업자 스레드가 준비될 때까지 기다린다.
                    System.out.println(Thread.currentThread().getName() + " :: start.await() 후");

//                    System.out.println(Thread.currentThread().getName() + " :: action.run() 전");
                    action.run(); // 5.
                    System.out.println(Thread.currentThread().getName() + " :: action.run() 후");

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(Thread.currentThread().getName() + " :: InterruptedException");
                } finally {
//                    System.out.println(Thread.currentThread().getName() + " :: done.countDown() 전");
                    done.countDown();  // ?? 타이머에게 작업을 마쳤음을 알린다.
                    System.out.println(Thread.currentThread().getName() + " :: done.countDown() 후");
                }
            });
        }

//        System.out.println(Thread.currentThread().getName() + " :: ready.await() 전 ");
        ready.await();     // 1. 모든 작업자가 준비될 때까지 기다린다. ready.countDown() 다 할 떄까지 기다림
        System.out.println(Thread.currentThread().getName() + " :: ready.await() 후");

        long startNanos = System.nanoTime();

//        System.out.println(Thread.currentThread().getName() + " :: start.countDown() 전");
        start.countDown(); // 3. 작업자들을 깨운다.

        System.out.println(Thread.currentThread().getName() + " :: start.countDown() 후"); //??
//        System.out.println(Thread.currentThread().getName() + " :: done.await() 전"); // ??

        done.await();      // ?? 모든 작업자가 일을 끝마치기를 기다린다.

        System.out.println(Thread.currentThread().getName() + " :: done.await() 후"); // 마지막
        return System.nanoTime() - startNanos;
    }
}
