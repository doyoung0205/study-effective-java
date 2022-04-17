# 열거 타입과 애너테이션

[아이템 36. 비트 필드 대신 EnumSet을 사용하라](#비트-필드-대신-enumset을-사용하라)  

<br>

## 비트 필드 대신 EnumSet을 사용하라
- 열거한 값들이 집합으로 사용될 경우, 각 상수에 서로 다른 2의 거듭제곱 값을 할당한 정수 열거 패턴을 사용해왔다.
```java
public class BitText {
    public static final int STYLE_BOLD = 1 << 0;            // 1
    public static final int STYLE_ITALIC = 1 << 1;          // 2
    public static final int STYLE_UNDERLINE = 1 << 2;       // 4
    public static final int STYLE_STRIKETHROUGH = 1 << 3;   // 8

    public void applyStyles(int styles) {
        System.out.printf("Applying styles %s to text %n", Objects.requireNonNull(styles));
    }

    public static void main(String[] args) {
        BitText text = new BitText();
        text.applyStyles(STYLE_BOLD | STYLE_ITALIC); // Applying styles 3 to text
    }
}
```
- 위와 같이 비트별 OR를 사용해 여러 상수를 하나의 집합으로 모을 수 있으며, 이렇게 만들어진 집합을 비트 필드라 한다.
- 비트 필드를 사요하면 비트별 연산을 사용해 합집합과 교집합 같은 집합 연산을 효율적으로 수행할 수 있다.
- 하지만, 비트 필드는 정수 열거 상수의 단점을 그대로 지니며, 정수 열거 상수 보다 해석하기가 훨씬 어렵다.
- 또한, 최대 몇 비트가 필요한지를 API 작성 시 미리 예측하여 적절한 타입(보통 int나 long)을 선택해야 한다.


### EnumSet
- java.util 패키지의 EnumSet 클래스는 열거 타입 상수의 값으로 구성된 집합을 효과적으로 표현해준다.
- Set 인터페이스를 완벽히 구현하며, 타입 안전하고, 다른 어떤 Set 구현체와도 함께 사용할 수 있다.
```java
public class Text {
  public enum Style {
      BOLD, ITALIC, UNDERLINE, STRIKETHROUGH
    }

  public void applyStyles(Set<Style> styles) {
          System.out.printf("Applying styles %s to text %n", Objects.requireNonNull(styles));
    }

  public static void main(String[] args) {
          Text text = new Text();
          text.applyStyles(EnumSet.of(BOLD, ITALIC)); // Applying styles [BOLD, ITALIC] to text
    }
}
```
- EnumSet 내부는 비트 벡터로 구현되었다.  
  원소가 총 64개 이하라면, 즉 대부분의 경우에 EnumSet 전체를 long 변수 하나로 표혀하여 비트 필드에 비견되는 성능을 보여준다.
  > 비트 벡터란 중복되지 않는 정수 집합을 비트로 나타내는 방식이다.
  8bit가 1byte이므로 0 ~ 7까지의 8개의 정수 집합은 고작 1byte 공간만 사용하여 데이터를 저장할 수 있다. 따라서 비트 벡터를 이용하면 최소의 저장 공간만 이용하는 장점이 있다.
- `removeAll`과 `retainAll` 같은 대량 작업은 비트를 효율적으로 처리할 수 있는 산술 연산을 써서 구현했다.
- EnumSet의 유일한 단점이라면 불변 EnumSet을 만들 수 없다는 것이다. `Collections.unmodifiableSet`으로 EnumSet을 감싸 사용할 수 있다.


<br>

---
#### Reference

- [비트 벡터(Beat Vector)](https://velog.io/@jimmy48/%EB%B9%84%ED%8A%B8-%EB%B2%A1%ED%84%B0Beat-Vector)

