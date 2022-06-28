
For the purposes of the Java programming language memory model, a single write to a non-volatile long or double value is treated as two separate writes: one to each 32-bit half. 

This can result in a situation where a thread sees the first 32 bits of a 64-bit value from one write, and the second 32 bits from another write.

https://docs.oracle.com/javase/specs/jls/se7/html/jls-17.html#jls-17.7



volatile

https://ttl-blog.tistory.com/238#volatile%EC%-D%--%--%EB%AC%B-%EC%A-%-C


a. 배타적 실행
위에 말한 대로 한 쓰레드가 변경하는 중이라서, 상태가 일관되지 않는 (공유하는 자원의) 객체를 현재 사용중인 쓰레드만 접근이 가능하고, 다른 쓰레드가 보지 못하게 막는 용도를 말한다.

이때 락에 대한 개념이 나온다. 락을 건 메서드에서 객체의 상태를 확인하고 필요하면 수정하도록 작성했을 때, 한 쓰레드에서 해당 메서드를 사용하게 되면 객체에 락이 걸리게 되고, 해당 객체는 다른 쓰레드에서 동시에 접근이 불가능하다.

즉 배타적 실행은 객체를 하나의 일관된 상태에서 다른 일관된 상태로 변화시키는 것이다.

https://jyami.tistory.com/112


Atomic 변수의 경우 CAS (Compare And Swap)알고리즘을 통해 동작한다.

CAS 알고리즘이란 현재 쓰레드가 존재하는 CPU 의 CacheMemory 와 MainMemory 에 저장된 값을 비교하여, 일치하는 경우 새로운 값으로 교체하고, 일치하지 않을 경우 기존 교체가 실패되고, 이에 대해 계속 재시도를 하는 방식이다.

