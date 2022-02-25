## clone 재정의는 주의해서 진행하라

`Cloneable` : 복제해도 되는 클래스임을 명시하는 용도의 믹스인 인터페이스이다.

#### 문제점

- 복제하는 `clone` 메서드가 `Cloneable` 안에 존재하지 않고 `Object` 클래스안에 있다.
- 따라서 `Cloneable` 을 구현하는 것 만으로 clone 메서드를 호출할 수 없다.
- `clone` 메서드는 public 메서드로 오버라이딩 해야 사용할 수 있다.
- `Cloneable` 를 구현하지 않는 다면 `Object` 의 `clone` 를 호출하면 `CloneNotSupportedException` 이 발생합니다.

```
아래의 코드들의 결과는 항상 참이 나온다.
x.clone() != x 
x.clone().getClass() == x.getClass()
x.clone().equals(x)
```

clone 메서드는 super.clone 을 호출해 얻어야 한다.

----

객체를 clone 을 할 떄, 해당 필드 내용을 정확히 **사용하여** 모든 필드를 초기화합니다. 필드의 내용 자체는 복제되지는 않는다.

따라서 이 메서드는 **"깊은 복사"** 작업이 아니라 이 개체의 **"얕은 복사"** 를 수행합니다.


### 깊은 복사 예시

```
@Override
public StackWithDeepCopy clone() {
    try {
        StackWithDeepCopy result = (StackWithDeepCopy) super.clone();
        result.elements = elements.clone(); // 재귀 overflow
        return result;
    } catch (CloneNotSupportedException e) {
        throw new AssertionError();
    }
}
```

단 위의 코드는 가변객체의 인스턴스 변수들의 final 사용을 못하도록 한다.

----

## 복사 생성자와 복사 팩터리라는 더 나은 객체 복사 방식을 제공할 수 있다.

1. 복사 생성자

```
public Yum(Yum yum) { ... };
```

2. 복사 팩터리

```
public static Yum newInstance (Yum yum) { ... };
```

언어 모순적이고 위험천만한 객체 생성 메커니즘(clone, 생성자를 쓰지 않는 방식)을 사용하지 않으며,

엉성하게 문서화된 규약에 기대지 않고, 정상적인 final 필드 용법과는 충돌하지 않으며,

불필요한 검사 예외 객체를 던지지 않고, 형변환도 필요치 않다.


---
### 배열은 clone 써도돼 !
배열의 clone 은 런타임 타입과 컴파일타임 타입 모두가 원본 배열과 똑같은 배열을 반환한다.

따라서 배열을 복제할 때는 배열의 clone 메서드를 사용하라고 권장한다.
