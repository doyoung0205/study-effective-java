package chapter11.item78;

import java.util.concurrent.TimeUnit;

/**
 * author : haedoang
 * date : 2022/07/10
 * description :
 */
public class SyncStopThread {
    private static boolean stopRequested;

    // write 동기화
    private static synchronized void requestStop() {
        System.out.println(Thread.currentThread().getName() + "invoke requestStop()");
        stopRequested = true;
    }

    // read 동기화
    private static synchronized boolean stopRequested() {
        System.out.println(Thread.currentThread().getName() + " invoke stopRequested()");
        return stopRequested;
    }

    public static void main(String[] args) throws InterruptedException {
        final Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested()) {
                i++;
            }
        });
        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        requestStop();
    }
}
