package chapter11.item78;

import java.util.concurrent.TimeUnit;

/**
 * author : haedoang
 * date : 2022/07/10
 * description :
 */
public class SyncStopThread2 {
    // 항상 가장 최근에 기록된 값을 읽게 보장한다. 동기화보다 속도가 빠름
    private static volatile boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {
        final Thread backgroundThread = new Thread(() -> {
            int i = 0;

            // 동기화하지 않아서 메인 스레드가 수정한 값을 백그라운드스레드가 본다는 보장을 할 수 없음
            while (!stopRequested) {
                i++;
            }
        });

        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }
}
