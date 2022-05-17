package chapter7.item48;

import chapter7.Item48.StreamUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TWO;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName : chapter7.item48
 * fileName : Item48Test
 * author : haedoang
 * date : 2022-05-17
 * description :
 */
public class Item48Test {
    Stream<BigInteger> primes;

    @BeforeEach
    void setUp() {
        primes = Stream.iterate(TWO, BigInteger::nextProbablePrime);
    }

    @Test
    @DisplayName("메르센 소수 구하기")
    public void mersennePrime() {
        // given
        StopWatch stopWatch = new StopWatch();

        // when
        stopWatch.start();

        List<BigInteger> actual = primes.map(p -> TWO.pow(p.intValueExact()).subtract(ONE))
                .filter(mersenne -> mersenne.isProbablePrime(50))
                .limit(20)
                .collect(Collectors.toList());

        stopWatch.stop();

        // then
        assertThat(actual).hasSize(20);
        assertThat(actual).contains(BigInteger.valueOf(3), BigInteger.valueOf(7));
        System.out.println(stopWatch.getStopTime());
    }


    /**
     * Stream.Iterate 소스이거나 중간 연산이 limit인 경우
     * 스트림 파이프라인은 병렬화 할 수 없다. 따라서 사용에 주의를 해야 한다
     * => 스트림 라이브러리가 병렬화 하는 방법을 찾지 못해 연산이 종료되지 않는다
     */
    @Disabled
    @Test
    @DisplayName("잘못 사용한 parallel 테스트")
    public void parallelNotUsingWithLimit() {
        // given
        primes.map(p -> TWO.pow(p.intValueExact()).subtract(ONE))
                .parallel()
                .filter(mersenne -> mersenne.isProbablePrime(50))
                .limit(20)
                .collect(Collectors.toList());

        // then
        throw new AssertionError();
    }


    @Test
    @DisplayName("소수 구하기 순차 스트림과 병렬 스트림 비교")
    public void getPrimeSequentialVersusParallel() {
        // when
        StopWatch stopWatch = new StopWatch();
        stopWatch.reset();
        stopWatch.start();

        StreamUtils.getPrimeCount(100);

        stopWatch.stop();

        double sequentialResult = stopWatch.getTime();

        stopWatch.reset();
        stopWatch.start();

        StreamUtils.getPrimeCountParallel(100);

        stopWatch.stop();

        double parallelResult = stopWatch.getTime();

        // then
        assertThat(sequentialResult).isGreaterThan(parallelResult);
    }
}
