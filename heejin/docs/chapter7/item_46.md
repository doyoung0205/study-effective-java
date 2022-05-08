# 람다와 스트림

[아이템 46. 스트림에서는 부작용 없는 함수를 사용하라](#스트림에서는-부작용-없는-함수를-사용하라)  
[- 수집기(Collector)의 종류](#수집기collector의-종류)  
[- 정리](#정리)  

<br>

## 스트림에서는 부작용 없는 함수를 사용하라
- 스트림은 함수형 프로그래밍에 기초한 패터다임이기 때문에 이해하기 어려울 수 있다.
- 스트림 패러다임의 핵심은 계산을 일련의 변환(transformation)으로 재구성하는 부분이다.
  - 이때, 각 변환 단계는 가능한 한 이전 단계의 결과를 받아 처리하는 순수 함수여야 한다.
  > 순수 함수란, 오직 입력만이 결과에 영향을 주는 함수를 말한다. 다른 가변 상태를 참조하지 않고, 함수 스스로도 다른 상태를 변경하지 않는다.
  - 중간 단계에서나 종단 단계에서나 스트림 연산에 건네는 함수 객체는 모두 부작용(side effect)이 없어야 한다.
  - 아래는 스트림을 잘못 사용한 예시이다.
    ```java
    Map<String, Long> freq = new HashMap<>();
    try (Stream<String> words = new Scanner(file).tokens()) {
        words.forEach(word -> {
        freq.merge(word.toLowerCase(), 1L, Long::sum);
    });
    ```
  - 스트림을 제대로 사용하여 다음과 같이 바꿀 수 있다.
    ```java
    Map<String, Long> freq;
    try (Stream<String> words = new Scanner(file).tokens()) {
        freq = words.collect(groupingBy(String::toLowerCase, counting()));
    }
    ```
  - **forEach 연산은 스트림 계산 결과를 보고할 때만 사용하고, 계산하는 데는 쓰지 않는 것이 좋다.**


### 수집기(Collector)의 종류
- `java.util.stream.Collectors` 클래스는 메서드를 39개 가지고 있다.
- 수집기가 생성하는 객체는 일반적으로 컬렉션이다.
- 수집기는 총 세 가지로, `toList()`, `toSet()`, `toCollection(collectionFactory)`가 있다.
- 스트림을 맵으로 취합하는 기능은 컬렉션에 취합하는 것보다 훨씬 복잡하다.

#### 맵 수집기
- `toMap` 형태는 스트림의 각 원소가 고유한 키에 매핑되어 있을 때 적합하다.
- 스트림 원소 다수가 같은 키를 사용한다면 파이프라인이 `IllegalStateException`을 던지며 종료될 것이다.
- 더 복잡한 형태의 `toMap`이나 `groupingBy`는 이런 충돌을 다루는 다양한 전략을 제공한다.
- 병합 함수의 형태는 `BinaryOperator<U>`이며, 여기서 U는 해당 맵의 값 타입이다.
- `groupingBy`는 입력으로 분류 함수(classifier)를 받고, 출력으로는 원소들을 카테고리별로 모아 놓은 맵을 담은 수집기를 반환한다.
  - `groupingBy`가 반환하는 수집기가 리스트 외의 값을 갖는 맵을 생성하게 하려면, 분류 함수와 함께 다운스트림(downstream) 수집기도 함께 명시해야 한다.
  - 다운스트림 수집기의 역할은 해당 카테고리의 모든 원소를 담은 스트림으로부터 값을 생성하는 일이다.
- `partitioningBy`는 분류 함수 자리에 프레디키트(predicate)를 받고, 키가 Boolean인 맵을 반환한다.

#### 기타 수집기
- 수집기에서 Collections의 속성 메서드를 활용할 수도 있다. 
  - `summing`, `averaging`, `summarizing` 등
- `reducing` 메서드들은 스트림 기능의 일부를 복제하여 다운스트림 수집기를 작은 스트림처럼 동작하게 한 것이다.
  - `filtering`, `mapping`, `flatMapping`, `collectingAndThen` 등
- `Collectors`에 정의되어 있지만 '수집'과는 관련이 없는 메서드도 3개 있다.
  - `minBy`, `maxBy`는 비교자를 이용해 스트림에서 값이 가장 작은 혹은 가장 큰 원소를 찾아서 반환한다.
  - `joinning`은 원소들을 연결하는 수집기를 반환한다.


### 정리
- 스트림 파이프라인 프로그래밍의 핵심은 부작용 없는 함수 객체에 있다.
- 종단 연산 중 `forEach`는 스트림이 수행한 계산 결과를 보고할 때만 이용해야 한다. 계산 자체에는 이용하지 말자.
- 스트림을 올바로 사용하려면 수집기를 잘 알아둬야 한다.
- 가장 중요한 수집기 팩터리는 `toList`, `toSet`, `toMap`, `groupingBy`, `joining`이다.



<br>


#### Reference
- [Class Collectors](https://docs.oracle.com/javase/10/docs/api/java/util/stream/Collectors.html)