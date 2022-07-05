package study.heejin.chapter11.item78;

/**
 * generateSerialNumber 메서드에 synchronized 한정자를 붙이면 동시에 호출해도 서로 간섭하지 않고 이전 호출에서 변경한 값을 읽게 된다.
 * 메서드에 synchronized를 붙였다면 nextSerialNumber 필드에서는 volatile을 제거해야 한다.
 */
public class VolatileSerialNumberSynchronized {
    private static int nextSerialNumber = 0;

    public static synchronized int generateSerialNumber() {
        return nextSerialNumber++;
    }
}
