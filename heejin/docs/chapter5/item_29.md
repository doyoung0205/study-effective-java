# 제네릭

[아이템 29. 이왕이면 제네릭 타입으로 만들어라](#이왕이면-제네릭-타입으로-만들어라)
[- 배열을 실체화 불가 타입으로 만드는 방법](#배열을-실체화-불가-타입으로-만드는-방법)
[- Object 기반의 Stack 클래스를 제네릭으로 만들기](#object-기반의-stack-클래스를-제네릭으로-만들기)
[- 제네릭 타입 매개변수](#제네릭-타입-매개변수)
[- 정리](#정리)

<br>

## 이왕이면 제네릭 타입으로 만들어라
제네릭 타입과 메서드를 사용하는 일은 일반적으로 쉬운 편이지만, 제네릭 타입을 새로 만드는 일은 조금 어렵다. 하지만 배워두면 그만한 값어치는 충분히 한다.

### 배열을 실체화 불가 타입으로 만드는 방법
1. 클래스 선언에 타입 매개변수를 추가
   ```java
   public class Stack<E> {
       ...
   }
   ```

2. 메서드 리턴 타임과 파라마터에 적절한 타입 매개변수 추가
    ```java
   public class Stack<E> {
       private final List<T> choiceList;
    
       public Chooser(Collection<T> choices) {
           choiceList = new ArrayList<>(choices);
       }
    
       public T choose() {
           Random rnd = ThreadLocalRandom.current();
           return choiceList.get(rnd.nextInt(choiceList.size()));
       }
   }
   ```


### Object 기반의 Stack 클래스를 제네릭으로 만들기
1. 제네릭 배열 생성을 금지하는 제약을 우회하는 방법
   ```java
   @SuppressWarnings("unchecked")
    public Stack() {
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }
   ```
   - Object 배열을 생성한 다음, 제네릭 배열로 형변환한다.
   - 형변환을 배열 생성시 한 번만 해주면 된다.


2. 필드의 타입을 E[]에서 Object[]로 바꾸는 방법
   ```java
   public Stack() {
       elements = new Object[DEFAULT_INITIAL_CAPACITY];
   }
   ```
   - 이렇게 하면 배열이 반환한 원소를 E로 형변환하면 경고가 발생한다.
   ```java
   public E pop() {
       if (size == 0)
           throw new EmptyStackException();

       // push에서 E 타입만 허용하므로 이 형변환은 안전하다.
       @SuppressWarnings("unchecked") E result = (E) elements[--size];

       elements[size] = null; // 다 쓴 참조 해제
       return result;
   }
   ```
   - 형변환을 배열에서 원소를 읽을 때마다 해줘야 한다.


- 현업에서는 첫 번째 방식을 더 선호하며 자주 사용한다.
- 하지만 (E가 가 Object가 아닌 한) 배열의 런타임 타입이 컴파일타임 타입과 달라 힙 오염을 일으킨다. *(→ item 32)*


### 제네릭 타입 매개변수
- 제네릭 타입 안에서 리스트를 사용하는 게 항상 가능하지도, 꼭 더 좋은 것도 아니다.
  - 자바가 리스트를 기본 타입으로 제공하지 않으므로 `ArrayList` 같은 제네릭 타입도 결국은 기본 타입인 배열을 사용해 구현해야 한다.
  - `HashMap` 같은 제네릭 타입은 성능을 높일 목적으로 배열을 사용하기도 한다.

- 제네릭 타입은 타입 매개변수에 아무런 제약을 두지 않는다.
  - 기본 타입이 아니라면 어떤 참조 타입으로도 제네릭 타입을 만들 수 있다. 
  -` Stack<Object>`, `Stack<int[]>`, `Stack<List<String>>`, `Stack` 모두 가능한 것이다.

- 타입 매개변수에 제약을 두는 제네릭 타입도 있다.
  - 예컨데, `java.util.concurrent.DelayQueue` 는 다음처럼 선언되어 있다.
    ```java
    class DelayQueue<E extends Delayed> implements BlockingQueue<E>
    ```
  - 이러한 타입 매개변수 `E`를 한정적 타입 매개변수라고 한다.


### 정리
- 클라이언트에서 직접 형변환해야 하는 타입보다 제네릭 타입이 더 안전하고 쓰기 편하다.
- 새로운 타입을 설계할 때는 형변환 없이도 사용할 수 있도록 하라.


<br>

