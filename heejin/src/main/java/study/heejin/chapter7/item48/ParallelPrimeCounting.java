package study.heejin.chapter7.item48;

import java.math.BigInteger;
import java.util.stream.LongStream;

public class ParallelPrimeCounting {

    public static void main(String[] args) {
        System.out.println(pi(10_000_000));
    }

    /**
     * n보다 작거나 같은 소수의 개수를 계산하는 함수
     * 성능: 병렬화 X -> pi(10^8) 31초
     *      병렬화 O -> pi(10^8) 9.2초
     * (참고) n이 너무 커지는 경우에는 이 방식보다 레머의 공식이라는 알고리즘을 사용하는 것이 더 효율적이다.
     */
    static long pi(long n) {
        return LongStream.rangeClosed(2, n)
                .parallel()
                .mapToObj(BigInteger::valueOf)
                .filter(i -> i.isProbablePrime(50))
                .count();
    }
}
