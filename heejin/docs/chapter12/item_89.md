# 직렬화

[아이템 90. 직렬화된 인스턴스 대신 직렬화 프록시 사용을 검토하라](#직렬화된-인스턴스-대신-직렬화-프록시-사용을-검토하라)

[- 직렬화 프록시 패턴](#직렬화-프록시-패턴)  
[- 정리](#정리)

<br>

## 직렬화된 인스턴스 대신 직렬화 프록시 사용을 검토하라

- Serializable을 구현하기로 결정한 순간 언어의 정상 메커니즘인 생성자 이외의 방법으로 인스턴스를 생성할 수 있게 된다.
- 버그와 보안 문제가 일어날 가능성이 커진다.
- 하지만 직렬화 프록시 패턴을 사용하면 이 위험을 크게 줄일 수 있다.

### 직렬화 프록시 패턴

- 바깥 클래스와 직렬화 프록시 모두 `Serializable을` 구현한다고 선언한다.
- 바깥 클래스의 논리적 상태를 정밀하게 표현하는 중첩 클래스를 설계하고, `private static`으로 선언한다.
- 중첩 클래스의 생성자는 단 하나여야 하며, 바깥 클래스를 매개변수로 받아야 한다.
    - 이 생성자는 단순히 인수로 넘어온 인스턴스의 데이터를 복사한다.
    - 유효성 검사나 방어적 복사도 필요없다.
- [Period 직렬화 프록시 예제](../../src/main/java/study/heejin/chapter12/item90/Period.java)
    ```java
    // 직렬화 프록시 패턴용 writeReplace 메서드
    private Object writeReplace() {
        return new SerializationProxy(this);
    }
  
    // 직렬화 프록시 패턴용 readObject 메서드
    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("프록시가 필요합니다.");
    }
  
    // 직렬화 프록시 패턴용 readResolve 메서드
    private Object readResolve() {
        return new Period(start, end);
    }
    ```
    - `writeReplace` 메서드는 자바의 직렬화 시스템이 바깥 클래스의 인스턴스 대신 SerializationProxy의 인스턴스를 반환하게 하는 역할은 한다.
    - `readObject` 메서드는 불변식을 훼손하려는 시도를 막아준다.
    - `readResolve` 메서드는 바깥 클래스와 논리적으로 동일한 인스턴스를 반환한다. 이 메서드는 역직렬화 시에 직렬화 프록시를 다시 바깥 클래스의 인스턴스로 변환해준다.

- 직렬화 프록시 패턴은 일반 인스턴스를 만들 때와 동일한 생성자, 정적 팩터리, 혹은 다른 메서드를 사용해 역직렬화된 인스턴스를 생성하는 것이다.
- 따라서 역직렬화된 인스턴스가 해당 클래스의 불변식을 만족하는지 검사할 또 다른 수단을 강구하지 않아도 된다.

#### 직렬화 프록시의 장점

- 가짜 바이트 스트림 공격과 내부 탈취 공격을 프록시 수준에서 차단해준다.
- 직렬화 프록시를 사용하면 필드를 final로 선언해도 되므로 클래스를 진정한 불변으로 만들 수 있다.
- 또한, 어떤 필드가 공격의 대상이 될지 고민하지 않아도 되며, 역직렬화할 때 유효성 검사를 수행하지 않아도 된다.
- 직렬화 프록시 패턴은 역직렬화한 인스턴스와 원래의 직렬화된 인스턴스의 클래스가 달라도 정상 작동한다.
    - 열거 타입의 원소가 64개 이하면 RegularEnumSet을 사용하고, 그보다 크면 JumboEnumSet을 사용한다.
    ```java
    private static class SerializationProxy<E extends Enum<E>> implements java.io.Serializable {
          private static final Enum<?>[] ZERO_LENGTH_ENUM_ARRAY = new Enum<?>[0];

          private final Class<E> elementType;

          private final Enum<?>[] elements;

          SerializationProxy(EnumSet<E> set) {
              elementType = set.elementType;
              elements = set.toArray(ZERO_LENGTH_ENUM_ARRAY);
          }

          @SuppressWarnings("unchecked")
          private Object readResolve() {
              EnumSet<E> result = EnumSet.noneOf(elementType);
              for (Enum<?> e : elements)
                  result.add((E)e);
              return result;
          }

          private static final long serialVersionUID = 362491234563181265L;
      }
    ```

#### 직렬화 프록시의 한계

- 클라이언트가 멋대로 확장할 수 있는 클래스에는 적용할 수 없다.
- 객체 그래프에 순환이 있는 클래스에도 적용할 수 없다.
  - 이런 객체의 메서드를 직렬화 프록시의 readResolve 안에서 호출하려 하면 ClassCastException이 발생할 것이다.
  - 직렬화 프록시만 가졌을 뿐 실제 객체는 아직 만들어진 것이 아니기 때문이다.
- 직렬화 프록시 패턴이 주는 강력함과 안정성에도 대가는 따른다.
  - 방어적 복사 보다 속도가 14% 느려졌다.

### 정리

- 제 3자가 확장할 수 없는 클래스라면 가능한 한 직렬화 프록시 패턴을 사용하자.
- 이 패턴은 불변식을 안정적으로 직렬화해주는 가장 쉬운 방법일 것이다.

<br>
