package me.doyoung.studyeffectivejava.utils;

public class SpeedCheckUtils {

    private static final String DEFAULT_TASK_NAME = "result";

    private SpeedCheckUtils() {
        throw new AssertionError();
    }

    public static void check(Runnable runnable) {
        check(DEFAULT_TASK_NAME, runnable);
    }

    public static void check(String taskName, Runnable runnable) {
        long st = System.currentTimeMillis();
        runnable.run();
        long et = System.currentTimeMillis();

        System.out.printf("%s :: \'%d\' ms", taskName, (et - st));
    }
}
