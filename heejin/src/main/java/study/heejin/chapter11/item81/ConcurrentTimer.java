package study.heejin.chapter11.item81;

import java.util.concurrent.*;

public class ConcurrentTimer {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        long nanoTime = time(executorService, 3, () -> {
            System.out.println("--- Task ---");
        });
        long second = TimeUnit.SECONDS.convert(nanoTime, TimeUnit.NANOSECONDS);
        System.out.println("#### nanoTime = " + nanoTime + "나노초");
        System.out.println("#### second = " + second + "초");
        executorService.shutdown();
    }

    private ConcurrentTimer() { } // 인스턴스 생성 불가

    public static long time(Executor executor, int concurrency, Runnable action) throws InterruptedException {
        CountDownLatch ready = new CountDownLatch(concurrency);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(concurrency);

        for (int i = 0; i < concurrency; i++) {
            executor.execute(() -> {
                ready.countDown();  // 타이머에게 준비를 마쳤음을 알린다.
                System.out.println("   --> " + Thread.currentThread().getName() + " | ready countDown 후");

                try {
                    start.await();  // 모든 작업자 스레드가 준비될 때까지 기다린다.
                    System.out.println("   --> " + Thread.currentThread().getName() + " | start await 후");
                    action.run();

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();

                } finally {
                    done.countDown();  // 타이머에게 작업을 마쳤음을 알린다.
                    System.out.println("   --> " + Thread.currentThread().getName() + " | done countDown 후");
                }
            });
        }

        ready.await();      // 모든 작업자가 준비될 때까지 기다린다.
        System.out.println("==> " + Thread.currentThread().getName() + " | ready await 후");

        long startNanos = System.nanoTime();
        System.out.println("==> @ " + Thread.currentThread().getName() + " | start countDown 전");
        start.countDown();  // 작업자들을 깨운다.
        System.out.println("==> @ " + Thread.currentThread().getName() + " | start countDown 후");

        done.await();       // 모든 작업자가 일을 끝마치기를 기다린다.
        System.out.println("==> " + Thread.currentThread().getName() + " | done await 후");

        return System.nanoTime() - startNanos;
    }
}
