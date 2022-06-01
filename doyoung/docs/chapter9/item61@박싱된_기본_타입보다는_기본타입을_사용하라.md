### 박싱된 기본 타입보다는 기본타입을 사용하라

박싱 기본타입과 기본타입 차이점

1. 기본 타입은 값만 가지고 있으나 박싱 기본타입은 식별성이란 값을 갖는다. 따라서 값은 같아도 다르다고 식별될 수 있다. (`new Integer(1) != new Integer(1)`)
2. 박싱 기본타입은 null 을 가질 수 있다. 따라서 기본타입으로 언박싱되는 순간 `NullPointException` 이 발생할 수 있다.
3. 기본 타입은 시간과 메모리 사용면에서 더 효율적이다.

#### 비교자 조심하기

```
Comparator<Integer> naturalOrder = (i, j) -> (i < j) ? -1 : (i == j ? 0 : 1);
```

첫번쨰 검사에서는 부등호가 있어 자동으로 컴파일러가 언박식해준다.

[참조](https://stackoverflow.com/questions/44427682/why-does-work-with-wrapper-classes-while-doesnt)

두번째 검사에서는 두객체의 참조 즉 식별성을 검사 하기 떄문에 == 이 값이 같더라도 항상 false 가 발생한다.

따라서 이렇게 수정하자

```
Comparator<Integer> naturalOrder = (iBoxed, jBoxed) -> {
    int i = iBoxed, j = jBoxed; // 오토박싱
    return i < j ? -1 : (i == j ? 0 : 1);
}
```

### 오토박싱 때문에 엄청 느린 상황

```
public static void main(String[] args) {
    Long sum = 0L;
    for (int i = 0; i < Integer.MAX_VALUE; i++) {
        sum += 1; // 병목지점 !
    }
    System.out.println(sum);
}
```

그렇다면 박싱된 기본 타입은 언제 써야 할까?

첫 번째, 컬렉션의 원소, 키, 값으로 쓴다. 컬렉션은 기본 타입을 담을 수 없음으로 어쩔 수 없이 박싱된 기본타입을 써야한다.





