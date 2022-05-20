# 메서드

[아이템 52. 다중정의는 신중히 사용하라](#다중정의는-신중히-사용하라)  
[- 다중정의의 문제점](#다중정의의-문제점)  
[- 다중정의 사용시 주의할 점](#다중정의-사용시-주의할-점)

<br>

## 다중정의는 신중히 사용하라
- 프로그래밍 언어가 다중정의를 허용한다고 해서 다중정의를 꼭 활용하라는 뜻은 아니다.
- 일반적으로 매개변수 수가 같을 때는 다중정의를 피하는게 좋다.

### 다중정의의 문제점
- 다중정의된 메서드는 컴파일타임에 어떤 메서드를 호출할지 정해진다.
  - 다중정의된 메서드는 매개변수의 컴파일타임 타입에 의해 이뤄진다. 
  - [다중정의 메서드 예제](../../src/main/java/study/heejin/chapter8/item52/CollectionClassifier.java)
- 반면, 재정의한 메서드는 런타임에 어떤 메서드를 호출할지 정해진다.
  - 메서드 재정의란 상위 클래스가 정의한 것과 똑같은 시그니처의 메서드를 하위 클래스에서 다시 정의한 것을 말한다.
  - [재정의 메서드 예제](../../src/main/java/study/heejin/chapter8/item52/Overriding.java)
  

### 다중정의 사용시 주의할 점

#### 다중정의가 혼동을 일으키는 상황을 피해야 한다.
- 안전하고 보수적으로 가려면 매개변수 수가 같은 다중정의는 만들지 말자.  
- 가변인수를 사용하는 메서드라면 다중정의를 아예 하지 말아야 한다. _(→ item53)_
- **다중정의 대신 메서드 이름을 다르게 지어주는 길도 항상 열려있다.**
  - `ObjectOutputStream`은 write 메서드를 다중정의가 아닌 메서드에 다른 이름을 지어주는 방식을 사용했다.
  - `writeBoolean(boolean)`, `writeInt(int)`, `writeLong(long)`
  - 이런 방식은 `ObjectInputStream`과 짝을 맞추기도 좋다.


#### 생성자는 이름을 다르게 지을 수 없으니 두번째 생성자부터는 무조건 다중정의가 된다.
- 이때는 정적 팩터리라는 대안을 활용할 수 있는 경우가 많다. _(→ item1)_


#### 매개변수가 같은 다중정의 메서드가 많더라도, 그 중 어느것이 동작할지 명확히 구분되어야 한다.
- 매개변수가 같은 다중정의 메서드가 명확히 구분되려면 매개변수 중 하나 이상이 근본적으로 달라야 한다.
- 근본적으로 다르다는 것은 null이 아닌 두 타입의 값을 어느 쪽으로든 형변환을 할 수 없다는 것이다.


#### 자바 5에서 도입된 오토박싱과 제네릭의 영향으로 다중정의 메서드 선택시 주의해야 한다.
- [List 인터페이스의 remove 예제](../../src/main/java/study/heejin/chapter8/item52/SetList.java)
  - 위의 예에서 `List<E>` 인터페이스가 `remove(Object)`와 `remove(int)`를 다중정의 했기 때문에 `Integer로` 형변환을 해줘야한다.
- 제네릭이 도입되기 전인 자바 4까지의 List에서는 Object와 int가 근복적으로 달라서 문제가 없었다.
- 그런데 제네릭과 오토박싱이 등장하면서 두 메서드의 매개변수 타입이 근본적으로 다르지 않게 되었다.


#### 자바 8에서 도입한 람다와 메서드 참조 역시 다중정의 메서드 선택시 주의해야 한다.
  - [부정확한 메서드 참조 예제](../../src/main/java/study/heejin/chapter8/item52/SetList.java)
    - 위의 예에서 `submit` 다중정의 메서드 중에는 `Callable<T>`를 받는 메서드도 있기 때문에 컴파일 오류가 발생한다.
    - `println`이 `void`를 리턴하기 때문에 `Runnable`로 추론해주리라 기대하지만 다중정의 해소는 이렇게 동작하지 않는다.
  - 참조된 메서드와 호출한 메서드 양쪽 다 다중정의 되어 있으면, 다중정의 해소 알고리즘이 동작하지 않는다.
  - **메서드를 다중정의할 때, 서로 다른 함수형 인터페이스라도 같은 위치의 인수로 받아서는 안된다.**
    - 이 말은 서로 다른 함수형 인터페이스라도 서로 근본적으로 다르지 않다는 뜻이다.


#### 이미 만들어진 클래스에 같은 기능을 하는 다중정의 메서드가 추가되어야 한다면 포워드 방식을 사용ㅎ나다.
  - 자바 5에서 StringBuffer, StringBuilder, String, CharBuffer 등 비슷한 부류의 타입을 위한 공통 인터페이스로 CharSequence가 등장하게 되어 다중정의 하게 되었다.
  - 이렇게 기능이 동일한 경우에는 일반적으로, 더 특수한 다중정의 메서드에서 덜 특수한 다중정의 메서드로 포워드 시킨다.
  - String 클래스의 contentEquals(StringBuffer)와 contentEquals(CharSequence)
    ```java
    public boolean contentEquals(StringBuffer sb) {
        return contentEquals((CharSequence)sb);
    }
    ```


  
<br>