package chapter11.item78;

import java.util.concurrent.atomic.AtomicLong;
import java.util.spi.AbstractResourceBundleProvider;

/**
 * author : haedoang
 * date : 2022/07/10
 * description :
 */
public class NumberGenerator {
    private static volatile int nextSerialNumber = 0;
    private static final AtomicLong nextSerialNum = new AtomicLong();

    public static void init() {
        nextSerialNumber = 0;
        nextSerialNum.updateAndGet(it -> 0L);
    }

    public static int generateSerialNumber() {
        return nextSerialNumber++;
    }

    public static long generateSerialNum() {
        return nextSerialNum.getAndIncrement();
    }
}
