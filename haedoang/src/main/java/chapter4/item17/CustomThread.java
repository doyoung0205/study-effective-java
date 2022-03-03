package chapter4.item17;

import java.util.stream.IntStream;

/**
 * author : haedoang
 * date : 2022/03/03
 * description :
 */
public class CustomThread implements Runnable {
    @Override
    public void run() {
        IntStream.range(0, 1000)
                .forEach(CustomThread::print);
    }

    private static void print(int i) {
        System.out.printf("%s value: %d\n", Thread.currentThread().getName(), i);
    }
}
