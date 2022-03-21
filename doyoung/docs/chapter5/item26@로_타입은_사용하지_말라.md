## 로 타입은 사용하지 말라

- `제네릭 인터페이스`, `제네릭 클래스` : 클래스와 인터페이스 선언에 타입 매개변수 (type parameter) 가 쓰인것
- `제네릭 타입` : 제네릭 클래스와 제네릭 인터페이스를 말한다.
- `매개변수화 타입` : List<String> 에서 String 이 매개변수화 타입이다.
- `로 타입` : 제네릭 타입에서 타입 매개변수를 전혀 사용하지 않을 때를 말한다. ex. `List`

이번 장에서는 `로 타입` 은 사용하지 말아야 한다. 그 이유는 `타입 안정성`, `타입 표현력`을 잃기 때문이다.

로 타입의 컬렉션에는 어떤 객체든 들어갈 수 있다. 따라서 컬렉션에서 원소를 꺼내 사용할 때 어떤 타입이 올지 예측 하기 어렵다.

또는 타입을 캐스팅 하는 과정에서 ClassCastException 이 발생 할 수 있다.

```
List somethings = List.of(1, "3");
for (Object someThing : somethings) {
    (String) string // ClassCastException
}
        
```

제네릭을 사용하면 하나의 컬렉션에 다른 타입이 들어갈 때 컴파일로 발견할 수 있다. (`타입 안정성 확보`)

또한

```
List<String> somethings = List.of("1", "3");
for (String someThing : somethings) { // `타입 표현력 확보`
}
        
```

하지만 로 타입이 있는 이유는 이전 제네릭이 나오기 전에 코드들과의 `호환성` 떄문이다.


---

## TIP

타입 안정성을 위해서 매개변수의 타입도 로 타입을 사용하지 말자.

```
void add (Set s1, Set s2) { ... }
```

여기서 s1, s2 는 여러개의 타입을 가지고 있는 Set 일지 모른다. 따라서 안전하지 않다. 이럴 때는 `와일드 카드` 를 사용하면 좋다.

```
void add (Set<?> s1, Set<?> s2) { ... }
```

--

## 로 타입을 사용해야 할 때

1. class 리터럴에는 로 타입을 사용한다.
2. instanceof 연산자는 로타입을 사용한다.
    - 런타임에는 제네릭 타입정보가 지워지므로 instanceof 연산자는 비한정적 와일드카드 타입 이외의 매개변수화 타입에는 적용할 수 없다.

## 용어 정리

| 한글            | 영어                     | 예                                | 아이템       |
|---------------|------------------------|----------------------------------|-----------|
| 매개변수화 타입      | parameterized type     | List<String>                     | 아이템 26    |
| 실제 타입 매개변수    | actual type parameter  | String                           | 아이템 26    |
| 제네릭 타입        | generic type           | List<E>                          | 아이템26, 29 |
| 정규 타입 매개변수    | format type parameter  | E                                | 아이템26     |
| 로 타입          | raw type               | List                             | 아이템26     |
| 한정적 타입 매개변수   | bounded type parameter | <E extends Number>               | 아이템29     |
| 제귀적 타입 한정     | recursive type bound   | <T extends Comparable<T>>        | 아이템 30    |
| 한정적 와일드 카드 타입 | bounded wildcard type  | List<? extends Number>           | 아이템 31    |
| 제네릭 메서드       | generic method         | static <E> List<E> asList(E[] a) | 아이템 31    |
| 타입 토큰         | type token             | String.class                     | 아이템33     |

