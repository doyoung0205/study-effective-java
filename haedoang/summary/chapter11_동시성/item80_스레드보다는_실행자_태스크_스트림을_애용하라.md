## 스레드보다는 실행자, 태스크, 스트림을 애용하라

### java.util.concurrent 패키지의 등장
- 실행자 프레임워크(Executor Framework) 
- 작업 큐 생성을 간단하게 할 수 있다
    ```java
    ExecutorService exec = Executors.newSingleThreadExecutor(); // 작업큐를 생성한다
    
    exec.execute(runnable); // 실행자에 태스크를 넘긴다
    
    exec.shutdown(); //실행자 종료
    ```
  
### 실행자 서비스의 주요 기능
- 특정 태스크가 완료되기를 기다린다 
  - `get()`
- 태스크 모음 중 아무것 하나(invokeAny 메서드) 혹은 모든 태스크(invokeAll 메서드)가 완료되기를 기다린다
- 실행자 서비스가 종료하기를 기다린다
  - `awaitTermination()`
- 완료된 태스크들의 결과를 차례로 받는다
  - `ExecutorCompletionService`
- 태스크를 특정 시간에 혹은 주기적으로 실행하게 한다
  - `ScheduledThreadPoolExecutor`

### 큐를 여러개 동시에 실행하려면 다른 종류의 실행자 서비스(스레드 풀)을 이용하라 
- `java.util.concurrent.Executors` 의 정적 팩터리 생성
- ThreadPollExecutor 
  - 스레드를 완전히 통제할 수 있다 

### Executors 정적 팩터리 클래스 
- `Executors.newCachedThreadPool`
  - 작거나 가벼운 서버에서 사용하기 좋다
  - 요청받은 태스크들이 큐에 쌓이지 않고 즉시 스레드에 위임돼 실행된다
- `Executors.newFixedThreadPool`
  - 스레드 개수를 고정할 수 있다

### 작업 큐를 손수 만드는 일은 삼가고, 스레드를 직접 다루는 것도 삼가하라 
- 스레드를 직접 다루면 `Thread`가 작업 단위와 수행 메커니즘 역할을 모두 수행해야 한다

### 실행자 프레임워크의 사용
- `Runnable`, `Callable` 인터페이스를 지원한다
  - 값을 반환하고, 예외를 던질 수 있다
- 태스크 수행 정책을 선택할 수 있고, 언제든 변경할 수 있다
- 포크-조인(fork-join) 태스크 지원
  - 자바7 이상 지원 
  - `ForkJoinPool` 사용
  - 모든 스레드가 바쁘게 움직여 CPU를 최대한 사용하여 높은 처리량과 낮은 지연시간을 달성한다
  

### 정리
- 작업 큐를 손수 만들지 말며, 스레드를 직접 다루지 말고 실행자 서비스를 사용하자
