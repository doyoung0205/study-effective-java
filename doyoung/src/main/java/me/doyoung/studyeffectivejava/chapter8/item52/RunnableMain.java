package me.doyoung.studyeffectivejava.chapter8.item52;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnableMain {

    public static void main(String[] args) {
        final Runnable runnable = System.out::println;
        new Thread(runnable).start();
        ExecutorService exec = Executors.newCachedThreadPool();


        exec.submit((Runnable) System.out::println); // 양쪽 다중 정의가 있어서 오류가남
    }
}
