# 메서드

[아이템 55. 옵셔널 반환은 신중히 하라](#옵셔널-반환은-신중히-하라)  
[- Optional 사용 시 주의사항](#optional-사용-시-주의사항)  
[- Optional을 사용하는 취지](#optional을-사용하는-취지)  
[- 언제 Optional을 사용하는가?](#언제-optional을-사용하는가)  
[- 정리](#정리)  

<br>

## 옵셔널 반환은 신중히 하라
메서드가 특정 조건에서 값을 반환할 수 없을 때 취할 수 있는 방법은 크게 2가지 있었다.  
1. 예외를 던진다.  
예외는 진짜 예외 적인 상황에서만 사용해야 하며 _(→ item 69)_, 예외를 생성할 때 스택 추적 전체를 캡쳐해야 하므로 비용도 만만치 않다.  
2. `null`을 반환한다.  
`null`을 반환할 수 있는 메서드는 별도의 null 처리 코드를 추가하지 않으면, 언젠가 NullpointExcetion이 발생할 수 있다.

- 자바 8에서는 옵셔널이 추가되었다.
  - `Optional<T>`는 `null`이 아닌 `T` 타임 참조를 하나 담거나, 혹은 아무것도 담지 않을 수 있다.
  - 옵셔널은 원소를 최대 1개 가질 수 있는 '불변' 컬렉션이다.
  - 보통은 T를 반환해야 하지만 특정 조건에서는 아무것도 반환하지 않아야 할 때 T 대신 Optional<T> 를 반환하도록 선언하면 된다. 
    그러면 유효한 반환값이 없을 때는 빈 결과를 반환하는 메서드가 만들어진다.
  - **옵셔널을 반환하는 메서드는 예외를 던지는 메서드보다 유연하고 사용하기 쉬우며, null을 반환하는 메서드보다 오류 가능성이 적다.**


### Optional 사용 시 주의사항
- Optional을 반환하는 메서드에서는 절대 null을 반환하지 말자. 이는 옵셔널을 도입한 취지를 무시하는 행위이다.
  - `Optional.of(value)` 에 null을 넣으면 NullpointException을 던진다.
  - null 값도 허용하는 옵셔널을 만들려면 `Optional.ofNullable(value)`를 사용하면 된다.
    ```java
    public static <E extends Comparable<E>> Optional<E> maxOptional(Collection<E> c) {
          if (c.isEmpty()) {
              return Optional.empty();
          }
          E result = null;
          for (E e : c) {
              if (result == null || e.compareTo(result) > 0) {
                  result = Objects.requireNonNull(e);
              }
          }
          return Optional.of(result);
      }
    ```

- 스트림의 종단 연산 중 상당수가 Optional을 반환한다.
  ```java
  public static <E extends Comparable<E>> Optional<E> maxStream(Collection<E> c) {
      return c.stream()
              .max(Comparator.naturalOrder());
  }
  ```


### Optional을 사용하는 취지
- 반환값이 없을 수도 있음을 API 사용자에게 명확히 알려준다.
  - 비검사 예외를 던지거나 null을 반환하면 클라이언트에서 그 사실을 인지하지 못했을 때 문제가 발생한다.
  - 하지만 검사 예외를 던지면 클라이언트에서 이에 대처하는 코드를 작성해야 한다.


- 마찬가지로 메서드에서 Optional을 반환한다면 이에 대처할 수 있다.
  - 기본 값을 설정할 수 있다.  
    `String lastWordInLexicon = max(words).orElse("단어 없음");`
  - 상황에 맞는 예외를 던질 수 있다.  
    `Toy toy = max(toys).orElseGet(TemperTantrumException::new);`
  - 곧 바로 값을 꺼내 사용할 수 있다.  
    `Element lastNobleGas = max(Elements.NOBLE_GASES).get();`
 

- 기본값을 설정하는 비용이 아주 커서 부담이 되는 경우에는 `orElseGet`을 사용하면 초기 설정 비용을 낮출 수 있다. 
  - `Supplier<T>`를 인수로 받는 `orElseGet`을 사용하면, 값이 처음 필요할 때 `Supplier<T>`를 사용해 생성하므로 초기 설정 비용을 낮출 수 있다.
  - `isPresent` 메서드를 사용할 때, 상당수는 `Supplier<T>`를 사용한 메서드로 대체할 수 있다.
    ```java
    // isPresent 를 적절하지 못하게 사용한 예
    System.out.println("부모 PID: " +
            (parentProcess.isPresent() ? String.valueOf(parentProcess.get().pid()) : "N/A")
    );

    // Optional의 map를 사용한 예
    System.out.println("부모 PID: " +
            parentProcess.map(h -> String.valueOf(h.pid())).orElse("N/A")
    );
    ```
    

- 스트림을 사용할 때, Optional의 값이 채워져 있는 것들을 뽑아서 사용할 수 있다.
  - 자바 8에서는 다음과 같이 구현 가능
    ```java
    listOfOptionals.stream()
            .filter(Optional::isPresent)
            .map(Optional::get);
    ```
  - 자바 9에서는 다음과 같이 구현 가능
    ```java
    listOfOptionals.stream()
            .flatMap(Optional::stream);
    ```


### 언제 Optional을 사용하는가?

- 결과가 없을 수 있으며, 클라이언트가 이 상황을 특별하게 처리해야 한다면 `Optional<T>`를 반환한다.
  - 하지만 Optional도 엄연히 새로 할당하고 초기화해야 하는 객체이고, 그 안에서 값을 꺼내려면 한 단계를 더 거치게 된다.
  - 따라서 성능이 중요한 상황에서는 Optional이 맞지 않을 수 있다.

- 박싱된 기본 타입을 담은 옵셔널을 반환하는 일은 없어야 한다.
  - `int`, `long`, `double` 전용 Optional 클래스가 있다.
  - `OptionalInt`, `OptionalLong`, `OptionalDouble` 이다.

- 컬렉션, 스트림, 배열, 옵셔널 같은 컨테이너 타입은 옵셔널로 감싸면 안된다.
  - 빈 `Optional<List<T>>`를 반환하기보다는 빈 `List<T>`를 반환하는 것이 좋다.

- 컬렉션이 키, 값, 원소나 배열의 원소로 사용하는 것이 적절한 상황은 거의 없다.


### 정리
- 값을 반환하지 못할 가능성이 있고, 호출할 때마다 반환값이 없을 가능성을 염두에 둬야 하는 메서드라면 옵셔널을 반환해야 할 상황일 수 있다.
- 옵셔널 반환에는 성능 저하가 뒤따르니, 성능에 민감한 메서드라면 null을 반환하거나 예외를 던지는 편이 나을 수 있다. 



<br>
