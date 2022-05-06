package study.heejin.chapter7.item45;

import java.math.BigInteger;
import java.util.stream.Stream;

/**
 * 메르센 수는 2^p - 1 형태의 수다.
 * 여기서 p가 소수이면 해당 메르센 수도 소수일 수 있는데, 이때의 수를 메르센 소수라고 한다.
 */
public class MersennePrimes {
    public static void main(String[] args) {
        primes().map(p -> BigInteger.TWO.pow(p.intValueExact()).subtract(BigInteger.ONE))
                .filter(mersenne -> mersenne.isProbablePrime(50))
                .limit(10)
                .forEach(mp -> System.out.println(mp.bitLength() + ": " + mp));
    }

    static Stream<BigInteger> primes() {
        return Stream.iterate(BigInteger.TWO, BigInteger::nextProbablePrime);
    }
}
