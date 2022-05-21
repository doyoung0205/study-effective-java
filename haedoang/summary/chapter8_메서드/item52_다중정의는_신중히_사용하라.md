## item52 다중정의는 신중히 사용하라

### 다중정의 메서드 사용 시 주의사항
- 메서드 호출 시 컴파일타임에 타입이 정해지기 때문에 제네릭 사용 시 주의해야 한다
    ```java
    // given
    Collection<?>[] collections = {
            new HashSet<String>(),
            new ArrayList<BigInteger>(),
            new HashMap<String, String>().values()
    };
    
    // when
    final List<String> actual = Arrays.stream(collections)
            .map(CollectionClassifier::classify)
            .collect(Collectors.toList());
    
    // then
    assertThat(actual).containsOnly("Etc");
    ```
    - 재정의한 메서드는 `동적`으로 선택되고, 다중정의 메서드는 `정적`으로 선택된다
- 다중정의가 혼동을 일으킬 수 있기 때문에 사용에 주의해야 한다
- 다중정의 대신 메서드 이름을 다르게 지어주는 방법도 있다

### 다중정의 메서드는 람다 사용 시 주의해야 한다
```java
ExecutorService exec = Executors.newCachedThreadPool();
exec.submit((Runnable) System.out::println); // Runnable, Callable 이 다중정의되어있음
```
- 메서드를 다중저으이할 떄, 서로 다른 함수형 인터페이스라도 같은 위치의 인수로 받아서는 안된다

### 정리
- 프로그래밍 언어가 다중정의를 허용한다고 해서 꼭 활용하라는 뜻은 아니다. 
- 매개변수 ㅅ가 같을 때는 다중정의를 피하는 게 좋다