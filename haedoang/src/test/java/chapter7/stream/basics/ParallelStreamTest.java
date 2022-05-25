package chapter7.stream.basics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName : stream
 * fileName : ParallelStreamTest
 * author : haedoang
 * date : 2022-05-17
 * description : <br/>
 * <a href="https://www.baeldung.com/java-when-to-use-parallel-stream">https://www.baeldung.com/java-when-to-use-parallel-stream</a>
 */
public class ParallelStreamTest {
    public static void main(String[] args) throws IOException {
        /** 순차스트림 (Sequential Streams) */
        System.out.println("-------- 순차 스트림 start--------");
        Arrays.asList(1, 2, 3, 4)
                .stream()  //default
                .forEach(it -> System.out.println(it + " " + Thread.currentThread().getName()));
        System.out.println("-------- 순차 스트림 end  --------");

        /** 병렬 스트림(Parallel Streams) => 별도의 코어에서 코드를 병렬로 실행한다. 순서 보장하지 않음의 유의할 것 */
        System.out.println("-------- 병렬 스트림 start--------");
        Arrays.asList(1, 2, 3, 4)
                .parallelStream()
                .forEach(it -> System.out.println(it + " " + Thread.currentThread().getName()));
        System.out.println("-------- 병렬 스트림 end--------");
    }

    /** 병렬 스트림은 포크 조인 프레임워크와 작업자 스레드의 공통 풀을 사용한다
     *  포크 조인 프레임워크는 여러 스레드 간의 작업 관리를 처리하기 위해 java 7의 java.util.concurrent에 추가되었다
     * */

    /**
     * Splitting Source
     * 포크 조인 프레임워크는 작업자 스레드 간에 소스 데이터를 분할하고 작업 완료 시 콜백을 처리하는 역할을 합니다
     */
    @Test
    @DisplayName("1 ~ 5 합 구하기 NG")
    public void parallelReduceNG() {
        // when
        int actual = Arrays.asList(1, 2, 3, 4)
                .parallelStream()
                .reduce(5, (n1, n2) -> {
                    System.out.println("n1 : " + n1 + " n2 :" + n2 + " thread: " + Thread.currentThread().getName());
                    return n1 + n2;
                });
        // then
        assertThat(actual).isEqualTo(30)
                .as("reduce 작업이 병렬 처리되기 때문에 모든 작업 스레드에 5가 추가된다");
    }

    @Test
    @DisplayName("1 ~ 5 합 구하기 OK")
    public void parallelReduceOK() {
        // when
        int actual = Arrays.asList(1, 2, 3, 4)
                .parallelStream()
                .reduce(0, (n1, n2) -> {
                    System.out.println("n1 : " + n1 + " n2 :" + n2 + " thread: " + Thread.currentThread().getName());
                    return n1 + n2;
                }) + 5;

        // then
        assertThat(actual).isEqualTo(15)
                .as("병렬 작업 처리 시 주의해야한다.");
    }

    /**
     * Common Thread Pool: 공통 풀의 스레드 수는 프로세서 코어 수와 같다
     * 스레드 수 변경(전역 설정):  -D java.util.concurrent.ForkJoinPool.common.parallelism=4
     */

    /**
     * Custom Thread Pool: 사용자 지정 스레드 풀
     */
    @Test
    @DisplayName("사용자 지정 스레드 풀 테스트")
    public void customThreadPoolTest() throws ExecutionException, InterruptedException {
        // given
        ForkJoinPool pool = new ForkJoinPool(1);
        Set<String> threads = new HashSet<>();

        // when
        int sum = pool.submit(() -> Arrays.asList(1, 2, 3, 4).parallelStream()
                .reduce(0, (n1, n2) -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("n1 : " + n1 + " n2 :" + n2 + " thread: " + threadName);
                    threads.add(threadName);
                    return n1 + n2;
                })).get();

        pool.shutdown();

