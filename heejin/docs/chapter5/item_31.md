# 제네릭

[아이템 31. 한정적 와일드카드를 사용해 API 유연성을 높이라](#한정적-와일드카드를-사용해-api-유연성을-높이라)
[- 한정적 와일드카드](#한정적-와일드카드)
[- 타입 매개변수와 와일드 카드](#타입-매개변수와-와일드-카드)
[- 정리](#정리)

<br>

## 한정적 와일드카드를 사용해 API 유연성을 높이라
- 매개변수화 타입은 불공변이기 때문에 `List<String>`은 `List<Object>`의 하위 타입도, 상위 타입도 아니다. (리스코프 치환 원칙)
- 하지만 불공변 방식보다 유연한 방식이 필요할 때가 있다.

    ```java
    public void pushAll(Iterable<E> src) {
        for (E e : src) {
            push(e);
        }
    }
    ```
  ```java
  public static void main(String[] args) {
      Stack<Number> numberStack = new Stack<>();
      Iterable<Integer> integers = Arrays.asList(3, 1, 4, 1, 5, 9);
      numberStack.pushAll(integers);
  }
  ```
  - Integer는 Number의 하위 타입이기 때문에 잘 동작해야 할 것 같지만, 실제로는 컴파일 오류가 발생한다.


### 한정적 와일드카드
- 위의 Stack 예제의 경우 **한정적 와일드카드 타입**을 사용해 해결할 수 있다.

  ```java
  public void pushAll(Iterable<? extends E> src) {
      for (E e : src) {
          push(e);
      }
  }
  ``` 
  - `Iterable<? extends E>` : 'E의 Iterable'이 아니라 'E의 하위 타입의 Iterable'이어야 한다는 의미이다.

  ```java
  public void popAll(Collection<? super E> dst) {
      while (!isEmpty()) {
          dst.add(pop());
      }
  }
  ``` 
  - `Collection<? super E>` : 'E의 Collection'이 아니라 'E의 상위 타입의 Collection'이어야 한다는 의미이다.
  

- 즉, 원소의 생산자나 소비자용 입력 매개변수에 와일드카드 타입을 사용하면 유연성을 극대화할 수 있다.
- 한편, 입력 매개변수가 생산자와 소비자 역할을 동시에 한다면 와일드카드 타입을 써도 좋을 게 없다.  
  타입을 정확히 지정해야 하는 상황에서는 와일드카드 타입을 쓰지 말아야 한다.
- 반환 타입에는 한정적 와일드카드 타입을 사용하면 안된다. 
  유연성을 높여주기는 커녕 클라이언트 코드에서도 와일드카드 타입을 써야하기 때문이다. 

> 펙스 (PECS) : producer-extends, consumer-super  
> PECS 공식은 와일드카드 타입을 사용하는 기본 원칙이다.
> - 매개변수화 타입 T가 생상자라면 <? extends T> 를 사용
> - 매개변수화 타입 T가 소비자라면 <? super T>를 사용


- [max 메서드에 와일드카드 적용](../../src/main/java/study/heejin/chapter5/item31/RecursiveTypeBound.java)


### 타입 매개변수와 와일드 카드
- 타입 매개변수와 와일드 카드에는 공통되는 부분이 있어서, 메서드를 정의할 때 둘 중 어느 것을 사용해도 괜찮을 때가 많다.
- **기본 규칙은 메서드 선언에 타입 매개변수가 한 번만 나오면 와일드카드로 대체한다는 것이다.**


### 정리
- 조금 복잡하더라도 와일드카드 타입을 적용하면 API가 훨씬 유연해진다.
- PECS 공식을 기억하자.
- 생산자는 extends, 소비자는  super를 사용해야 한다.


<br>

---
#### Reference

- [Java - ScheduledThreadPoolExecutor 사용 방법](https://codechacha.com/ko/java-scheduled-thread-pool-executor/)

