package chapter7.Item48;

import java.math.BigInteger;
import java.util.stream.LongStream;

/**
 * packageName : chapter7.Item48
 * fileName : StreamUtils
 * author : haedoang
 * date : 2022-05-17
 * description :
 */
public class StreamUtils {
    private StreamUtils() {
    }

    public static long getPrimeCount(int max) {
        return LongStream.range(2, max)
                .mapToObj(BigInteger::valueOf)
                .filter(i -> i.isProbablePrime(50))
                .count();
    }

    public static long getPrimeCountParallel(int max) {
        return LongStream.range(2, max)
                .parallel()
                .mapToObj(BigInteger::valueOf)
                .filter(i -> i.isProbablePrime(50))
                .count();
    }

}
