package study.heejin.chapter5.item31;

import java.time.LocalTime;
import java.util.concurrent.*;

public class ScheduledThreadPoolExecutor {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        scheduleRunnable();
        scheduleCallable();
        scheduleAtFixedRate();
        scheduleWithFixedDelay();
    }

    public static void scheduleRunnable() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        Runnable runnable = () -> System.out.println("Runnable task : " + LocalTime.now());
        int delay = 3;

        // Job을 스케쥴링합니다.
        System.out.println("Scheduled task : " + LocalTime.now() );
        executor.schedule(runnable, delay, TimeUnit.SECONDS);
    }

    public static void scheduleCallable() throws ExecutionException, InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        Callable<String> callable =  () -> "Callable task : " + LocalTime.now();
        int delay = 3;

        // Job을 스케쥴링합니다.
        System.out.println("Scheduled task : " + LocalTime.now() );
        ScheduledFuture<String> future =
                executor.schedule(callable, delay, TimeUnit.SECONDS);

        // 결과가 리턴될 때 까지 기다립니다.
        String result = future.get();
        System.out.println(result);
    }

    public static void scheduleAtFixedRate() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        Runnable runnable = () -> {
            System.out.println("++ Repeat task : " + LocalTime.now());
            sleepSec(3);
            System.out.println("-- Repeat task : " + LocalTime.now());
        };
        int initialDelay = 2;  // Job이 처음 실행될 때 기다리는 시간
        int delay = 3;         // Job이 실행되는 시간 간격

        System.out.println("Scheduled task : " + LocalTime.now() );
        executor.scheduleAtFixedRate(
                runnable, initialDelay, delay, TimeUnit.SECONDS);
    }

    public static void scheduleWithFixedDelay() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        Runnable runnable = () -> {
            System.out.println("++ Repeat task : " + LocalTime.now());
            sleepSec(3);
            System.out.println("-- Repeat task : " + LocalTime.now());
        };
        int initialDelay = 2;
        int delay = 3;

        // Job을 스케쥴링합니다.
        System.out.println("Scheduled task : " + LocalTime.now() );
        executor.scheduleWithFixedDelay(
                runnable, initialDelay , delay, TimeUnit.SECONDS);
    }

    private static void sleepSec(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
