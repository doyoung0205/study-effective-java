package study.heejin.chapter11;

import java.util.concurrent.TimeUnit;

/**
 * stopRequested 필드를 동기화해 접근하면 기대한 대로 1초 후에 종료된다.
 *
 * 여기서 쓰기 메서드(requestStop)와 읽기 메서드(stopRequested) 모두를 동기화 했음을 주목해야 한다.
 * 쓰기와 읽기 모두가 동기화되지 않으면 동작을 보장하지 않는다.
 */
public class StopThreadSynchronized {
    private static boolean stopRequested;

    private static synchronized void requestStop() {
        stopRequested = true;
    }

    private static synchronized boolean stopRequested() {
        return stopRequested;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested())
                i++;
        });

        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        requestStop();
    }
}
