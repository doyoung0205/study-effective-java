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


volatile

https://ttl-blog.tistory.com/238#volatile%EC%-D%--%--%EB%AC%B-%EC%A-%-C

a. 배타적 실행
위에 말한 대로 한 쓰레드가 변경하는 중이라서, 상태가 일관되지 않는 (공유하는 자원의) 객체를 현재 사용중인 쓰레드만 접근이 가능하고, 다른 쓰레드가 보지 못하게 막는 용도를 말한다.

이때 락에 대한 개념이 나온다. 락을 건 메서드에서 객체의 상태를 확인하고 필요하면 수정하도록 작성했을 때, 한 쓰레드에서 해당 메서드를 사용하게 되면 객체에 락이 걸리게 되고, 해당 객체는 다른 쓰레드에서 동시에
접근이 불가능하다.

즉 배타적 실행은 객체를 하나의 일관된 상태에서 다른 일관된 상태로 변화시키는 것이다.

https://jyami.tistory.com/112

Atomic 변수의 경우 CAS (Compare And Swap)알고리즘을 통해 동작한다.

CAS 알고리즘이란 현재 쓰레드가 존재하는 CPU 의 CacheMemory 와 MainMemory 에 저장된 값을 비교하여, 일치하는 경우 새로운 값으로 교체하고, 일치하지 않을 경우 기존 교체가 실패되고, 이에
대해 계속 재시도를 하는 방식이다.

