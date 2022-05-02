package me.doyoung.studyeffectivejava.chapter7.item44;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiFunctions {
    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(() -> {
            System.out.println("runnable");
        });

        executorService.submit(() -> {
            return "callable";
        });
    }
}
