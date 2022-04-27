## item36 비트 필드 대신 EnumSet을 사용하라

### 비트 필드란 ?
- 비트별 OR을 사용하여 여러 상수를 하나의 집합으로 표현할 수 있다
    ```java
    public static final int STYLE_BOLD = 1 << 0; //1
    public static final int STYLE_ITALIC = 1 << 1; //2
    public static final int STYLE_UNDERLINE = 1 << 2; //4
    public static final int STYLE_STRIKETHROUGH = 1 << 3; //8
    
    BOLD 이면서 ITALIC 인 문자열 => STYLE_BOLD | STYLE_ITALIC // 3
    ```

### 비트 필드를 대처하는 EnumSet
- 중복값을 허용하지 않는 EnumSet으로 비트 필드를 대체할 수 있다
- 비트 필드의 단점인 `초기에 데이터의 타입(크기)을 지정`해야하는 단점을 효과적으로 개선하였다.


### 정리
- 열거 할 수 있는 타입을 한데 모아 집합 형태로 사용한다고 해도 비트 필드를 사용할 이유는 없다
  - 데이터 타입의 크기의 제한이 없고 코드가 더 직관적이다
- EnumSet의 유일한 단점은 불변 EnumSet 이 없다는 점이다. 
  - `Collections.unmodifiableSet`으로 EnumSet을 감싸서 사용할 수 있다