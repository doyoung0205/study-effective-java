# 제네릭

[아이템 26. 로 타입은 사용하지 말라](#로-타입은-사용하지-말라)

<br>

## 로 타입은 사용하지 말라

### 타입 매개변수
- 클래스와 인터페이스 선언에 타입 매개변수가 쓰이면, 이를 제네릭 클래스 혹은 제네익 인터페이스라고 한다.  
  - `List`는 원소의 타입을 나타내는 타입 매개변수 `E`를 받는다.  
  - 그래서 이 인터페이스의 완전한 이름은 `List<E>`지만, 짧게 `List` 라고 자주 쓴다.


### 매개변수화 타입
- 각각의 제네릭 타입은 일련의 매개변수화 타입을 정의한다.
  - `List<String>` 에서 `String`이 정규 타입 매개변수 ``에 해당하는 실제 타입 매개변수다.


### 로 타입
- 제네릭 타입을 하나 정의하면 그에 딸린 로 타입(raw type)도 함께 정의된다.
- 로 타입이란 제네릭 타입에서 타입 매개변수를 전혀 사용하지 않을 때를 말한다.
  - `List<E>`의 로 타입은 `List`다.
- 로 타입을 쓰는 걸 언어 차원에서 막아 놓지는 않았지만 절대로 써서는 안된다.
  - 로 타입은 타입 선언에서 제네릭 타입 정보가 지워진 것처럼 동작하는데, 제네릭이 도래하기 전 코드와 호환되도록 하기 위함이다.
  - 로 타입을 쓰면 제네릭이 안겨주는 안전성과 표현력을 모두 잃게 된다.
- 로 타입을 사용하면 `ClassCastException`이 런 타입에 발생할 수 있다.
- 실제 타입 매개변수가 무엇인지 신경 쓰고 싶지 않다면, 비한정적 와일드카드 타입을 사용하는 것이 좋다.
  - 예를들어 제네릭 타입인 Set<E>의 unbounded wildcard type은 Set<?>이다.
  ```java
  static int numElementsInCommon(Set<?> set1, Set<?> set2) { ... }
  ```
  
### 로 타입을 쓰지 말라는 규칙의 예외
- class 리터럴에는 로 타입을 써야 한다.
  - 자바 명세는 class 리터럴에 매개변수화 타입을 사용하지 못하게 했다(배열과 기본 타입은 허용한다).
  - 예를 들어, `List.class`, `String[].class`, `int.class` 는 허용하고, `List<String>.class` 와 `List<?>.class` 는 허용하지 않는다.
- 런타임에는 제네릭 타입 정보가 지워지므로, `instanceof` 연산자는ㄴ 비한정적 와일드카드 타입 이외의 매개변수화 타입에는 적용할 수 없다. 
- `instanceof` 메서드는 로 타입이든 비한정적 와일드카드 타입이든 똑같이 동작한다.


### 용어 정리

| 한글 용어 | 영문 용어 | 예 | 아이템 |
|:---:|:---:|:---:|:---:|
| 매개변수화 타입 | parameterised type | List<String> | 아이템 26 |
| 실제 타입 매개변수 | actual type parameter | String | 아이템 26 |
| 제네릭 타입 | generic type | List<E> | 아이템 26, 29 |
| 정규 타입 매개변수 | formal type parameter | E | 아이템 26 |
| 비한정적 와일드카드 타입 | unbounded wildcard type | List<?> | 아이템 26 |
| 로 타입 | raw type | List | 아이템 26 |
| 한정적 타입 매개변수 | bounded type parameter | <E extends Number>| 아이템 29 |
| 재귀적 타입 한정 | recursive type bound | List<? extends Comparable<T>> | 아이템 30 |
| 한정적 와일드카드 타입 | bounded wildcard type | List<? extends Number> | 아이템 31 |
| 제네릭 메서드 | generic method | static <E> List<E> asList(E[] a) | 아이템 30 |
| 타입 토근 | type token | String.class | 아이템 33 |


<br>