        // then
        assertThat(sum).isEqualTo(10);
        assertThat(threads).hasSize(1)
                .as("오라클에서는 customThread pool의 사용보다 common thread pool 사용을 더 권장한다");
    }

    /***
     * 병렬 처리는 다중 코어 사용으로 인한 다중 스레드 관리, 메모리 지역성, 소스 분할 및 결과 병합의 오버헤드르 고려해야 한다
     *  benchmark 툴로 확인이 가능하다
     *
     *  implementation 'org.openjdk.jmh:jmh-core:1.35'
     *  testImplementation 'org.openjdk.jmh:jmh-generator-annprocess:1.35'
     *  https://www.baeldung.com/java-microbenchmark-harness
     */

    /** 1. Overhead :: 병렬 처리가 더 많은 오버헤드가 발생한다
     *
     *  IntStream.rangeClosed(1, 100).reduce(0, Integer::sum);
     *  IntStream.rangeClosed(1, 100).parallel().reduce(0, Integer::sum);
     *
     *  Benchmark                                                     Mode  Cnt        Score        Error  Units
     *  SplittingCosts.sourceSplittingIntStreamParallel               avgt   25      35476,283 ±     204,446  ns/op
     *  SplittingCosts.sourceSplittingIntStreamSequential             avgt   25         68,274 ±       0,963  ns/op [*]
     *
     *  참조 ) JMH modes
     *      - Throughput(처리량): 단위 시간당 작업
     *      - AverageTime(평균 시간): 작업 당 평균 처리 시간 -> 기본값
     *      - SampleTime(샘플 시간): 각 작업의 시간 샘플링
     *      - SingleShotTime(싱글 샷 타임): 단일 작업 시간 측정
     * */

    /**
     *  2. Splitting Code :: 데이터 소스 균등 분할 성능 차이 비교
     *
     *  private static final List<Integer> arrayListOfNumbers = new ArrayList<>();
     *  private static final List<Integer> linkedListOfNumbers = new LinkedList<>();
     *
     *  static {
     *     IntStream.rangeClosed(1, 1_000_000).forEach(i -> {
     *         arrayListOfNumbers.add(i);
     *         linkedListOfNumbers.add(i);
     *     });
     *  }
     *
     *  arrayListOfNumbers.parallelStream().reduce(0, Integer::sum);
     *  arrayListOfNumbers.stream().reduce(0, Integer::sum)
     *  linkedListOfNumbers.parallelStream().reduce(0, Integer::sum);
     *  linkedListOfNumbers.stream().reduce(0, Integer::sum);
     *
     *  Benchmark                                                     Mode  Cnt        Score        Error  Units
     *  DifferentSourceSplitting.differentSourceArrayListParallel     avgt   25    2004849,711 ±    5289,437  ns/op [*]
     *  DifferentSourceSplitting.differentSourceArrayListSequential   avgt   25    5437923,224 ±   37398,940  ns/op
     *  DifferentSourceSplitting.differentSourceLinkedListParallel    avgt   25   13561609,611 ±  275658,633  ns/op
     *  DifferentSourceSplitting.differentSourceLinkedListSequential  avgt   25   10664918,132 ±  254251,184  ns/op
     *
     *  ArrayList의 경우 배열로 이루어져 있으므로 코드 병렬 분할 시 이점을 가져온다. 그에 반해 LinkedList는 성능이 더 나빠졌다
     */

    /**
     *  3. Merge Cost :: 병렬 계산 시 최종적으로는 결과를 결합해야 한다 **
     *
     *  arrayListOfNumbers.stream().parallel().collect(Collectors.toSet());
     *  arrayListOfNumbers.stream().collect(Collectors.toSet());
     *  arrayListOfNumbers.stream().parallel().reduce(0, Integer::sum);
     *  arrayListOfNumbers.stream().reduce(0, Integer::sum);
     *
     *  Benchmark                                                     Mode  Cnt        Score        Error  Units
     *  MergingCosts.mergingCostsGroupingParallel                     avgt   25  135093312,675 ± 4195024,803  ns/op
     *  MergingCosts.mergingCostsGroupingSequential                   avgt   25   70631711,489 ± 1517217,320  ns/op
     *  MergingCosts.mergingCostsSumParallel                          avgt   25    2074483,821 ±    7520,402  ns/op [*]
     *  MergingCosts.mergingCostsSumSequential                        avgt   25    5509573,621 ±   60249,942  ns/op
     *
     *  병렬 처리는 축소 및 추가와 같은 작업에 대해서는 비용이 적게 드는 반면 set 또는 map 같이 그룹핑하는 병합 작업에는 비용이 많이 들게 된다
     */

    /***
     *  4. Memory Locality :: 병렬 처리는 프로세서 코어가 유용한 작업(선형 자료구조)을 계속 바쁘게 할 때 성능 이점을 가져온다
     *
     *  private static final int[] intArray = new int[1_000_000];
     *  private static final Integer[] integerArray = new Integer[1_000_000];
     *
     *  static {
     *     IntStream.rangeClosed(1, 1_000_000).forEach(i -> {
     *         intArray[i-1] = i;
     *         integerArray[i-1] = i;
     *     });
     *  }
     *
     *  Arrays.stream(intArray).reduce(0, Integer::sum);
     *  Arrays.stream(intArray).parallel().reduce(0, Integer::sum);
     *  Arrays.stream(integerArray).reduce(0, Integer::sum);
     *  Arrays.stream(integerArray).parallel().reduce(0, Integer::sum);
     *
     *  Benchmark                                                     Mode  Cnt        Score        Error  Units
     *  MemoryLocalityCosts.localityIntArraySequential                avgt   25     293142,385 ±    2526,892  ns/op
     *  MemoryLocalityCosts.localityIntArrayParallel                  avgt   25     116247,787 ±     283,150  ns/op [*]
     *  MemoryLocalityCosts.localityIntegerArraySequential            avgt   25    5134866,640 ±  148283,942  ns/op
     *  MemoryLocalityCosts.localityIntegerArrayParallel              avgt   25    2153732,607 ±   16956,463  ns/op
     *
     *  최고의 지역성을 가지는 배열 자료구조에서는 순차스트림보다 병렬스트림이 더 많은 성능 이점을 가져온다.
     */

    /***
     *  NQ Model :: 병렬 처리 성능 향상 제공하는 지 분석하는 모델
     *   - N (소스 데이터의 요소 수)
     *   - Q (데이터 요소당 수행된 계산의 양)
     *
     *  N * Q 값이 클수록 병렬화로 인한 성능 향상될 가능성이 높다(숫자 합산과 같이 사소한 Q가 있는 경우 N > 10000)
     *  계산 수가 증가하면 병렬 처리로 성능을 높이는 데 필요한 데이터 크기가 줄어든다
     */

}
