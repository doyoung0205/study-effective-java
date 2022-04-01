## 한정적 와일드카드를 사용해 API 유연성을 높이라

매개변수화 타입은 불공변이다.

따라서 List<String> 은 List<Object> 가 하는 일을 제대로 수행하지 못하니 하위 타입이 될 수 없다.

(리스코프 치환 원칙) 에 어긋난다.

```
매개변수에 와일드카드 타입 적용

public void pushAll(Iterable<? extends E> src) {
    for (E e : src)
        push(e);
}

public void popAll(Collection<? super E> dst) {
    while (!isEmpty())
        dst.add(pop());
}

```

이럴 경우 리스코프 치환 원칙을 지킬 수 있다.

```
Stack<Number> numberStack = new Stack<>();
Iterable<Integer> integers = Arrays.asList(3, 1, 4, 1, 5, 9);

// numberStack 으로 들어간다.
// 생산자 - extends
numberStack.pushAll(integers);
```

## 펙스 (PECS) : producer-extends, consumer-super

- 생산자(super) : stack 으로 데이터가 흘러감 (stack data 생성)
- 소비자(extends) : stack 에서 데이터가 나감 (stack data 소비)

### 명시적 타입 인수

```GenericSingletonFactory.<String>identityFunction();```

### 목표 타이핑

- 목표 타입과 목표 타입 추론

```인터페이스는 다중상속 가능 ```


