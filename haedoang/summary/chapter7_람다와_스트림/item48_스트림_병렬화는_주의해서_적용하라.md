## item48 스트림 병렬화는 주의해서 적용하라

### 자바 병렬화 히스토리
- 처음 릴리즈되었을 때부터 자바는 `스레드`, `동기화`, `wait/notify`를 지원했다
- 자바 5부터는 `java.util.concurrent` 라이브러리와 `실행자(Executor)` 프레임워크를 지원했다
- 자바 7부터는 `고성능 병렬 분해`(parallel decom-position) 프레임워크인 `포크-조인(fork-join)`를 패키지를 추가했다
- 자바 8부터는 `parallel` 메서드 한번 호출로 파이프라인을 병렬 실행할 수 있는 `스트림`을 지원한다

### 병렬 스트림 사용 주의 사항 
- 데이터 소스가 Stream.iterate 이거나 중간 연산으로 limit을 사용하는 경우 병렬화 성능 개선을 할 수 없다
    - 스트림 라이브러리가 병렬화 방법을 찾지 못해 무한 루프에 빠질 수 있다
- 스트림 병렬 처리 사용에 좋은 자료 구조, 타입
  - 스트림 소스가 `ArrayList`, `HashMap`, `HashSet`, `ConcurrentHashMap` 의 인스턴스, 배열, int 범위, long 범위
  - `참조 지역성`이 뛰어난 자료구조이다
  - 이에 대한 상세 설명은 `summary/stream/병렬스트림.md` 파일에 병렬 스트림에 대한 내용을 모두 정리해두었으니 참고하자
- 스트림을 잘못 병렬화 하는 경우(응답 불가 포함) 성능이 나빠지거나 결과 자체가 잘못될 수 있다
  - `stream/ParallelStream.java` 참고

### 병렬 스트림으로 성능을 개선하는 방법
- 조건이 잘 갖추어지는 경우(참조 지역성이 뛰어난 자료구조, 다량의 데이터 등) 코어 수만큼의 성능 향상을 누릴 수 있다
    ```java
    public static long getPrimeCount(int max) {
            return LongStream.range(2, max)
                    .mapToObj(BigInteger::valueOf)
                    .filter(i -> i.isProbablePrime(50))
                    .count();
        }
    
    // faster    
    public static long getPrimeCountParallel(int max) {
        return LongStream.range(2, max)
                .parallel()
                .mapToObj(BigInteger::valueOf)
                .filter(i -> i.isProbablePrime(50))
                .count();
    }
    ```
  
### 정리
- java는 순차 스트림을 기본적으로 지원한다
- 병렬 스트림은 잘못 사용하는 경우 성능 상의 문제를 일으킬 수 있다
- 따라서 성능 개선이 필요한 경우 순차 스트림에서 병렬 스트림으로 전환하는 방향으로 진행하도록 한다
- 운영 환경과 유사한 조건에서 수행하여 성능 지표를 관찷한 후 성능 개선에 확신이 있을 때 병렬화 코드를 사용하도록 하자


