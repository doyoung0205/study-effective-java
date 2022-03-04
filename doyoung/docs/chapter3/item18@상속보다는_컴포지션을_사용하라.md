## 상속보다는 컴포지션을 사용하라.

```
여기서 다룰 '상속'은 클래스가 다른 클래스를 확장하는 '구현상속'을 의미한다.
```

### 상속의 주의사항

상속은 코드를 재사용할 수 있는 강력한 수단이지만, 아래와 같은 주의사항이 있다.

1. 상위 클래스의 내부 구현이 하위 클래스에게 영향을 많이 끼친다.
2. 하위 클래스나 상위 클래스의 실수로 메소드 재정의(`오버라이딩`)가 된다면 `캡슐화`를 깨뜨릴 수 있다.

#### 캡슐화란?

item15의 캡슐화 정의를 보면 다음과 같다.

캡슐화, 정보은닉: 오직 API 를 통해서만 다른 컴포넌트와 소통하며 **서로의 내부 동작 방식**에는 전혀 개의치 않는다.

#### 상속의 문제 예시

상속을 사용하게 되면 서로의 내부 동작 방식을 주의깊게 신경써야 하는 상황이 생긴다.

- `me.doyoung.studyeffectivejava.chapter3.item18.InstrumentedHashSet`
- `me.doyoung.studyeffectivejava.chapter3.item18.InstrumentedHashSetTest`

`InstrumentedHashSet.addAll()` 메서드는 ```super.addAll(c);``` 을 통해서 원소를 추가한다.

이는 겉으로는 아무 문제 없어 보이지만, addAll 내부 구현은 ```add``` 메서드를 통해서 원소를 추가한다. 이때  ```add``` 메서드는 super(상위 클래스) 에 있는 add 메서드가 실행되지 않고
`오버라이딩` 된 메서드가 실행되어 `addCount` 값이 예상보다 더 많이 증가하게 된 것이다.

```
@Override public boolean addAll(Collection<? extends E> c) {
    addCount += c.size();
    return super.addAll(c); // --> addAll 내부에 add 메서드는 내부 add 가 아니라 오버라이딩 된 add 가 실행.
}

@Override public boolean add(E e) {
    addCount++;
    return super.add(e);
}

```

### 컴포지션

기존 클래스가 새로운 클래스의 구성요소로 쓰인다는 뜻이다.

- AS-IS

```java
public class MagicNumber extends Number {
  int magic;
}
```

- TO-BE

```java
public class MagicNumber {
  Number numberPrint;
  int magic;
}
```

### 컴포지션 예시

집합 클래스 자신, 전달 클래스

내부 구현 방식의 영향에서 벗어나며, 심지어 기존 클래스에 새로운 메서드가 추가되더라도 전혀 영향받지 않는다.

- `me.doyoung.studyeffectivejava.chapter3.item18.InstrumentedSet` (래퍼 클래스)
  - 컴포지션으로 구현된 ForwardingSet 을 상속받아 오버라이딩 이슈를 해결할 수 있다.
  - 왜냐하면 `ForwardingSet` 의 메소드 전부 `전달 메서드` 이기 떄문이다.
  - 따라서 내부 구현에 대한 이슈가 없어진다.
- `me.doyoung.studyeffectivejava.chapter3.item18.ForwardingSet` (전달 클래스)
  - `Set` 을 구현하기 위한 모든 메서드를 컴포지션을 통해 `Set`에게 위임 및 전달 하였다.

`ForwardingSet` 은 Set 의 있는 메서드 들과 똑같은 시그니처 메서드를 만들어도 오버라이딩 되지 않아
`Set` 으로 부터 내부 구현 방식의 영향에서 벗어 날 수 있다.

## 어려운점

- (p.114) 캡슐화를 깨뜨린다는 것의 개념적 이해
  - 상위 클래스가 어떻게 구현되느냐에 따라 하위 클래스의 동작에 이상이 생길 수 있다.
  - 캡슐화란?
  - **서로의 내부 동작 방식**에는 전혀 개의치 않는다.
- (p.119) 래퍼 클래스의 SELF 문제
  - 참고: `me.doyoung.studyeffectivejava.chapter3.item18.SelfTest.java`
- (p.119) is-a 와 has-a
  - is-a :
    - 사람은 인간이다.
    - 고양이는 동물이다.
    - 게임 캐릭터는 엔티티이다.
  - has-a :
    - 자동차는 배터리를 가지고 있다.
    - 사람은 심장을 가지고 있다.
    - 전투기는 HUD를 가지고 있다.

- (p.117) 전달 클래스는 기존 클래스의 내부 구현 방식의 영향에서 벗어난다?
  - 오버라이딩 되지 않아서
- (p.119) 래퍼 클래스를 사용한 이유
  - 기존에 `InstrumentedHashSet` 처럼 기존에 Set 에서 기능을 덧 붙인 클래스를 만들때 이번엔 상속이 아닌
    <br> Set 을 감싸고 있기 때문에 래퍼 클래스라고 불리는 것이다.

## 참조

- https://unluckyjung.github.io/oop/2021/03/17/Inheritance-and-Encapsulation/
