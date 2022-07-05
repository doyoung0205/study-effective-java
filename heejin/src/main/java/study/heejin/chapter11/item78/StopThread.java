package study.heejin.chapter11.item78;

import java.util.concurrent.TimeUnit;

/**
 * 이 프로그램이 1초 후에 종료될 것이라 생각하지만, 실제로는 종료되지 않고 계속 실행된다.
 * 원인은 동기화에 있다.
 *
 * OpenJDK 서버 VM이 실제로 적용하는 끌어올리기라는 최적화 기법으로 아래와 같이 최적화 될 수 있다.
 * // 원래 코드
 * while (!stopRequested) {
 *     i++;
 * }
 * // 최적화한 코드
 * if (!stopRequested) {
 *     while(true) {
 *         i++;
 *     }
 * }
 */
public class StopThread {
    private static boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested) {
                i++;
            }
        });

        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }
}
