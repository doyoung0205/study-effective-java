# 제네릭

[아이템 32. 제네릭과 가변인수를 함께 쓸 때는 신중하라](#제네릭과-가변인수를-함께-쓸-때는-신중하라)  
[- varargs 매개변수화 타입에 제네릭을 허용하는 이유](#varargs-매개변수화-타입에-제네릭을-허용하는-이유)  
[- @SafeVarargs 애너테이션](#safevarargs-애너테이션)  
[- 제네릭 varargs 매개변수](#제네릭-varargs-매개변수)  
[- 정리](#정리)  

<br>

## 제네릭과 가변인수를 함께 쓸 때는 신중하라
- 가변인수는 메서드에 넘기는 인수의 개수를 클라이언트가 조절할 수 있게 해주는데, 구현 방식에 허점이 있다.
- 가변인수 메서드를 호출하면 가변인수를 담기 위한 배열이 자동으로 하나 만들어진다.
  ```java
  public static void example(List<String> ... stringLists) {
  }
  ```
- 메서드를 선언할 때 실체화 불가 타입으로 varargs 매개변수를 선언하면 컴파일러가 경고를 보낸다.
- 매개변수화 타입의 변수가 타입이 다른 객체를 참조하면 힙 오염이 발생한다.
  ```java
  public static void dangerous(List<String> ... stringLists) {
    List<Integer> intList = List.of(42);
    Object[] objects = stringLists;
    objects[0] = intList;               // 힙 오염 발생
    String s = stringLists[0].get(0);   // ClassCastException
  }
  ```

### varargs 매개변수화 타입에 제네릭을 허용하는 이유
- 제네릭이나 매개변수화 타입의 varargs 매개변수를 받는 메서드가 실무에서 매우 유용하기 때문이다.
  - `Arrays.asList(T... a)`
  - `Collections.addAll(Collection<? super T> c, T... elements)`
  - `EnumSet.of(E first, E... rest)`
- 다행히 위의 예시의 매개변수화 타입은 안전하다.


### @SafeVarargs 애너테이션
- 자바 7에서는 @SafeVarargs 애너테이션이 추가되어 제네릭 가변인수 메서드 작성자가 클라이언트 측에서 발생하는 경고를 숨길 수 있게 되었다.
- @SafeVarargs 애너테이션은 메서드 작성자가 그 메서드가 타입 안전함을 보장하는 장치다.
- 그렇다면 메서드가 안전한지는 어떻게 확신할 수 있을까?
  - 가변인수 메서드를 호출할 때 varargs 매개변수를 담는 제네릭 배열이 만들어진다.
  - varargs 매개변수 배열이 호출자로부터 그 메서드로 순수하게 인수들을 전달하는 일만 한다면 그 메서드는 안전하다.
- @SafeVarargs 애너테이션은 재정의 할 수 없는 메서드에만 달아야 한다. 재정의한 메서드도 안전할지는 보장할 수 없기 때문이다.
- 자바 8에서 이 애너테이션은 오직 정적 메서드와 final 필드에만 붙일 수 있고, 자바 9부터는 private 인스턴스 메서드에도 허용된다.


### 제네릭 varargs 매개변수
- varargs 매개변수 배열에 아무것도 저장하지 않고도 타입 안전성을 깰 수도 있으니 주의해야 한다.
  ```java
  public static <T> T[] toArray(T... args) {
      return args;
  }
  ```
  - 이 메서드가 반환하는 배열의 타입은 이 메서드에 인수를 넘기는 컴파일타임에 결정되는데, 그 시점에는 컴파일러에게 충분한 정보가 주어지지 않아 타입을 잘못 판단할 수 있다.
  - 따라서 varargs 매개변수 배열을 그대로 반환하면 힙 오염을 이 메서드를 호출한 쪽의 콜스택으로까지 전이하는 결과를 낳을 수 있다.생
  - [varargs 매개변수의 힙 오염이 콜스택으로 전이된 예제](../../src/main/java/study/heejin/chapter5/item32/PickTwo.java)

- 제네릭 varargs 매개변수를 안전하게 사용
  ```java
  @SafeVarargs
  public static <T> List<T> flatten(List<? extends T>... lists) {
      List<T> result = new ArrayList<>();
      for (List<? extends T> list : lists) {
          result.addAll(list);
      }
      return result;
  }
  ```
- 제네릭 varargs 매개변수를 List로 대체
  ```java
  public static <T> List<T> flatten(List<List<? extends T>> lists) {
      List<T> result = new ArrayList<>();
      for (List<? extends T> list : lists) {
          result.addAll(list);
      }
      return result;
  }
  ```





### 정리
- 가변인수와 제네릭은 함께 사용할 때 주의해야 한다.
- 타입 안전성이 깨지기 때문에 제네릭 varargs 배열 매개변수에 값을 저장하는 것은 안전하지 않다.
- 메서드에 제네릭 varargs 매개변수를 사용하고자 한다면, 메서드가 타입 안전하지 확인하고 @SafeVarargs 애너테이션을 달아주자.

<br>

---
#### Reference

- [Java - ScheduledThreadPoolExecutor 사용 방법](https://codechacha.com/ko/java-scheduled-thread-pool-executor)

