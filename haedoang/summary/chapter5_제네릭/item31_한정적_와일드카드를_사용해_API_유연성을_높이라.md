## item 31 한정적 와일드카드를 사용해 API 유연성을 높여라
- 제네릭은 불공변이라 하였다
- 따라서 제네릭은 상,하위 타입 구분이 존재하지 않기 때문에 유연하지 못할 수 있다
  - 리스코프 치환 법칙 위배하지 않기 위함
- 이러한 대체 수단으로 `한정적 와일드 카드 타입`을 사용할 수 있다

### 생산자와 소비자(PECS: Producer-Extend, Consumer-Super)
- 생산자(producer)
  - 매개변수화 타입 `T` 인스턴스를 생산하는 경우 
  - 매개변수화 타입 `T`가 생산자인 경우 `<? extends T>`를 사용한다
- 소비자(consumer)
  - 매개변수화 타입 `T` 인스턴스를 소비하는 경우
    매개변수화 타입 `T`가 생산자인 경우 `<? super T>`를 사용한다

### 유연성을 극대화하려면 원소의 생산자나 소비자용 입력 매개변수에 와일드카드를 타입을 사용하라
```java
public void smartPushAll(Iterable<? extends E> src) {
    for (E e : src) {
        push(e);
    }
}

public void smartPopAll(Collection<? super E> dst) {
    while (isEmpty()) {
        dst.add(pop());
    }
}
```
- 생산자, 소비자에 따른 와일드카드 한정 타입을 적용한 예이다
- smartPushAll(): `src` 매개변수가 `E` 타입을 생산하기 때문에 생산자이다
- smartPopAll(): `dst` 매개변수는 `E` 타입을 사용하는 소비자이다

### Comparator, Comparable
- 해당 인터페이스는 `E` 타입을 소비만 하는 인터페이스이다. 
  - `Comparator<? super E>`, `Comparable<? super E>`

### 메서드 선언에 타입 매개변수가 한번만 나오면 와일드카드로 대체하라
```java
//good
public static void swap(List<?> list, int i, int j);
    
//not good
public static <E> void swapHelper(List<E> list, int i, int j);

```
- 비한정적 타입 매개변수라면 비한정적 와일드카드로 한정적 타입 매개변수라면 한정적 와일드 카드로 변경한다
- 저자는 위의 예시에서 1번 코드를 지향하고 있다. 이러한 이유에 대해 조금 더 찾아보았다
- 캡슐화에 대한 의미도 포함되어 있는 것 같았다. public API에 `E` 타입에 대한 정보를 줄 필요가 없다는 것이다
- 오히려 공개 API에서는 와일드 카드 타입으로 `모든 타입을 받을 수 있음을 명시`해주고 내부 helperMethod를 통해 와일드 카드 타입을 실제 타입으로 변경한다


### 정리
- 제네릭을 사용할 경우 불공변 타입때문에 사용성에 제한이 생길 수 있다.
- 한정적 와일드카드를 사용하면 유연하게 사용할 수 있다.
- `PECS` 패턴은 `생산자:extends`, `소비자:super`이다.
- 메서드 선언에 타입 매개변수가 한번만 나오는 경우에는 와일드카드로 대체하라

- 레퍼런스
  - [https://stackoverflow.com/questions/69045652/why-list-can-convert-to-liste-through-method-invocation-conversion/69045719#69045719](https://stackoverflow.com/questions/69045652/why-list-can-convert-to-liste-through-method-invocation-conversion/69045719#69045719)