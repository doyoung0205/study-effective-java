## item26 로 타입은 사용하지 말라

### Hello, Generic
- 제네릭이 나오기 이전까지는 컬렉션에서 객체를 꺼낼 때마다 객체 타입을 지정하도록 형변환을 해주어야 했다
- 형변환의 오류는 런타임 시점에서 오류를 발견할 수 있었다
- 제네릭을 사용하면 위와 같은 문제를 컴파일 시점에서 확인할 수 있기 떄문에 더 안전하고 명확한 프로그램을 만들어준다


### 제네릭이란 ? 
- `제네릭 클래스`, `제네릭 인터페이스`: 클래스와 인터페이스 선언에 타입 매개변수가 쓰이는 경우를 말한다
  - ex) `public interface Set<E> extends Collection<E>`
- 우리는 제네릭 클래스와 제네릭 인터페이스를 통틀어 `제네릭 타입(generic type)` 이라고 부른다
- 제네릭 타입은 `매개변수화 타입(Parameterized type)`을 정의한다
  - ex) `List<String>` : List 클래스의 타입 매개변수를 String 으로 한정한다
- 제네릭 타입을 하나 정의하면 그에 딸린 `로 타입(raw type)`도 함께 정의된다
  - 제네릭 탕비에서 타입 매개변수를 사용하지 않는 경우를 말한다 => 제네릭 도래하기 전 코드와의 `호환성` 때문
  - 따라서, 우리는 로 타입을 굳이 사용할 필요가 없고 이에 따른 문제점을 만들 필요가 없다

### raw type 사용 시의 문제점
> private final Collection stamps;
- `raw type`은 타입을 한정하지 않는다 예를 들면, `Collection<Object>` 형태로 동작한다
- 컬렉션에 데이터를 넣는 경우에는 최상위 타입이기 때문에 문제될 것은 없지만,<br/>
  `컬렉션 내 객체에 접근하여 형변환이 일어나는 시점` 즉, 런타임 동작 시 문제를 발생할 수 있다
    
### raw type 을 사용하지 말고 매개변수화 타입을 사용하자 
> private final Collection<Stamp> stamps;
- `타입 안정성`을 보장하여 컴파일 시점에서 오류를 파악할 수 있다
- **컴파일러는 컬렉션에서 원소를 꺼내는 모든 곳에서 `보이지 않는 형 변환`을 추가하여 절대 실패하지 않음을 보장한다**

### raw type은 이전 코드와의 호환성 때문에 만들어진 것이다
- 기존의 코드와의 호환성을 깨뜨리지 않기 위해 raw type이 정의되었고 제네릭 구현에는 `소거(erasure)` 방식이 사용되었다
  - 소거 방식이란, 원소 타입을 컴파일타임에만 검사하며 런타임에는 알 수 없는 방식이다 (제네릭 이전 코드와의 호환성을 위함)

### raw type vs Object 매개변수화 타입 (List vs List&lt;Object&gt;)
- `raw type`는 위에서도 말했듯이 제네릭 이전의 사용과의 호환성으로 만들어졌다. 즉 모든 타입을 받을 수 있다 
  - ex) `List<String>`, `List<Boolean>`
- ``List<Object>``은 ``List<String>``의 상위 타입이 아니다 
  - Object는 String 클래스의 상위 타입이지만 `컬렉션 객체에서는 아님`을 주의할 것
  - 동일한 컬렉션에 다른 타입의 유형의 추가가 발생할 충돌을 방지하기 위해 제네릭의 하위 타입 규칙으로 지정되었다
- 매개변수화 타입에 반해 로 타입은 타입 안정성을 잃게 된다 

### 비한정적 와일드카드 타입(unbounded wildcard type)
- 로 타입은 모든 타입을 받을 수 있었다. 하지만 타입 안정성을 갖지 못한다 <br/>
  이러한 단점을 보완하기 위해서 `비한정적 와일드카드 타입`을 사용한다
- `<?>`: 실제 타입 매개변수가 무엇인지 알 필요가 없는 경우 사용한다(타입 안정성 보장)

### raw type vs unbounded wild card type vs bounded type  
- `List`: 유형 매개변수가 없는 리스트 요소가 모든 유형의 목록이다 각 요소들이 서로 다른 타입일 수 있다
- `List<?>`: `요소를 특정하지만 알려지지 않은 유형`의 목록이다 모두 같은 유형이어야 한다
- `List<T extends E>`: `T` 유형 매개변수는 `E`를 확장한 유형의 목록이다 그렇지 않은 경우 유효하지 못하다

### 로 타입을 사용하는 경우 (class 리터럴, instanceof)
- `class 리터럴`: 런타임 시점에서 제네릭 정보가 지워진다는 것을 학습하였다. 따라서 클래스 리터럴에서는 제네릭 타입을 가질 수 없다 
  - 제네릭 클래스의 모든 인스턴스는 실제 유형 매개변수에 관계없이 동일한 런타임 클래스를 가진다 
  - ```java
      final List<String> strs = Arrays.asList();
      final List<Integer> numbers = Arrays.asList();
      
      // then
      assertThat(strs.getClass() == numbers.getClass()).isTrue();
    ```
- `instanceof`: class 리터럴에 대한 설명과 같다 


#### 정리
- 로 타입은 제네릭 이전의 코드와의 호환성을 위해 사용되었지만 타입 안정성을 보장하지 못하기 때문에 사용을 지양하도록 한다
- String 매개변수화 타입은 Object 매개변수화 타입의 하위 타입이 아니다
- 타입을 한정하지 않는 경우 로 타입 대신 `비한정적 와일드카드 타입`을 사용하여 타입 안정성을 보장하자


#### 출처 
- [https://stackoverflow.com/questions/2745193/why-is-using-collectionstring-class-illegal](https://stackoverflow.com/questions/2745193/why-is-using-collectionstring-class-illegal)