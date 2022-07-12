## item81 wait과 nofity 보다는 동시성 유틸리티를 애용하라

### wait와 notify는 올바르게 사용하기가 아주 까다로우니 고수준 동시성 유틸리티를 사용하자


### 동시성 프레임워크
1. 실행자 프레임워크
2. 동시성 컬렉션(concurrent collection)
   - List, queue, Map 같은 표준 컬렉션 인터페이스에 동시성을 가미해 구현한 고성능 컬렉션
     - `ConcurrentHashMap`
   - 동시성 컬렉션에서 동시성을 무력화하는 건 불가능하며, 외부에서 락을 추가로 사용하면 오히려 속도가 느려진다
   - 여러 메서드를 원자적으로 묶어 호출하는 것은 불가능하다
     - `상태 의존적 수정` 메서드를 지원한다 ex) `putIfAbsent()`
3. 동기화 장치(synchronizer)


### Collections.synchronizedMap 보다는 ConcurrentHashMap을 사용하는 게 훨씬 좋다


### 동기화 장치
- `CountDownLatch`, `Semaphore`, `CyclicBarrier`, `Phaser`
- 사용 방법 정리:
  - [https://github.com/haedoang/java/blob/master/study/src/test/java/cuncurrent/OverviewTest.java](https://github.com/haedoang/java/blob/master/study/src/test/java/cuncurrent/OverviewTest.java)
  
### 시간 간격을 잴 떄는 System.currentTimeMills 보다 System.nanoTime이 더 정확하다

### wait 메서드를 사용할 떄는 반드시 대기 반복문을 사용하라. 반복문 밖에서는 호출하지 말자
```java
synchronized(obj) {
    while(condition) {
        obj wait();    
    }    
    // do next
}
```

### notify 대신 notifyAll()을 사용하라  
- 외부로 공개된 객체에 대한 실수로 혹은 악의적으로 `notify`를 호출하는 상황에 대비하기 위해 notifyAll() 사용을 권장한다

### 정리
- wait, notify를 직접 사용하지 말고 동시성 유틸리티를 사용하라
- 만약 사용하게 된다면 while 문 안에서만 사용하고, notify() 보다는 notifyAll()을 사용하여 응답 불가 상태에 빠지지 않도록 해야 한다