package study.heejin.chapter7.item48;

import java.math.BigInteger;
import java.util.stream.Stream;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TWO;

public class ParallelMersennePrimes {
    /**
     * 쿼드 코어에서 마지막 20번째 계산을 수행하는 시검에 CPU 3개가 남아 있을 것이다.
     * 그러면 21, 22, 23번째 메르센 소수를 찾는 작업이 병렬로 시작된다.
     * 하지만 21, 22, 23번째 계산은 20번째 계산보다 2배, 4배, 8배의 시간이 더 걸린다.
     * 따라서 병렬화의 영향으로 프로그램이 한참동안 종료되지 않게 된다.
     */
    public static void main(String[] args) {
        primes().map(p -> TWO.pow(p.intValueExact()).subtract(ONE))
                .parallel() // 스트림 병렬화
                .filter(mersenne -> mersenne.isProbablePrime(50))
                .limit(20)
                .forEach(System.out::println);
    }

    private static Stream<BigInteger> primes() {
        return Stream.iterate(TWO, BigInteger::nextProbablePrime);
    }
}
