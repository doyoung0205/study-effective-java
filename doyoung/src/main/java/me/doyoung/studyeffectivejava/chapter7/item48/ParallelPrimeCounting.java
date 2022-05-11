package me.doyoung.studyeffectivejava.chapter7.item48;

import java.math.BigInteger;
import java.util.stream.LongStream;

public class ParallelPrimeCounting {
    // 코드 48-3 소수 계산 스트림 파이프라인 - 병렬화 버전 (295쪽)
    static long piWithParallel(long n) {
        return LongStream.rangeClosed(2, n)
                .parallel()
                .mapToObj(BigInteger::valueOf)
                .filter(i -> i.isProbablePrime(50))
                .count();
    }

    static long pi(long n) {
        return LongStream.rangeClosed(2, n)
                .mapToObj(BigInteger::valueOf)
                .filter(i -> i.isProbablePrime(50))
                .count();
    }

    public static void main(String[] args) {
        System.out.println(pi(10_000_000));
    }
}
