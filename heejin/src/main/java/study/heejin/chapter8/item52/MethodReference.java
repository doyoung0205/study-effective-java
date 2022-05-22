package study.heejin.chapter8.item52;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MethodReference {
    public static void main(String[] args) {
        // 1. Thead의 생성자 호출
        new Thread(System.out::println).start();

        // 2. ExecutorService의 submit 메서드 호출
        ExecutorService exec = Executors.newCachedThreadPool();
//        exec.submit(System.out::println); // 컴파일 오류!

        Runnable runnable = System.out::println;
        exec.submit(runnable); // 타입을 지정해서 매개변수로 넣어주면 컴파일 오류가 나지 않음
    }
}
