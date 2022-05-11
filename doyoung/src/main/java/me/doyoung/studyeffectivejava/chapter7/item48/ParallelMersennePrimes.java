package me.doyoung.studyeffectivejava.chapter7.item48;

import java.math.BigInteger;
import java.util.stream.Stream;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TWO;

// 병렬 스트림을 사용해 처음 20개의 메르센 소수를 생성하는 프로그램 (291쪽 코드 48-1의 병렬화 버전)
// 주의: 병렬화의 영향으로 프로그램이 종료하지 않는다.
public class ParallelMersennePrimes {
    public static void main(String[] args) {
        primes().map(p -> TWO.pow(p.intValueExact()).subtract(ONE))
                .parallel() // 스트림 병렬화
                .filter(mersenne -> mersenne.isProbablePrime(50))
                .limit(20)
                .forEach(System.out::println);
    }

    static Stream<BigInteger> primes() {
        return Stream.iterate(TWO, BigInteger::nextProbablePrime);
    }
}
