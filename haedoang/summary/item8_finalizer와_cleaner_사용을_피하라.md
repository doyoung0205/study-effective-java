### 아이템8 finalizer와 cleaner 사용을 피하라
- 자바는 `finalizer`와 `cleaner` 객체 소멸자를 제공한다.
- 하지만 성능 상의 문제로 사용을 지양하도록 하자.(가비지 컬렉터보다 성능이 좋지 않다)
- 또한, `finalizer`는 `finalizer attack`에 취약한 보안 문제가 있다.
- 객체 소멸이 확실하지 않은 finalizer 나 cleaner를 사용하는 것은 시스템에 문제를 발생할 여지가 있으므로 사용을 지양하자

#### finalizer Attack 이란
- finalize 메서드를 통해 클래스의 정보에 접근하는 공격 방식이다.(chapter2.item8.FinalizerAttackTest.java)

#### 네이티브 피어
- `JFrame`과 같은 자바 객체가 네이티브 메서드를 통해 기능을 위임한 객체로 가비지 컬렉터는 이 존재를 알지 못한다.
- 이러한 객체는  finalizer 나 cleaner를 통해 처리하기 적당하다 
- 단, 성능 저하가 발생할 우려가 있기 때문에 즉시 메모리를 수거해야할 경우라면 `close` 메서드를 사용하도록 하자
