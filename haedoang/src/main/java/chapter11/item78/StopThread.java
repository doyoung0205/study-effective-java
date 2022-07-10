package chapter11.item78;

import java.util.concurrent.TimeUnit;

/**
 * author : haedoang
 * date : 2022/07/10
 * description :
 */
public class StopThread {
    private static boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {
        final Thread backgroundThread = new Thread(() -> {
            int i = 0;

            // 동기화하지 않아서 메인 스레드가 수정한 값을 백그라운드스레드가 본다는 보장을 할 수 없음
            while (!stopRequested) {
                i++;
            }
//      위의 코드는 VM 최적화한 결과 코드는 아래와 같다 응답 불가 상태가 된다
//            if (!stopRequested) {
//                while (true) {
//                    i++;
//                }
//            }
        });

        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }
}
