# 객체 생성과 파괴

[아이템 8. finalizer와 cleaner 사용을 피하라](#finalizer와-cleaner-사용을-피하라)  
[- finalizer와 cleaner의 문제점](#finalizer와-cleaner의-문제점)  
[- AutoCloseable 사용](#AutoCloseable-사용)  
[- finalizer와 cleaner를 사용하려는 경우](#finalizer와-cleaner를-사용하려는-경우)

<br>

## finalizer와 cleaner 사용을 피하라

자바는 두 가지 **객체 소멸자**를 제공한다.

- `finalizer`는 예측할 수 없고, 상황에 따라 위험할 수 있어 일반적으로 불필요하다.
- `cleaner`는 `finalizer`보다는 덜 위험하지만, 여전히 예측할 수 없고, 느리고, 일반적으로 불필요하다.


- 자바의 finalizer와 cleaner는 C++의 파괴자(destructor)와는 다른 개념이다.
    - C++ 에서의 파과자는 특정 객체와 관련된 자원을 회수하는 보편적인 방법이다.
    - 자바에서는 접근할 수 없게 된 객체를 회수하는 역할을 가비지 컬렉터가 담당한다.
    - C++의 파괴자는 비메모리 자원을 회수하는 용도로 쓰인다.
    - 자바에서는 `try-with-resource`와 `try-finally`를 사용해 해결한다.


### finalizer와 cleaner의 문제점

- **`finalizer`와 `cleaner`는 즉시 수행된다는 보장이 없다.**  
  **→ 즉, `finalizer`와 `cleaner`로 제때 실행되어야 하는 작업은 절대 할 수 없다.**

    - `finalizer`나 `cleaner`를 얼마나 신속히 수행할지는 전적으로 가비지 컬렉터 알고리즘에 달렸으며, 이는 가비지 컬렉터 구현마다 천차만별이다.
    - `finalizer` 스레드는 다른 애플리케이션 스레드보다 우선순위가 낮아서 자원 회수가 제멋대로 지연될 수 있다.
    - `cleaner`는 자신을 수행할 스레드를 제어할 수 있다는 면에서 조금 낫다. 하지만 여전히 백그라운드에서 수행되며 가비지 컬렉터의 통제하에 있으니 즉각 수행되리라는 보장은 없다.


- **`finalizer`와 `cleaner`의 수행 시점뿐 아니라 수행 여부조차 보장하지 않는다.**
    - 객체에 포함된 종료 작업을 전혀 수행하지 못한 채 프로그램이 중단 될 수도 있다.  
      **→ 상태를 영구적으로 수정하는 작업에서는 절대 `finalizer`나 `cleaner`에 의존해서는 안된다.**  
      (예를 들어, 데이터베이스 같은 공유 자원의 영구 락(lock) 해제를 `finalizer`나 `cleaner`에 맡겨 놓으면 분산 시스템 전체가 서서히 멈출 것이다.)
    - `finalizer` 동작 중 발생한 예외는 무시되며, 처리할 작업이 남아있더라도 그 순간 종료된다.  
      이처럼 훼손된 객체를 사용하려 한다면, 보통의 경우엔 잡지 못한 예외가 스레드를 중단시키고 스택 추적 내역을 출력하겠지만, `finalizer`에서 발생한 예외는 경고조차 출력하지 않는다.
    - 그나마 `cleaner`를 사용하는 라이브러리는 자신의 스레드를 통제하기 때문에 이러한 문제가 발생하지 않는다.


- `finalizer`와 `cleaner`는 심각한 성능 문제도 동반한다.


- `finalizer`를 사용한 클래스는 `finalizer` 공격에 노출되어 심각한 보안 문제를 일으킬 수도 있다.
    - `finalizer` 공격 원리는 간단한다.  
      생성자나 직렬화 과정에서 예외가 발생하면, 이 생성되다 만 객체에서 악의적인 하위 클래스의 `finalizer`가 수행될 수 있게 된다.
    - 객체 생성을 막으려면 생성자에서 예외를 던지는 것만으로 충분하지만, `finalizer`가 있다면 그렇지도 않다.
    - final 클래스들은 그 누구도 하위 클래스를 만들 수 없으니 이 공격에서 안전하다.
    - final이 아닌 클래스를 `finalizer` 공격으로부터 방어하려면 아무 일도 하지 않는 `finalizer` 메서드를 만들고 final로 선언하자.


### AutoCloseable 사용

- 종료해야 할 자원(파일이나 스레드 등)을 담고 있는 객체의 클래스에서 `finalizer`나 `cleaner` 대신 `AutoCloseable`을 사용하면 된다.
    - `AutoCloseable`을 구현해주고, 클라이언트에서 인스턴스를 다 쓰고 나면 `colse` 메서드를 호출하면 된다.
    - 일반적으로 예외가 발생해도 제대로 종료되도록 `try-with-resource`를 사용해야 한다.
    - 또한, 각 인스턴스는 자신이 닫혔는지를 추적하는 것이 좋다.  
      `close` 메서드에서 이 객체는 더 이상 유효하지 않음을 필드에 기록하고, 다른 메서드는 이 필드를 검사해서 객체가 닫힌 후에 불렸다면 `IllefalStateExcaption`을 던지는 것이다.
    
    <br>
  
    ```java
    private final State state; // 방의 상태. cleanable과 공유
    private final Cleaner.Cleanable cleanable;
    
    public Room(int numberOfJunkPiles) {
        this.state = new State(numberOfJunkPiles);
        this.cleanable = cleaner.register(this, state);
    }
    
    @Override
    public void close() {
        cleanable.clean();
    }
    ```

    - `State` 인스턴스는 절대로 `Room` 인스턴스를 참조해서는 안된다. `Room` 인스턴스를 참조할 경우 **순환참조**가 생겨 가비지 컬렉터가 `Room` 인스턴스를 회수해갈 기회가 오지 않는다.
    - 클라이언트가 모든 Room 생성을 `try-catch-resource` 블록으로 감쌌다면 자동 청소는 전혀 필요하지 않다.
    
    <br>
    
    ```java
    public class Adult {
        public void cleanRoom(int numberOfJunkPiles) {
            try (Room myRoom = new Room(numberOfJunkPiles)) {
                System.out.println("Adult 방 청소 완료!");
            }
        }
    }
    ```

    ```java
    public class Teenager {
        public void cleanRoom(int numberOfJunkPiles) {
            new Room(numberOfJunkPiles);
            System.out.println("Teenager 방 청소 완료!");
        }
    }
    ```
    - `Adult` 클래스의 `cleanRoom` 메서드를 호출하면 `Cleaner`의 `clean` 메서드가 호출된다.
    - `Teenager` 클래스의 `cleanRoom` 메서드를 호출하면 `Cleaner`의 `clean` 메서드가 호출되지 않는다. `Cleaner`가 언제 호출될지 예상할 수 없다.


### finalizer와 cleaner를 사용하려는 경우  

(1) 자원의 소유자가 close 메서드를 호출하지 않는 것에 대한 안전망 역할
  - 자바 라이브러리의 일부 클래스는 안전망 역할의 `finalizer`를 제공한다.  
    `FileInputStream`, `FileOutputStream`, `ThreadPoolExecutor`가 대표적이다.
  - 이런 안전망 역할의 `finalizer`를 작성할 때는 그만한 값어치가 있는지 심사숙고해야 한다.


(2) 네이티브 피어와 연결된 객체에서 사용하는 경우
  - 네이티브 피어란 일반 자바 객체가 네이티브 메서드를 통해 기능을 위임한 네이티브 객체를 말한다.
  - 네이티브 피어는 자바 객체가 아니니 가비지 컬렉터는 그 존재를 알지 못한다.
  - 단, 성능 저하를 감당할 수 있고, 네이티브 피어가 심각한 자원을 가지고 있지 않을 때에만 해당된다.

<br>

---

#### Reference

- [LinkedHashMap removeEldestEntry() Method in Java](https://www.geeksforgeeks.org/linkedhashmap-removeeldestentry-method-in-java)

