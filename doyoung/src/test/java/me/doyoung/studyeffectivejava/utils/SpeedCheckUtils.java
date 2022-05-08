package me.doyoung.studyeffectivejava.utils;

import org.junit.platform.commons.util.ReflectionUtils;

public class SpeedCheckUtils {

    private static final String DEFAULT_TASK_NAME = "result";

    private SpeedCheckUtils() {
        throw new AssertionError();
    }

    public static void speedCheck(Runnable runnable) {
        speedCheck(DEFAULT_TASK_NAME, runnable);
    }

    public static void speedCheck(String taskName, Runnable runnable) {
        long st = System.currentTimeMillis();
        runnable.run();
        long et = System.currentTimeMillis();
        System.out.printf("%s :: '%d' ms\n", taskName, (et - st));
    }
}
