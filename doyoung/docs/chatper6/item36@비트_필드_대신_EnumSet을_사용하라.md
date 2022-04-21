### 비트 필드 대신 EnumSet 을 사용하라.

```
public class Text {
    public static final int STYLE_BOLD = 1 << 0;
    public static final int STYLE_ITALIC = 1 << 1;
    public static final int STYLE_UNDERLINE = 1 << 2;
    public static final int STYLE_STRIKETHROUGH = 1 << 3;
    
    public void applyStyles(int styles) { ... }
}
```

예전에는 위와 같은 방식으로 정수 열거 패턴을 사용했었다.

거듭 제곱을 사용하는 이유는 다양한 경우의 수 를 만들기 위해서 이다.

### 단점

- 비트값이 숫자 이기 때문에 알아보기 힘들다.
- 타입이 정해져있기 때문에 크기에 제약이 있다.(나중에 커지면 타입을 바꿔주어야 함.)

### 대안 EnumSet

EnumSet 의 내부는 비트 백터 로 구현되었고 비트를 직접 다룰 때 난해한 작업들을 다 처리해준다.

```
public class Text {
    public enum Style {BOLD, ITALIC, UNDERLINE, STRIKETHROUGH}

    // 어떤 Set을 넘겨도 되나, EnumSet이 가장 좋다.
    public void applyStyles(Set<Style> styles) { ... }

    // 사용 예
    public static void main(String[] args) {
        Text text = new Text();
        final EnumSet<Style> styles = EnumSet.of(Style.BOLD, Style.ITALIC);
        text.applyStyles(styles);
    }
}

```
