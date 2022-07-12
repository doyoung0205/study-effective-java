### 스레드보다는 실행자, 태스크 스트림을 애용하라

**Executor** Framework 라고 하는 인터페이스 기반의 유연한 **태스크** 실행 기능을 담고 있다.

```
ExecutorService exec = Executors.newSingleThreadExecutor();

// 실행
exec.execute(runnable);

// 종료
exec.shutdown();
```

실행자의 주요 기능들

- 특정 테스크가 완료되기를 기다린다.
- 태스크 모음 중 아무것 하나(invokeAny 메서드) 혹은 모든 태스크 (invoke All 메서드)가 완료되기를 기다린다.
- 실행자 서비스가 종료하기를 기다린다. (awaitTermination 메서드)
- 완료된 태스크들의 결과를 차례로 받는다. (ExecutorCompletionService 이용).
- 태스크를 특정 시간에 혹은 주기적으로 실행하게 한다.(ScheduledThreadPoolExecutor 이용.)


- Executors.newSingleThreadExecutor()
- Executors.newCachedThreadPool() : 태스크를 큐에 쌓이지 않고 즉시 스레드의 위임 가벼운 서버는 좋지만 무거운 서버는 비추
- Executors.newFixedThreadPool() : 무거운 서버에선 고정된 스레드를 사용하는것이 훨씬 낫다.

자바 7 이후

ForkJoinTask 포크 조인 태스크는 포크 조인 풀 이라는 특별한 실행자 서비스가 실행해준다.
ForkJoinTask 를 구성하는 스레드들이 이 태스크들을 처리하며 일을 먼저 끝낸 스레드는 다른 스레드가 바쁘게 움직여 CPU를
최대한 활용하면서 높은 처리량과 낮은 지연시간을 달성한다. (병렬 스트림) 자바 병렬 프로그래밍




