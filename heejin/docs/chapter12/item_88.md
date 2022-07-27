# 직렬화

[아이템 88. readObject 메서드는 방어적으로 작성하라](#readobject-메서드는-방어적으로-작성하라)

[- 정리](#정리)

<br>

## readObject 메서드는 방어적으로 작성하라

- 방어적 복사를 사용하는 불변 클래스를 직렬화하면, 해당 클래스의 불변식을 보장하지 못하게 된다.
- **문제는 readObject 메서드가 실질적으로 또 다른 public 생성자이기 때문이다.**
    - 따라서 다른 생성자와 똑같은 수준으로 주의를 기울여야 한다.
    - 메서드의 매개변수가 유효한지 검사해야 하고, 필요하다면 매개변수를 방어적으로 복사해야 한다.

#### 유효성 검사

- 불변식을 깨뜨릴 의도로 생성한 바이트 스트림을 역직렬화 하면 불변식이 깨질 수 있다.
    - [허용되지 않는 Period 인스턴스를 생성할 수 있다.](../../src/main/java/study/heejin/chapter12/item88/BogusPeriod.java)
    - 이 문제를 고치려면 `Period`의 `readObject` 메서드가 `defaultReadObject`를 호출한 다음 역직렬화 된 객체가 유효한지 검사해야 한다.
    - 아래와 같이 `readObject` 메서드에 유효성 검사를 추가한다.
      ```java
      private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
          s.defaultReadObject();
  
          // 불변식을 만족하는지 검사한다.
          if (start.compareTo(end) > 0) {
              throw new IllegalArgumentException(start + "가 " + end + "보다 늦다.");
          }
      }
      ```

#### 방어적 복사

- **객체를 역직렬화 할 때는 클라이언트가 소유해서는 안되는 객체 참조를 갖는 필드를 모두 방어적으로 복사해야 한다.**
    - 위와 같이 `readObject` 메서드를 추가하더라도 가변 공격을 통해 불변식이 깨질 수 있다.
    - [필드 참조를 추가하면 가변 Period 인스턴스를 생성할 수 있다.](../../src/main/java/study/heejin/chapter12/item88/MutablePeriod.java)
    - 가변 공격의 문제를 방어적 복사를 통해 해결할 수 있다.

      ```java
      private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
          s.defaultReadObject();
          
          // 가변 요소들을 방어적으로 복사한다.
          start = new Date(start.getTime());
          end = new Date(end.getTime());
      
          // 불변식을 만족하는지 검사한다.
          if (start.compareTo(end) > 0) {
              throw new IllegalArgumentException(start + "가 " + end + "보다 늦다.");
          }
      }
      ```
    - 방어적 복사를 유효성 검사보다 앞서 수행하며, `Date`의 `clone` 메서드는 사용하지 않았음에 주목하자.

#### 기본 readObject 메서드를 사용해도 되는 경우

- `transient` 필드를 제외한 모든 필드의 값을 매개변수로 받아 유효성 검사 없이 필드에 대힙하는 `public` 생성자를 추가해도 괜찮다면, 기본 `readObject` 메서드를 사용해도 된다.
- 그렇지 않으면, 커스텀 `readObject` 메서드를 만들어서 유효성 검사와 방어적 복사를 수행해야 한다.
- 혹은 직렬화 프록시 패턴을 사용하는 방법도 있다. _(→ item90)_

### 정리

- `readObject` 메서드를 작성할 때는 언제나 `public` 생성자를 작성하는 자세로 임해야 한다.

- **안전한 `readObject`를 작성하는 지침**
    - `private`이어야 하는 객체 참조 필드는 각 필드가 가리키는 객체를 방어적으로 복사하라. 불변 클래스 내의 가변 요소가 여기 속한다.
    - 방어적 복사 다음에는 모든 불변식을 검사하여 불변식에 어긋나면 `InvalidObjectException`을 던진다.
    - 역직렬화 후 객체 그래프 전체의 유효성을 검사해야 한다면 `ObjectInputValidation` 인터페이스를 사용하라.
    - 직접적이든 간접적이든, 재정의할 수 있는 메서드는 호출하지 말자.

<br>
