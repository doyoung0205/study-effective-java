## 변경 가능성을 최소화하라

### 변경이 최소화된 클래스 - 불변 클래스

불변 클래스란 그 인스턴스 내부의 값을 변경할 수 없는 클래스를 뜻한다.

```
 이번 아이템 주제에 맞게 변경 가능성이 최소화되었다고 말할 수 있다.
```

### 불변이 되기 위한 조건

- 객체의 상태를 변경하는 메서드(변경자)를 제공하지 않는다.
- 클래스를 확장할 수 없도록 한다.
  - 하위 클래스에서 객체를 변경할 수 있는 사태가 있을 수 있기에 상속을 금지한다.
  - **방법**: 클래스를 final 이나 정적 팩토리메서드 패턴을 통해서 상속을 금지한다.
- 모든 필드를 `final` 로 선언한다.
  - 인스턴스를 동기화 없이 다른 스레드로 건네도 문제없이 동작하게끔 보장한다.
  - 참고: [자바 언어 명세의 메모리 모델 부분](https://docs.oracle.com/javase/specs/jls/se8/html/jls-17.html#jls-17.5)
- 모든 필드를 `private` 으로 선언한다.
  - 직접 접근해 필드 값 변경을 막아준다.
- 자신 외에는 내부의 가변 컴포넌트에 접근할 수 없도록 한다.
  - 가변객체가 있다면 가변객체를 직접적으로 접근하게 하면 안되며 방어적 복사를 통해서 접근 해야한다.

### 불변 객체 특징

- 상태를 변경하지 않고 변경된 상태를 가지는 객체를 생성해서 반환한다.
- 참고: `me.doyoung.studyeffectivejava.chapter3.item17.Complex`

### 불변 객체의 장점

- 스레드 안전하여 따로 동기화할 필요 없다.
- 불변 객체는 안심하고 공유할 수 있다. (최대한 재사용 되길 권한다.)
  - 주로 상수로 많이 제공된다.```BigInteger.ONE```

### 불변 객체 사용 TIP

- 객체를 만들 때 불변 객체들을 구성요소로 사용하면 불변식을 유지하기 수월하다.
- 실패 원자성 제공

```
실패 원자성 (failure atomicity) : 메서드에서 예외가 발생한 후에도 그 객체는 여전히 유효한 상태여야 하는 성질.

불변 객체는 내부의 상태를 바꾸지 않으니 이 성질을 만족한다.
```

### 불변 객체 단점

- 아주 조금이라도 값이 다르면 반드시 독립된 객체로 만들어야 한다. (객체 생성 비용 부담)

### 불변 객체의 단점 극복 TIP

- 캐싱: 똑같은 객체를 요청할 경우 캐싱한 값을 반환 (계산 비용 절감)
  - 참고: `me/doyoung/studyeffectivejava/chapter3/item17/DoyoungNumberTest.java`
  - 가변 동반 클래스(companion class) : 객체를 완성하기까지 단계가 많고, 중간 단계의 객체들이 모두 버려지면
    <br/> 성능 문제가 생길 수 있기 떄문에 해결방법으로 다단계 연산을 기본으로 제공하는 클래스
    - package-private 으로 두는 경우
      - 복잡한 연산이라 예측하기 어렵다면 공개하지 않고 package-private 접근제어자를 두는 것이 좋다.
      - 예를들어, BigInteger 는 `SignedMutableBigInteger`, `MutableBigInteger`, `BitSieve` 같은 가변 동반 클래스가 있다.
    - public 으로 두는 경우:
      - 복잡하지 않고 예측이 가능하다면 public 접근제어자로 두는 것이 좋다.
      - 예를 들어, String 은 `StringBuilder` 같은 가변 동반 클래스가 있다.

### BigInteger 와 BigDecimal 가 불변 객체가 되지 않을 수 있는 경우

`BigInteger` 와 `BigDecimal` 는 final class 이지 않고 public 생성자를 가졌기에 **상속을 할 수 있다.**
따라서 상속한 하위객체를 사용할 경우 불변 객체임을 확실히 보장할 수 없다. 따라서 매개변수에 `BigInteger` 와 `BigDecimal` 가 명시되어 있다면
<br/> 불변 객체가 보장되지 않은 하위객체들이 넘어올 가능성이 있으니 주의 해야한다.

```
public void calc(BigInteger val) {
  val = BigInteger.safeInstance(val); // 불변 객체 보장하기 위한 방어
}
```

## 어려웠던 내용

- (p.108) 불변 객체가 왜 스레드에서 안전한지?
  - 참고: `me.doyoung.studyeffectivejava.chapter3.item17.ThreadSafeTest.java`
- (p.108) 불변객체는 자유롭게 공유할 수 있고, 불변 객체끼리는 내부 데이터를 공유할 수 있다는 내용
  - 불변식을 유지할 수 있고 이는 실패 원자성을 보장하기 떄문이다.
- (p.111) BigInteger 와 BigDecimal 가 불변 객체가 되지 않을 수 있는 경우
  - final class 이지 않고 public 생성자를 가졌기에 **상속을 할 수 있다.**

### 참고

- https://github.com/Java-Bom/ReadingRecord/issues/47
- https://junseokdev.tistory.com/34
- https://devonce.tistory.com/26
- https://www.baeldung.com/java-testing-multithreaded
- https://docs.oracle.com/javase/tutorial/essential/concurrency/imstrat.html
- https://recordsoflife.tistory.com/686
- http://tutorials.jenkov.com/java-concurrency/thread-safety.html
- https://docs.oracle.com/javase/specs/jls/se8/html/jls-17.html#jls-17.5
- https://medium.com/javarevisited/java-concurrency-java-memory-model-96e3ac36ec6b
