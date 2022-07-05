package study.heejin.chapter11.item78;

import java.util.concurrent.atomic.AtomicLong;

/**
 * java.util.concurrent.atomic 패키지에는 락 없이도 스레드 안전한 프로그래밍을 지원하는 클래스들이 담겨있다.
 * volatile은 동기화의 두 효과 중 통신 쪽만 지원하지만 이 패키지는 원자성(배타적 실행)까지 지원한다.
 */
public class VolatileSerialNumberAutomic {
    private static final AtomicLong nextSerialNumber = new AtomicLong();

    public static long generateSerialNumber() {
        return nextSerialNumber.getAndIncrement();
    }
}
