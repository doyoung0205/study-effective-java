# 람다와 스트림

[아이템 42. 익명 클래스보다는 람다를 사용하라](#익명-클래스보다는-람다를-사용하라)  
[- 정리](#정리)

<br>

## 익명 클래스보다는 람다를 사용하라
- 익명 클래스의 인스턴스를 함수 객체로 사용하는 기법은 낡은 기법이다.
    ```java
    Collections.sort(words, new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return Character.compare(o1.charAt(0), o2.charAt(0));
        }
    });
    ```

- 지금은 함수형 인터페이스라 부르는 인터페이스들의 인스턴스를 람다식을 사용해 만들 수 있다.
    ```java
  Collections.sort(words, (o1, o2) -> Character.compare(o1.charAt(0), o2.charAt(0)));  
    ```
  - 람다를 사용하면, 매개변수 `Comparator<String>`, 반환타입 `int` 에 대해 컴파일러가 타입을 추론해 준다. (컴파일러가 타입을 결정하지 못하는 경우에는, 직접 명시하면 된다.)

- 람다 자리에 비교자 생성 메서드를 사용하면 코드를 더 간결하게 만들 수 있다.
   ```java
  Collections.sort(words, comparingInt(o -> o.charAt(0)));
    ```
  - 타입을 명시해야 코드가 더 명확할 때만 제외하고, 람다의 모든 매개변수 타입은 생략하자.

- 더 나아가 자바 8에서 추가된, List 인터페이스의 sort 메서드를 이용하면 더 짧아진다.
   ```java
  words.sort(comparingInt(o -> o.charAt(0)));
    ```

#### 람다의 사용
- 람다를 언어 차원에서 지원하면서 기존에는 적합하지 않았던 곳에서도 함수 객체를 실용적으로 사용할 수 있게 되었다.
- [함수 객체(람다)를 인스턴스 필드에 저장해 상수별 동작을 구현한 열거 타입](../../src/main/java/study/heejin/chapter7/item42/Operation.java)
- 메서드나 클래스와 달리, 람다는 이름이 없고 문서화도 못한다. 따라서 코드 자체로 동작이 명확히 설명되지 않거나 코드 줄 수가 많아지면 람다를 쓰지 말아야 한다.
- 람다는 함수형 인터페이스에서만 쓰인다.
  - 추상 클래스의 인스턴스를 만들 때 람다를 쓸 수 없으니 익명클래스를 써야 한다.
  - 추상 메서드가 여러 개인 인터페이스의 인스턴스를 만들 때도 익명 클래스를 써야 한다.
- 람다는 자신을 참조할 수 없다.
  - 람다에서의 `this` 키워드는 바깥 인스턴스를 가리킨다.
  - 익명 클래스에서의 `this`는 익명 클래스의 인스턴스 자신을 가리킨다.
- 람다도 익명 클래스처럼 직렬화 형태가 구현별로 다를 수 있다. 따라서 람다와 익명클래스를 직렬화하는 일은 극히 삼가야 한다.


### 정리
- 익명 클래스는 (함수형 인터페이스가 아닌) 타입의 인스턴스를 만들 때만 사용하라.


<br>