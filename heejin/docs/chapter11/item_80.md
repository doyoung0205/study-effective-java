# 동시성

[아이템 80.스레드보다는 실행자, 태스크, 스트림을 애용하라](#스레드보다는-실행자-태스크-스트림을-애용하라)  

[- 실행자 서비스의 주요 기능](#실행자-서비스의-주요-기능)  
[- 태스크와 실행자 매커니즘](#태스크와-실행자-매커니즘)  

<br>

## 스레드보다는 실행자, 태스크, 스트림을 애용하라
- `java.util.concurrent` 패키지는 실행자 프레임워크라고 하는 인터페이스 기반의 유연한 태스크 실행 기능을 담고 있다.

    ```java
    // 작업 큐 생성
    ExecutorService exec = Executors.newSingleThreadExecutor();
    // 실행자에게 태스크(작업) 전달 
    exec.execute(runnable);
    // 실행자 종료
    exec.shutdown()
    ```

### 실행자 서비스의 주요 기능
- 특정 태스크가 완료되기를 기다린다. - [get](../../src/test/java/study/heejin/chapter11/Item79Test.java#LC70)
- 태스크 모음 중 아무것 하나(`invokeAny`) 혹은 모든 태스크(`invokeAll`)가 완료되기를 기다린다.
- 실행자 서비스가 종료하기를 기다린다. - `awaitTermination`
- 완료된 태스크들의 결과를 차례로 받는다. - `ExecutorCompletionService`
- 태스크를 특정 시간에 혹은 주기적으로 실행하게 한다. - `ScheduledThreadPoolExecutor`

#### `ThreadPoolExecutor`
- 스레드 풀 동작을 결정하는 거의 모든 속성을 설정할 수 있다. - [Docs](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/concurrent/ThreadPoolExecutor.html)

#### `Executors.newCachedThreadPool`
- 작은 프로그램이나 가벼운 서버라면 이 일반적으로 좋은 선택일 것이다.
- 특별히 설정할게 없고 일반적인 용도에 적합하게 동작한다.
- `CachedThreadPool`은 무거운 프로덕션 서버에는 좋지 못하다.
  - `CachedThreadPool`에서는 요청받은 태스크들이 큐에 쌓이지 않고 즉시 스레드에 위임되어 실행된다.
  - 가용 스레드가 없다면 새로 하나를 생성한다.
  - 서버가 아주 무겁다면 CPU 이용률이 100%로 치닫고, 새로운 태스크가 도착하는 족족 또 다른 스레드를 생성하며 상황을 더욱 악화시킨다.

#### `Executors.newFixedThreadPool`
- 무거운 프로덕션 서버에는 스레드 수를 고정(`FixedThreadPool`)하여 사용하거나, 완전히 통제(`ThreadPoolExecutor`)하여 사용하는 편이 훨씬 낫다.


### 태스크와 실행자 매커니즘
- 실행자 프레임워크에서는 *작업 단위*와 *실행 메커니즘*이 분리된다.
- 작업 단위를 나타내는 핵심 추상 개념이 *태스크*이다.
- 태스크에서는 두 가지가 있다.
  - `Runnable`
  - `Callable`: `Runnable`과 비슷하지만 값을 반환하고 임의의 예외를 던질 수 있다.
- 태스크를 수행하는 일반적인 메커니즘이 실행자 서비스이다.
  - 태스크 수행을 실행자 서비스에 맡기면 원하는 태스크 수행 정책을 선택할 수 있고, 언제든 변경할 수 있다.
- 자바 7이 되면서 실행자 프레임워크는 포크-조인 태스크를 지원하도록 확장되었다.
  - 포크-조인 태스크는 포크-조인 풀이라는 특별한 실행자 서비스가 실행해준다.
  - `ForkJoinTask`의 인스턴스는 작은 하위 태스크로 나뉠 수 있다.
  - `ForkJoinPool`을 구성하는 스레드들이 이 태스크들을 처리하며, 일을 먼저 끝낸 스레드는 다른 스레드의 남은 태스크를 가져와 대신 처리할 수도 있다.
  - 포크-조인 태스크를 직접 작성하고 튜닝하기란 어려운 일이지만, 포크-조인 풀을 이용해 만든 병렬 스트림을 이용하면 적은 노력으로 그 이점을 얻을 수 있다. (물론, 포크-조인에 적합한 형태여야 한다.) _(→ item48)_


<br>
