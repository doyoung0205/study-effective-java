# 직렬화

[아이템 87. 커스텀 직렬화 형태를 고려해보라](#커스텀-직렬화-형태를-고려해보라)

[- 커스텀 직렬화](#커스텀-직렬화)  
[- 정리](#정리)

<br>

## 커스텀 직렬화 형태를 고려해보라

- 객체의 물리적 표현과 논리적 내용이 같다면 기본 직렬화 형태라도 무방하다.
    - 예컨데, 성명은 놀리적으로 이름, 성, 중간이름이라는 문자열로 구성되며, 인스턴스 필드들은 이 논리적 구성요소를 정확하게 반영한다.
    ```java
    public class Name implements Serializable {
        /**
         * 성. null이 아니어야 함.
         * @serial 
         */
        private final String lastName;
        /**
         * 이름. null이 아니어야 함.
         * @serial 
         */
        private final String firstName;
        /**
         * 중간이름. 중간이름이 없다면 null.
         * @serial 
         */
        private final String middleName;
            
        ... // 나머지 코드는 생략
    }
    ```
    - `Name`의 필드는 모두 `private` 임에도 문서화 주석이 달려있다.
    - 이 필드들은 결국 클래스의 직렬화 형태에 포함되는 공개 API에 속하며, 공개 API는 모두 문서화해야 하기 때문이다.

- 반면, 논리적 표현과 물리적 표현의 차이가 있는 클래스는 직렬화 형태에 적합하지 않다.
    - 예컨데, 아래의 `StringList` 클래스는 논리적으로 일련의 문자열을 표현하지만, 물리적으로는 문자열들을 이중 연결 리스트로 연결했다.
    ```java
    public final class StringList implements Serializable {
        private int size = 0;
        private Entry head = null;
        
        private static class Entry implements Serializable {
            String data;
            Entry next;
            Entry previous;
        }
    }
    ... // 나머지 코드는 생략
    ```

- **기본 직렬화 형태가 적합하다고 결정했더라도 불변식 보장과 보안을 위해 `readObject` 메서드를 제공해야 할 때가 많다.**
    - `Name` 클래스의 경우에는 `readObject` 메서드가 `lastName`, `firstName` 필드가 `null`이 아님을 보장해야 한다. _(→ item88, 90)_

#### 객체의 물리적 표현과 논리적 표현의 차이가 클 때 기본 직렬화 형태 사용의 문제점

1. 공개 API가 현재의 내부 표현 방식에 영구히 묶인다.
2. 너무 많은 공간을 차지할 수 있다.
3. 시간이 너무 많이 걸릴 수 있다.
4. 스택 오버플로우를 일으킬 수 있다.

### 커스텀 직렬화

#### `writeObject`와 `readObject`

  - `writeObject`와 `readObject`가 직렬화 형태를 처리한다.
  - [StringList 클래스를 합리적으로 직렬화 한 예시](../../src/main/java/study/heejin/chapter12/item87/StringListCustom.java)
    - `transient` 한정자는 해당 인스턴스 필드가 기본 직렬화 형태에 포함되지 않는다는 의미이다.
    - StringList의 필드가 `transient`로 선언되어도, `writeObject`와 `readObject는` 각각 가장 먼저 `defaultWriteObject`
      와 `defaultReadObject`를 호출한다.
    - StringList 클래스를 커스텀 직렬화 형태로 변경하면, 기본 직렬화를 사용할 때 보다 대략 절반 정도의 공간을 차지하며, 두 배 정도 빠르게 수행된다.

#### 불변식을 보장할 수 없는 직렬화

- 해시테이블은 기본 직렬화 형태를 사용하면 심각한 버그로 이어질 수 있다.
    - 어떤 엔트리를 어떤 버킷에 담을지는 키에서 구한 해시코드가 결정하는데, 그 계산 방식은 구현에 따라 달라질 수 있기 때문이다.

#### transient

- 기본 직렬화를 사용하면 `transient` 필드들은 역직렬화될 때 기본값으로 초기화 된다.
    - 객체 참조 필드는 null, 숫자 기본 타입 필드는 0, boolean 파입은 false로 초기화된다.

- 해당 객체의 논리적 상태와 무관한 필드라고 확신할 때만 `transient` 한정자를 생략해야 한다.
    - `defaultWriteObject` 메서드를 호출하면 `transient`로 선언하지 않은 모든 인스턴스 필드가 직렬화 된다.
    - **커스텀 직렬화 형태를 사용한다면, 대부분의 (혹은 모든) 인스턴스 필드를 `transient`로 선언해야 한다.**

#### 동기화

- 객체의 전체 상태를 읽는 메서드에 적용해야 하는 동기화 메커니즘은 직렬화에도 적용해야 한다.
    - 예컨데, 모든 메서드를 `synchronized`로 선언하여 스레드 안전하게 만든 객체에서 기본 직렬화를 사용하려면 `writeObject`도 `synchronized`로 선언해야 한다.
    ```java
    private synchronized void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
    }
    ```

#### 직렬 버전 UID

- **어떤 직렬화 형태를 택하든 직렬화 가능 클래스 모두에 직렬 버전 UID를 명시적으로 부여하자.**
    - 직렬 버전 UID가 꼭 고유할 필요는 없다.
    - 직렬 버전 UID가 없는 기존 클래스를 구버전으로 직렬화된 인스턴스와 호환성을 유지한 채 수정하고 싶다면, 구버전에서 사용한 자동 생성된 값을 그대로 사용해야 한다.
    - 이 값은 직렬화된 인스턴스가 존재하는 구버전 클래스를 serialver 유틸리티에 입력으로 주어 실행하면 얻을 수 있다.
    - 구버전으로 직렬화된 인스턴스들과의 호환성을 끊으려는 경우를 제외하고는 직렬 버전 UID를 절대 수정하지 말자.

### 정리

- 기본 직렬화 형태는 객체를 직렬화한 결과가 해당 객체의 논리적 표현에 부합할 때만 사용하고, 그렇지 않으면 객체를 적절히 설명하는 커스텀 직렬화 형태를 고안하라.
- 직렬화 형태도 공개 메서드를 설계할 때에 준하는 시간을 들여 설계해야 한다.

<br>
