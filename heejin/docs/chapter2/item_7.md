# 객체 생성과 파괴

[아이템 7. 다 쓴 객체 참조를 해제하라](#다-쓴-객체-참조를-해제하라)  
[- 스택](#스택)  
[- 캐시](#캐시)  
[- 리스너](#리스너)  

<br>

## 다 쓴 객체 참조를 해제하라
자바는 가비지 컬렉터가 있기 때문에 메모리를 직접 관리 하지 않아도 된다.  
하지만 메모리 관리에 더 이상 신경쓰지 않아도 된다고 오해할 수 있는데, 절대 사실이 아니다.


### 스택
- 다음과 같이 스택을 구현하는 코드에서 메모리 누수 문제가 숨어있다.

    ```java
    public Object memoryLeakPop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return elements[--size];
    }
    ```
  
  - 위의 코드에서는 스택에서 꺼내진 객체들을 가비지 컬렉터가 회수하지 않는다.
  - 왜냐하면, 위의 스택에서는 객체들이 다 쓴 참조를 여전히 가지고 있기 때문이다.


- 가비지 컬렉션 언어에서는 (의도하지 않게 객체를 살려두는) 메모리 누수를 찾기가 아주 까다롭다.
  - 해법은 간단하다. **다 쓴 객체의 참조를 null 처리(참조 해제)하면 된다.**
    ```java
    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object result = elements[--size];
        elements[size] = null; // 다 쓴 참조 해제
        return result;
    }
    ```
    
  - 다 쓴 참조를 해제하면 실수로 해당 참조를 사용하려고 할 때 `NullPointException`을 던지며 종료하기 때문에 프로그램 오류를 조기에 발견할 수도 있다. 
  - 하지만, 객체 참조를 null 처리하는 것 역시 바람직하지 않으므로 null 처리는 예외적인 경우여야 한다.
  - 다 쓴 객체 참조를 해제하는 가장 좋은 방법은 그 참조를 담은 변수를 **유효 범위(scope) 밖으로 밀어내는 것**이다. *(→ 아이템 57)*
  - null 처리는 스택처럼 자기 메모리를 직접 관리 하는 경우에 사용해야 한다.
  - 스택은 객체 자체가 아니라 **객체 참조**를 담는 elements 배열을 관리한다. 배열의 **활성 영역**에 속한 원소들이 사용되고, **비활성 영역**은 쓰이지 않는다. **문제는 가비지 컬렉터는 이 사실을 알 길이 없다.**

  **→ 일반적으로 자기 메모리를 직접 관리하는 클래스라면 항상 메모리 누수에 주의해야 한다.**


### 캐시
캐시 역시 메모리 누수를 일으키는 주범이다.
- 객체 참조를 캐시에 넣고 나서, 이 사실을 까맣게 잊은 채 그 객체를 다 쓴 뒤로도 한참을 그냥 놔두는 일을 자주 접할 수 있다.

  - `WeakHashMap`
    - 캐시 내부에서 키(key)를 참조하는 동안만(값이 아니다) 엔트리가 살아 있는 캐시가 필요한 상황이라면 `WeakHashMap`을 사용해 캐시를 만들자.
  - 캐시의 유효 기간을 정확히 정의하기 어려울 때는 시간이 지날수록 엔트리를 청소해줘야 한다.  
    - `ScheduledThreadPoolExecutor` 같은 백그라운드 스레드를 활용하거나 캐시에 새 엔트리를 추가할 때 부수 작업으로 수행하는 방법이 있다.
    - `LinkedHashMap`은 `removeEldestEntry` 메서드를 써서 새 엔트리를 추가할 때 부수 작업을 수행하여 캐시를 청소한다. [(→ 예시)](https://github.com/pageprologue/study-effective-java/blob/main/heejin/src/test/java/study/heejin/chapter2/Item7Test.java#LC50)


### 리스너
리스너(listener) 혹은 콜백(callback)이라 부르는 것도 메모리 누수의 주범이다.
- 클라이언트가 콜백을 등록만 하고 명확히 해지하지 않는다면, 뭔가 조치해주지 않는 한 콜백은 계속 쌓여갈 것이다.
- 이럴 때 콜백을 **약한 참조**(weak reference)로 저장하면 가비지 컬렉터가 즉시 수거해간다.

<br>

---
#### Reference

- [LinkedHashMap removeEldestEntry() Method in Java](https://www.geeksforgeeks.org/linkedhashmap-removeeldestentry-method-in-java)


