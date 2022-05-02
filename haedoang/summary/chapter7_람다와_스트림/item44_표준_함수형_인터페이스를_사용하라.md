## item44 표준 함수형 인터페이스를 사용하라

- 람다의 지원으로 템플릿 메서드 패턴의 매력이 줄게되었다.
  - `함수객체를 받는 정적 팩터리 또는 생성자`를 사용한다

### 필수로 알아야하는 함수형 인터페이스
- UnaryOperator<T>               
  - T apply(T t)
    - String::toLowerCase
    
- BinaryOperator<T>
  - T apply(T t1, T t2)
    - BigInteger::add
    
- Predicate<T> 
  - boolean test(T t)
    - Collection::isEmpty
    
- Function<T, R>
  - R apply(T t)
    - Arrays::asList
    
- Supplier<T> 
  - T get()
    - Instant::now
    
- Consumer<T>
  - void accept(T t)
    - System.out::println

### 자바에서 제공하는 표준 함수형 인터페이스를 활용하라
- `java.util.function` 패키지에는 총 43개의 인터페이스가 담겨져있다
- 위 6개의 인터페이스만 알면 나머지는 변형된 인터페이스로 유추가 가능하다
- 필요한 용도에 맞는게 있는 경우 직접 구현하지 말고 사용하도록 하자

### 기본 함수형 인터페이스에 박싱된 기본 타입을 넣어 사용하지는 말자
- 표준 함수형 인터페이스는 대부분 기본타입을 지원한다
- 불필요한 오토박싱은 성능저하를 유발한다

### 함수형 인터페이스 작성의 목적
1. 해당 클래스의 코드가 설명 문서를 읽을 이에게 인터페이스가 람다용으로 설계되었음을 의미한다
2. 해당 인터페이스가 1개의 추상 메서드만을 가지고 있어야 컴파일되게 해준다
3. 유지보수 과정에서 누군가 실수로 메서드를 추가하지 못하도록 막아준다

### 직접 만든 함수형 인터페이스에는 항상 @FunctionalInterface 애너테이션을 사용하라

### 서로 다른 함수형 인터페이스를 같은 위치의 인수로 받는 메서드들을 다중정의하면 안된다
- 클라이언트에게 불필요한 모호함을 줄 수 있다.
  ```java
  executorService.submit((Runnable) ()-> {
  });
  
  executorService.submit((Callable<? extends Object>) ()-> {
    return null;
  });  
  ```


### 정리
- API 설계 시 람다도 염두에 두어야 한다. 입력값과 반환값에 함수형 인터페이스 타입을 활용하라