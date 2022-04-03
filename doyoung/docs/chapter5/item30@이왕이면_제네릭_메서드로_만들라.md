### 이왕이면 제네릭 메서드로 만들라

- 제네릭 메서드 : 매개변수화 타입을 받는 정적 유틸리티 메서드

```
// 로 타입
public static Set union(Set s1, Set s2) {
    Set result = new HashSet<>(s1);
    result.addAll(s2);
    return result;
}
```

- 로 타입으로 할 경우 경고가 발생

타입을 안전하게 만드려면 타입 매개변수 지정해야한다. !

```
// 제네릭 메서드
public static <E> Set<E> union(Set<E> s1, Set<E> s2) {
    Set<E> result = new HashSet<>(s1);
    result.addAll(s2);
    return result;
}
```

---

### 제네릭 싱글턴 팩터리

- 불변 객체를 여러 타입으로 활용할 수 있게 만들어야 할 때 사용
- 요청한 타입 매개 변수에 맞게 매번 그 객체의 타입을 바꿔주는 정적 팩터리

``` 
// Collections.reverseOrder
public static <T> Comparator<T> reverseOrder() {
    return (Comparator<T>) ReverseComparator.REVERSE_ORDER;
}

// Collections.emptySet
public static final <T> Set<T> emptySet() {
    return (Set<T>) EMPTY_SET;
}   
 ```

### 항등 함수

- 입력 값을 수정 없이 그대로 반환하는 특별한 함수
- `제네릭을 통해`  여러 타입으로 변환 가능 !!!

```
public static <T> UnaryOperator<T> identityFunction() {
    return (UnaryOperator<T>) IDENTITY_FN;
}


// String 제네릭으로 변환
UnaryOperator<String> sameString = GenericSingletonFactory.<String>identityFunction();
UnaryOperator<String> sameString = identityFunction();

```

### 재귀적 타입 한정 (recursive type bound)

```
public static <E extends Comparable<E>> E max(Collection<E> c) {
    if (c.isEmpty())
        throw new IllegalArgumentException("컬렉션이 비어 있습니다.");

    E result = null;
    for (E e : c)
        if (result == null || e.compareTo(result) > 0)
            result = Objects.requireNonNull(e);

    return result;
}

```

- `<E extends Comparable<E>>`: "모든 타입 E는 자신과 비교할 수 있다."




