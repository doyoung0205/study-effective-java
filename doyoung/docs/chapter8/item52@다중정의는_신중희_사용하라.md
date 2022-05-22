### 다중정의는 신중히 사용하라


다중정의 (오버로딩)된 메서드들 중 어느 메서드들이 호출할지는 컴파일 타이메 정해진다.

```
Collection<?>[] collections = {
        new HashSet<String>(),
        new ArrayList<BigInteger>(),
        new HashMap<String, String>().values()
};

for (Collection<?> collection : collections) {
    assertThat(classify(collection)).isEqualTo("그 외");
}
```

따라서 컴파일 시점에는 Collection 이지만 런타임에는 각각 HashSet, ArrayList, HashMap 으로 다르지만
호출할 메서드를 선택하는 데는 영향을 주지 못한다.

따라서 컴파일타임의 매개변수 타입을 기준으로 항상 세 번째 메서드인 classify(Collection<?>) 만 호출하는 것이다.

**다중정의 되었기 때문이다.**

안전하고 보수적으로 가려면 매개변수 수가 같은 다중정의는 만들지 말자.
다중정의하는 대신 메서드 이름을 다르게 지어주는 길도 항상 열려 있다 !


잘못된 예시로
1. 인수가 같은 함수형 인터페이스 (ExecutorService -> submit)
2. 오토박싱과 관련된 다중정의 메서드 (List -> remove)
