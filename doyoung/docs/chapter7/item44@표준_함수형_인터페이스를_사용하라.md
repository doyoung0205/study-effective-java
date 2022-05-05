### 표준 함수형 인터페이스를 사용하라.

필요한 용도에 맞는 게 있다면, 직접 구현하지 말고 표준 함수형 인터페이스를 활용하라.

- API가 다루는 개념의 수가 줄어들어 익히기 더 쉬워진다.
- 상호 운영성이 좋은 default 메서드도 많이 제공한다.

### 표준 인터페이스

- Operator : 반환 타입과 인수 타입이 같은 함수
    - UnaryOperator<T>, T apply(T t), String::toLowerCase
    - BinaryOperator<T>, T apply(T t1, T t2), BigInteger::add
- Predicate: 인수 하나를 받아 boolean 을 반환
    - Predicate<T>, boolean test(T t), Collection::isEmpty
- Function: 인수와 반환타입이 다른 함수
    - Function<T, R>, R apply(T t), Arrays::asList
- Supplier: 인수를 받지 않고 값을 제공하는 함수
    - Supplier<T>, T get(), Instant::now()
- Consumer: 인수를 하나 받고 반환값이 없는 함수
    - Consumer<T>, void accept(T t), System.out::println

### 주의사항

기본 함수형 인터페이스에 박싱된 기본 타입을 넣어 사용하지는 말자

### 전용 함수형 인터페이스를 고려해봐야할 떄

- 자주 쓰이며, 이름 자체가 용도를 명확히 설명해줄 때
- 반드시 따라야 하는 규약이 있을 때
- 유용한 디폴트 메서드를 제공해야할 때

### 직접 함수형 인터페이스 주의사항

- 항상 @FunctionalInterface 애너테이션을 사용하라 !
- 서로 다른 함수형 인터페이스를 같은 위치의 인수로 받는 메서드들을 다중으로 정의해서는 안된다.

```

final ExecutorService executorService = Executors.newFixedThreadPool(4);
executorService.submit(() -> {
    System.out.println("runnable");
});

executorService.submit(() -> {
    return "callable";
});

```
