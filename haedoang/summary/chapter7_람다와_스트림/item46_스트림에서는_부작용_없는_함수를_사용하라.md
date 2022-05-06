## item46 스트림에서는 부작용 없는 함수를 사용하라

### 스트림 패러다임의 핵심
- 계산을 일련의 변환으로 재구성한다
- 각 계산의 단계, 변환 단계는 가능한 한 이전 단계의 결과를 받아 처리하는 순수 함수여야 한다
  - 순수함수란 오직 입력만이 결과에 영향을 주는 함수를 말한다
  - 다른 가변 상태를 참조하지 않고, 함수 스스로도 다른 상태를 변경하지 않는다
  - 스트림 연산에 건네는 함수 객체는 모두 부작용이 없어야 한다

### 스트림을 잘못 사용하는 경우 예시1) 다른 가변 상태를 참조하여 다른 상태를 변경함
```java
// anti
try (Stream<String> words = Stream.of("a", "b", "c", "d", "e")) {
    words.forEach(word -> {
        freq.merge(word.toUpperCase(), 1L, Long::sum);
    });
}

// good
try (Stream<String> words = Stream.of("a", "b", "c", "d", "e")) {
    freq = words.collect(groupingBy(String::toUpperCase, counting()));
}
```
- 스트림의 종단 연산인 `forEach` 에서 freq 객체의 상태를 변경한다
- `forEach` 연산은 스트림 계산 결과를 보고할 때만 사용하고, 계산할 때는 사용하지 말자


### Collector 
- `java.util.Collectors` 클래스는 수집기라고 하며, 스트림 원소를 객체 하나로 취합하는 역할을 한다
- `toList()`, `toSet()`, `toCollection(collectionFactory)` 
- 상세 예제는 별도의 테스트 코드는 별도의 테스트 코드에 작성 예정

### 정리
- 스트림 파이프 라인 프로그래밍의 핵심은 부작용 없는 함수 객체에 있다
- 종단 연산인 forEach는 결과를 보고할 때만 사용하도록 한다
- `toList`, `toSet`, `toMap`, `groupingBy`, `joining` 의 사용법을 꼭 익히도록 한다