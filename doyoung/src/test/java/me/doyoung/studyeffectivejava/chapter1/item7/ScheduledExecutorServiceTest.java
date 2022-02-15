package me.doyoung.studyeffectivejava.chapter1.item7;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceTest {

    // chedule()은 일정 시간 뒤에 Job을 실행시키는 메소드입니다.
    @Test
    void schedule() throws InterruptedException {

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        Runnable runnable = () -> System.out.println("Runnable task : " + LocalTime.now());
        int delay = 3;

        // Job을 스케쥴링합니다.
        System.out.println("Scheduled task : " + LocalTime.now());
        executor.schedule(runnable, delay, TimeUnit.SECONDS);

        Thread.sleep(4000);
    }

    // 작업을 일정 시간 간격으로 실행시키는 메소드입니다.
    @Test
    void scheduleAtFixedRate() throws InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        Runnable runnable = () -> {
            System.out.println("++ Repeat task : " + LocalTime.now());
            sleepSec(2);
            System.out.println("-- Repeat task : " + LocalTime.now());
        };
        int initialDelay = 2;
        int delay = 3;

        // Job을 스케쥴링합니다.
        System.out.println("Scheduled task : " + LocalTime.now());
        executor.scheduleAtFixedRate(
                runnable, initialDelay, delay, TimeUnit.SECONDS);

        Thread.sleep(5000);

    }

    // Job이 완료된 후, 일정 시간 뒤에 다시 Job을 실행시키는 메소드입니다.
    @Test
    void scheduleWithFixedDelay() throws InterruptedException {

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        Runnable runnable = () -> {
            System.out.println("++ Repeat task : " + LocalTime.now());
            sleepSec(1);
            System.out.println("-- Repeat task : " + LocalTime.now());
        };
        int initialDelay = 2;
        int delay = 3;

        // Job을 스케쥴링합니다.
        System.out.println("Scheduled task : " + LocalTime.now() );
        executor.scheduleWithFixedDelay(
                runnable, initialDelay , delay, TimeUnit.SECONDS);

        Thread.sleep(8000);
    }

    private static void sleepSec(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
