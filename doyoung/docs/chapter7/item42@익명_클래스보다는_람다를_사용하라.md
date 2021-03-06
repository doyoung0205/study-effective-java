### 익명 클래스보다는 람다를 사용하라

JDK 1.1 이후부터 함수 객체를 만드는 주요 수단은 익명 클래스가 되었다.

```
Collections.sort(words, new Comparator<String>() {
    public int compare(String s1, String s2) {
        return Integer.compare(s1.length(), s2.length());
    }
});

```

그 이후 JDK 8 버전 부터 메서드 하나짜리 인터페이스를 특별한 의미를 인정받아 특별한 대우를 받았다.
-> 함수형 인터페이스 (@FunctionalInterface)

이 인터페이스들의 인스턴스를 람다식을 사용해 만들 수 있게 되었다.

```
Collections.sort(words, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
```

s1, s2 인 매개변수 타입은 생략한다. 특별히 코드가 더명확할 때만 제외하고는 생략하는 것이 좋다.

```
이 조건들은 람다와 함께 쓸 때는 두 배로 중요해진다. 컴파일러가 타입을 추론하는 데, 필요한 타입 정보 대부분을 
제네릭에서 얻기 때문이다.
```

### 항상 람다를 써야할까?

람다는 이름이 없고 문서화도 못 한다. 따라서 코드 자체로 동작이 명확히 설명되지 않거나 코드 줄 수 가 많아지면 람다를 쓰지 말아햐 한다.

세줄 안에 끝내는 것이 좋다.


### 꼭 익명클래스를 사용해야할 때는?

람다에서의 this 키워드는 바깥 인스턴스를 가리킨다. 반면 익명 클래스에서의 this 는 익명 클래스의 인스턴스 자신을 가르킨다.

따라서 함수 객체가 자신을 참조해야 한다면 익명 클래스를 써야 한다.


