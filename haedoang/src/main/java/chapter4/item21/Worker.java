package chapter4.item21;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

/**
 * packageName : chapter4.item21
 * fileName : Worker
 * author : haedoang
 * date : 2022-03-23
 * description :
 */
class Worker implements Runnable {
    private final ArrayList<String> list;
    private final CountDownLatch countDownLatch;

    public Worker(ArrayList<String> list, CountDownLatch countDownLatch) {
        this.list = list;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        list.addAll(Arrays.asList("씻기", "옷입기", "출근하기", "퇴근하기"));
        countDownLatch.countDown();
    }
}
