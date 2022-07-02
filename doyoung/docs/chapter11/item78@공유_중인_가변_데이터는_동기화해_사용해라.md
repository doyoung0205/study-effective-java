### 공유 중인 가변 데이터는 동기화해 사용해라

### 용어

`synchronized`:  해당 메서드나 블록을 한번에 한 스레드씩 수행하도록 보장한다.
`베타적 실행`: 한 스레드가 변경하는 중이라서 상태가 일관되지 않는 순간의 객체를 다른 스레드가 보지 못하게 막는 용도

### 동기화의 목적

한 객체가 일관된 상태를 가지고 생성되고, 이 객체에 접근하는 메서드는 그 객체에 락을 건다.
락을 건 메서드는 객체의 상태를 확인하고 필요하면 수정한다.

1. **즉, 객체를 하나의 일관된 상태에서 일관된 상태로 변화시킨다.**

동기화를 제대로 사용하면 어떤 메서드로 이 객체의 상태가 일관되지 않은 순간을 볼 수 없을 것이다.

동기화 없이는 한 스레드가 만든 변화를 다른 스레드에서 확인하지 못할 수 있다.(???)

2. 동기화는 일관성이 깨진 상태를 볼 수 없게 하는 것은 물론, 동기화된 메서드나
   블록에 들어간 **스레드가 같은 락의 보호하에 수행된 모든 이전 수정의 최종 결과를 보게 해준다.**

**언어 명세상 long 과 double 외의 변수를 읽고 쓰는 동작은 원자적 이다.**

```
long 과 double 이 원자적이지 않은 이유는 64-bit 이기 때문이다.

For the purposes of the Java programming language memory model, a single write to a non-volatile long or double value is
treated as two separate writes: one to each 32-bit half.

This can result in a situation where a thread sees the first 32 bits of a 64-bit value from one write, and the second 32
bits from another write.
```

**[출처](https://docs.oracle.com/javase/specs/jls/se7/html/jls-17.html#jls-17.7)**

#### 동기화는 베타적 실행뿐 아니라 스레드 사이의 안정적인 통신에 꼭 필요하다.

```java
public class StopThread {
    private static boolean stopRequested;

    public static void main(String[] args)
            throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested)
                i++;
        });
        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }
}
```

동기화 되지 않는 다면 가상 머신이 다음과 같은 최적화를 수행한다.

```
if(!stopRequested)
   while (true)
      i++;
```

백그라운드 스레드를 메인 스레드가 보았을 때는 stopRequested 가 변화한다는 것을 눈치 채지 못하고
한번 !stopRequested 상태가 true 일 경우에는 항상 while 문이 실행한다고 이해한 것 이다.

따라서 stopRequested 부분에 쓰기와 읽기 부분에 동기화 문을 추가해야 변화를 확인 할 수 있다.

```
private static synchronized void requestStop() {
  stopRequested = true;
}

private static synchronized boolean stopRequested() {
  return stopRequested;
}
```

조금 더 빠른 대안으로는 volatile 으로 선언하는 것도 있다.

[출처](https://ttl-blog.tistory.com/238#volatile%EC%-D%--%--%EB%AC%B-%EC%A-%-C)
[출처](https://jyami.tistory.com/112)

베타적 수행과는 상관 없지만 항상 최근에 기록된 값을 읽게 됨을 보장한다.

```java
public class StopThread {
    private static volatile boolean stopRequested;

    public static void main(String[] args)
            throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested)
                i++;
        });
        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }
}
```

### volatile 은 안전 실패를 조심해야 한다.

예를 들어, number++ 같은 연산은 number = number + 1 이다. number 를 읽고 1증가한 새로운 값을 저장하는 것이다.
만약 두 번째 스레드가 이 두 접근 사이에 비집고 들어와 값을 읽어가면 첫 번째 스레드와 똑같은 값을 돌려받게 된다.

따라서 synchronized 한정자를 붙이면 이 문제가 해결된다.
그리고 더 견고하게 만들려면 최댓값에 도달하면 예외를 던지게 하자

### 또다른 대안으로 atomic 을 사용해보자

이 패키지는 락 없이도 (lock-free)스레드 안전한 프로그래밍을 지원한다.
volatile 은 동기화의 두 효과 중 통신 쪽만 지원하지만 이 패키지는 원자성까지 지원한다.

Atomic의 경우 CAS (Compare And Swap)알고리즘을 통해 동작한다.

CAS 알고리즘이란 현재 쓰레드가 존재하는 CPU 의 CacheMemory 와 MainMemory 에 저장된 값을 비교하여, 일치하는 경우 새로운 값으로 교체하고, 일치하지 않을 경우 기존 교체가 실패되고, 이에
대해 계속 재시도를 하는 방식이다.

성능이 동기화 버전보다 우수하다.

### 애초에

애초에 가변 데이터를 공유하지 않는 것이다. 다시 말해 가변 데이터는 단일 스레드에서만 사용하도록 하자!!

한 스레드가 데이터를 다 수정한 후 다른 스레드에 공유할 때는 해당 객체에서 공유하는 부분만 동기화해도 된다.
그러면 그객체를 다시 수정할 일이 생기기 전까지 다른 스레드들은 동기화 없이 자유롭게 값을 읽어갈 수 있다.

이러한 객체를 사실상 불변이라 하고 다른 스레드에 이런 객체를 건네는 행위를 안전 발행 이라 한다.

객체를 안전하게 발행하는 방법은 정적필드, volatile, final, 혹은 synchronized 와 같은 락을 통해 접근하는 필드에 저장해도 된다.





