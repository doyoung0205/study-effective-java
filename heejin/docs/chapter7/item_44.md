# 람다와 스트림

[아이템 44. 표준 함수형 인터페이스를 사용하라](#표준-함수형-인터페이스를-사용하라)  
[- 표준 함수형 인터페이스](#표준-함수형-인터페이스)  
[- 전용 함수용 인터페이스를 구현해야 하는 경우](#전용-함수용-인터페이스를-구현해야-하는-경우)   
[- 함수형 인터페이스를 사용할 때 주의점](#함수형-인터페이스를-사용할-때-주의점)  
[- 정리](#정리)

<br>

## 표준 함수형 인터페이스를 사용하라
- 자바가 람다를 지원하면서 API를 작성하는 방식이 크게 바뀌었다. 
  - 예컨데, 템플릿 메서드 패턴 사용법 보다 함수 객체를 매개변수로 받는 생성자와 메서드를 사용하는 방식을 사용하게 되었다.  
  - [LinkedHashMap 의 removeEldestEntry 예시](../../src/test/java/study/heejin/chapter7/item44/RemoveEldestEntryTest.java)  
    1 ) removeEldestEntry 메서드 재정의  
    2 ) EldestEntryRemovalFunction 함수형 인터페이스 사용
      ```java
      @FunctionalInterface
      public interface EldestEntryRemovalFunction<K, V> {
          boolean remove(Map<K, V> map, Map.Entry<K, V> eldest);
      }
      ```
      → 필요한 용도에 맞는 표준 함수형 인터페이스가 있다면, 직접 구현하지 않고, 표준 함수형 인터페이스를 사용하자.  
    3 ) BiPredicate 표준 함수형 인터페이스 사용
    ```java
    @FunctionalInterface
    public interface BiPredicate<T, U> {
        boolean test(T t, U u);
    }
    ```
    
### 표준 함수형 인터페이스
- 표준 함수형 인터페이스들은 유용한 디폴트 메서드를 많이 제공한다.
- 표준 함수형 인터페이스 대부분은 기본 타입만 지원한다.
- 기본 함수형 인터페이스에 박싱된 기본 타입을 넣어 사용하지 말자.
- `java.util.funtion` 패키지에는 총 43개의 인터페이스가 담겨 있다.

  | 인터페이스 | 함수 시그니처 | 예 |
  |:---:|:---:|:---:|
  | UnaryOperator<T> | T apply(T t)) | String::toLowerCase |
  | BinaryOperator<T> | T apply(T t1, T t2) | BigInteger::add |
  | Predicate<T> | boolean test(T t) | Collection::isEmpty |
  | Function<T, R> | R apply(T t) | Arrays::asList |
  | Supplier<T> | T get() | Instant::now |
  | Consumer<T> | void accept(T t) | System.out::println |

  - `Operator` 인터페이스는 인수가 1개인 `UnaryOperator와` 인수가 2개인 `BinaryOperator로` 나뉘며, 반환값과 인수의 타입이 같은 함수를 뜻한다.
  - `Predicate` 인터페이스는 인수 하나를 받아 boolean을 반환하는 함수를 뜻한다.
  - `Function` 인터페이스는 인수와 반환 타입이 다른 함수를 뜻한다.
  - `Supplier` 인터페이스는 인수를 받지 않고 값을 반환(혹은 제공)하는 함수를 뜻한다.
  - `Consumer` 인터페이스는 인수 하나 받고 반환값은 없는(인수를 소비하는) 함수를 뜻한다.


#### 표준 함수형 인터페이스의 변형
- 변형1) 매개변수가 지정된 경우
  - eg. `IntPredicate`, `LongBinaryOperator`
  - `Function` 인터페이스의 경우 반환 타입이 매개변수화 된다.  
    \- eg. `LongFunction<int[]>` 은 long을 인수를 받아 int[]을 반환한다. → [예제 코드](../../src/test/java/study/heejin/chapter7/item44/Item44Test.java#LC15)

- 변형2) 반환타입이 지정된 경우
  - eg. `ToLongFunction` 은 int[] 인수를 받아 long을 반환한다. → [예제 코드](../../src/test/java/study/heejin/chapter7/item44/Item44Test.java#LC35)

- 변형3) 반환타입과 매개변수가 지정된 경우
  - 인수와 같은 타입을 반환하는 함수는 `UnaryOperator` 이므로, `Function` 인터페이스의 변형은 입력과 결과의 타입이 항상 다르다.
  - 입력과 결과 타입이 모두 기본 타입이면 접두어로 `SrcToResult`를 사용한다.
  - eg. `LongToIntFunction` 은 long을 인수로 받아 int를 반환한다. → [예제 코드](../../src/test/java/study/heejin/chapter7/item44/Item44Test.java#LC52)
  

### 전용 함수용 인터페이스를 구현해야 하는 경우
- API에서 자주 쓰이며, 이름 자체가 용도를 명확히 설명해 준다.
- 구현하는 쪽에서 반드시 지켜야 할 규약이 있다.
- (비교자들을 변환하고 조합해주는) 유용한 디폴트 메서드를 제공할 수 있다.


### 함수형 인터페이스를 사용할 때 주의점
- `@FunctionalInterface` 애너테이션을 사용해서 프로그래머의 의도를 명시해야 한다.
  - 해당 인터페이스가 람다용으로 설계된 것임을 알려준다.
  - 해당 인터페이스가 추상 메서드를 오직 하나만 가지고 있어야 컴파일된다.
- 서로 다른 함수형 인터페이스를 같은 위치의 인수로 받는 메서드들을 다중정의하면 안된다.
  - `ExecutorSevice의` sumit 메서드는 `Callback<T>`를 받는 것과 `Runnable`을 받는 것을 다중정의해서 모호해졌다. → [예제 코드](../../src/test/java/study/heejin/chapter7/item44/Item44Test.java#LC62)


### 정리
- 필요한 용도에 맞는게 있다면, 직접 구현하지 말고 표준 함수형 인터페이스를 활용하라.
- 기본 함수형 인터페이스에 박싱된 기본 타입을 넣어 사용하지 말자.
- 직접 만든 함수형 인터페이스에는 항상 `@FunctionalInterface` 애너테이션을 사용하라.

<br>