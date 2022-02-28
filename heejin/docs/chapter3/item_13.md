# 모든 객체의 공통 메서드

[아이템 13. clone 재정의는 주의해서 진행하라](#clone-재정의는-주의해서-진행하라)   
[- Cloneable 인터페이스](#cloneable-인터페이스)  
[- clone 메서드의 일반 규약](#clone-메서드의-일반-규약)  
[- clone 메서드 구현](#clone-메서드-구현)  
[- clone 메서드 재정의 시 주의사항](#clone-메서드-재정의-시-주의사항)  
[- 복사 생성자와 복사 팩터리](#복사-생성자와-복사-팩터리)

<br>

## clone 재정의는 주의해서 진행하라
- Cloneable은 복제해도 되는 클래스임을 명시하는 용도의 믹스인 인터페이스지만, 아쉽게도 의도한 목적을 제대로 이루지 못했다. 
  - `clone()` 메서드가 선언된 곳이 `Cloneable`이 아닌 `Object`이다. 
  - `clone()` 메서드의 접근 제한자가 `protected` 이다. 
  - `Cloneable`을 구현하는 것만으로는 접근이 허용된 clone 메서드를 제공한다는 보장이 없기 때문에 `clone()`를 호출할 수 없다.

<br>

### Cloneable 인터페이스
- Object의 protected 메서드인 clone의 **동작 방식을 결정한다.**
- Cloneable 을 구현한 클래스의 인터페이스에서 clone을 호출하면 그 객체의 필드들을 하나하나 복사한 객체를 반환한다.
- Cloneable 을 구현하지 않은 클래스에서 clone을 호출하는 경우 `CloneNotSupportedException`을 던진다.

<br>

### clone 메서드의 일반 규약
- '복사'의 정확한 듯은 그 객체를 구현한 클래스에 따라 다를 수 있다.
  - 아래의 명세가 일반적으로 참이지만, 필수는 아니다.

    ```java
    // 복제 객체가 원본 객체와 같지 않다.
    x.clone() != x  // true
    ```
    ```java
    // 복제 객체의 인스턴스와 원본 객체의 인스턴스가 같다.
    x.clone().getClass() == x.getClass()  // true
    ```
    ```java
    // 복제 객체와 원본 객체가 동등성을 만족한다.
    x.clone().equals(x)  // true
    ```
  - 관례상, 반환된 객체와 원본 객체는 독립적이다. 
  - 이를 만족하려면 super.clone() 으로 얻은 객체의 필드 중 하나 이상을 반환 전에 수정해야 할 수 있다.

<br>

### clone 메서드 구현
- 복제가 필요한 클래스에 Cloneable을 구현하고, 접근 제어자는 public으로, 반환 타입은 클래스 자신으로 변경한다.
- 불변 클래스는 굳이 clone 메서드를 제공하지 않는 것이 좋다.
- try-catch 블록으로 Object의 clone 메서드가 검사 예외(checked exception) 로 제공되는 것을 비검사 예외(unchecked exception) 로 처리하도록 한다. *(→ item 71)*
- 참조형 타입을 필드로 갖는다면, 단순히 super.clone() 을 구현해서는 안된다.
  - 원본이나 복제본 중 하나를 수정하면 다른 하나도 수정되어 불변식을 해치게 된다. - [예시](https://github.com/pageprologue/study-effective-java/blob/main/heejin/src/test/java/study/heejin/chapter3/Item13Test.java#LC21)
  - clone 메서드가 제대로 동작하려면 내부의 참조 타입까지 재귀적으로 복사를 해주어야 한다. - [예시](https://github.com/pageprologue/study-effective-java/blob/main/heejin/src/test/java/study/heejin/chapter3/Item13Test.java#LC43)
  - clone을 재귀적으로 호출하는 것만드로 충분한 복사가 되지 않는 경우도 있다. 
    - 복제본은 자신만의 버킷 배열을 갖지만, 이 배열은 원본과 같은 연결 리스트를 참조하여 원본과 복제본 모두 예기치 않게 동작할 가능성이 생긴다. - [예시](https://github.com/pageprologue/study-effective-java/blob/main/heejin/src/test/java/study/heejin/chapter3/Item13Test.java#LC64)
    - 이를 해결하려면, 각 버킷을 구성하는 연결 리스트를 복사해야 한다. - [예시](https://github.com/pageprologue/study-effective-java/blob/main/heejin/src/test/java/study/heejin/chapter3/Item13Test.java#LC85)
    - `HashTable.Entry`는 깊은 복사를 지원하도록 보강되었다.
  - super.clone을 호출하여 객체의 모든 필드를 초기 상태로 설정한 다음, 고수준 메서드들을 활용하여 복제하는 방법이 있다. 
    - 고수준 메서드를 활용하면 간단하긴 하지만, 저수준에서 바로 처리할 때보다는 느리다.
    - 또한, 필드 단위 객체 복사를 우회하기 때문에 Cloneable 아키텍처와 어울리지 않는 방식이기도 하다.

  #### <요약>
  - `Cloneable` 인터페이스를 구현하는 모든 클래스는 `clone`을 재정의한다.
  - 접근 제한자는 `public`으로 수정한다.
  - 반환타입은 클래스 자신으로 수정한다.
  - 가장 먼저 `super.clone`을 호출한 후 필요한 필드를 적절하게 수정한다.  
    \- 객체의 내부에 숨어있는 모든 가변 객체를 복사하고, 복제본이 가진 객체 참조 모두가 복사된 객체들을 가리키도록 한다.  
    \- 내부 복사는 주로 clone을 재귀적으로 호출해 구현하지만, 이 방식이 항상 최선은 아니다.
  - 기본 타입 필드와 불변 객체 참조만 갖는 클래스인 경우 어떤 필드도 수정할 필요가 없다.
    - 단, 일련번호나 고유 ID는 비록 기본 타입이나 불변일 지라도 수정해줘야 한다.

<br>

### clone 메서드 재정의 시 주의사항
- 생성자에서는 재정의될 수 있는 메서드를 호출하지 않아야 하는 것처럼 clone 메서드도 마찬가지이다. *(→ item 19)*
- clone 메서드는 CloneNotSupportedException을 던진다고 선언되어 있지만 재정의한 메서에서는 throws 절을 없애야 한다. 
  (검사 예외를 비검사예외로 수정해야 그 메서드를 사용하기에 편리하다.)
- 상속용 클래스는 Cloneable을 구현해서는 안된다.
- Cloneable을 구현한 스레드 안전 클래스를 작성할 때는 clone 메서드 역시 적절히 동기화해줘야 한다. *(→ item 78)*

<br>

### 복사 생성자와 복사 팩터리
- 복사 생성자란 단순히 자신과 같은 클래스의 인스턴스를 인수로 받는 생성자를 말한다.
  ```java
  // 복사 생성자
  public Yum(Yum yum) { ... };
  
  // 복사 팩터리
  public static Yum newInstance(Yum yum) { ... };
  ```
- 복사 생성자와 그 변형인 복사 팩터리는 Cloneable/clone 방식보다 나은 면이 많다.
  - 언어 모순적인 객체 생성 메커니즘을 사용하지 않는다.
  - 정상적인 final 필드 용법과 충돌하지 않는다.
  - 불필요한 검사 예외를 던지지 않는다.
  - 형변환이 필요하지 않다.
- 복사 생성자와 복사 팩터리는 해당 클래스가 구현한 '인터페이스' 타입의 인스턴스를 인수로 받을 수 있다.
  - 인터페이스 기반 복사 생성자와 복사 팩터리의 더 정확한 이름은 '변환 생성자'와 '변환 팩터리'다.

<br>

### 핵심 정리
- 새로운 인터페이스를 만들 때는 절대 Cloneable을 확장해서는 안되며, 새로운 클래스도 이를 구현해서는 안된다.
- final 클래스라면 Cloneable을 구현해도 위험이 크지 않지만, 성능 최적화 관점에서 검토한 후 문제가 없을 때만 드물게 허용해야 한다. *(→ item 67)*
- 복제 기능은 생성자와 팩터리를 이용하는게 최고라는 것이 기본 원칙이다. 
- 단, 배열만은 clone 메서드 방식이 가장 깔끔하다.


<br>

---
#### Reference

- [코드 재사용을 위한 Mixin](https://stonzeteam.github.io/%EC%BD%94%EB%93%9C-%EC%9E%AC%EC%82%AC%EC%9A%A9%EC%9D%84-%EC%9C%84%ED%95%9C-Mixin)


