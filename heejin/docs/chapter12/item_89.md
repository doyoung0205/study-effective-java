# 직렬화

[아이템 89. 인스턴스 수를 통제해야 한다면 readResolve 보다는 열거 타입을 사용하라](#인스턴스-수를-통제해야-한다면-readresolve-보다는-열거-타입을-사용하라)

[- 정리](#정리)

<br>

## 인스턴스 수를 통제해야 한다면 readResolve 보다는 열거 타입을 사용하라

- 싱글톤 패턴은 바깥에서 생성자를 호출하지 못하게 막는 방식으로 인스턴스가 오직 하나만 만들어짐을 보장한다. _(→ item3)_
    ```java
    public class Elvis {
        public static final Elvis INSTANCE = new Elvis();
        private Elvis() { ... }
    
        public void leaveTheBuilding() { ... }
    }
    ```
    - 이 클래스에 `implements Serializable`을 추가하는 순간 더 이상 싱글톤이 아니게 된다.
    - 기본 직렬화를 쓰지 않고, 커스텀 `readObject`를 제공하더라도 이 클래스가 초기화될 때 만들어진 인스턴스와는 다른 인스턴스를 반환하게 된다.

- `readResolve` 기능을 이용하면 readObject가 만들어낸 인스턴스를 다른 것으로 대체할 수 있다.
    - 이때 새로 생성된 객체의 참조는 유지하지 않으므로 바로 가비지 컬렌션 대상이 된다.

    ```java
    private Object readResolve() {
        return INSTANCE;
    }
    ```
    - 이 메서드는 역직렬화한 객체는 무시하고 클래스 초기화 때 만들어진 Elvis 인스턴스를 반환한다.
    
    
- **`readResolve`를 인스턴스 통제 목적으로 사용한다면 객체 참조 타입 인스턴스 필드는 모두 `transient`로 선언해야 한다.**
    - 그렇지 않으면 `readResolve` 메서드가 실행되기 전에 잘 조작된 스트림을 써서 해당 참조 필드의 내용이 역직렬화되는 시점에 인스턴스의 참조를 훔쳐올 수 있다.

- `readResolve` 메서드를 사용하는 대신 열거 타입을 사용할 수도 있다.
    - **직렬화 가능한 인스턴스 통제 클래스를 열거 타입을 이용해 구현하면 선언한 상수 외의 다른 객체는 존재하지 않음을 자바가 보장한다.**
    ```java
    public enum Elvis {
        INSTANCE;
    
        private String[] favoriteSongs = { "Hound Dog", "Heartbreak Hotel" };
    
        public void printFavorites() {
            System.out.println(Arrays.toString(favoriteSongs));
        }
    }
    ```


### 정리

- 불변식을 지키기 위해 인스턴스를 통제해야 한다면 가능한 한 열거 타입을 사용하자.
- 직렬화와 인스턴스 통제가 모두 필요하다면 readResolve 메서드를 추가하고, 모든 참조 타입 인스턴스 필드를 transient로 선언해야 한다.


<br>
