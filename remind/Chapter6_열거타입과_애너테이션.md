# Chapter 6. 열거타입과 애너테이션

---

## Item34 int 상수 대신 열거 타입을 사용하라

### 1.  정수 열거 타입은 안티 패턴이다

```java
//anti
public class Favorites {
    public static final int FRUIT_APPLE = 0;
    public static final int FRUIT_PEACH = 1;
    public static final int FRUIT_PINEAPPLE = 2;
    public static final int FRUIT_WATERMELON = 3;
    public static final int FRUIT_ORANGE = 4;
    public static final int FRUIT_GRAPE = 5;

    public static final int FOOD_HAMBURGER = 0;
    public static final int FOOD_PIZZA = 1;
    public static final int FOOD_CHICKEN = 2;
    public static final int FOOD_SALAD = 3;
    public static final int FOOD_SUSHI = 4;
    public static final int FOOD_T_BONE_STAKE = 5;
```

➖ 타입 안전하지 못하다 

➖ 접두어를 통해 구분할 수 밖에 없다

➖ 상수로 정의한 값의 변경이 필요한 경우 클라이언트에서도 다시 컴파일해야 한다

➖ 상수 개수가 몇 개인지 파악하기 어렵다

### 2.  열거 타입은 정수 열거 타입보다 장점이 많다

```java
public enum Fruit {
    APPLE(0),
    PEACH(1),
    PINEAPPLE(2),
    WATERMELON(3),
    ORANGE(4),
    GRAPE(5);

    private int rank;

    Fruit(int rank) {
        this.rank = rank;
    }
}
```

➕ 타입 안전하다

➕ 싱글톤으로 구현이 가능하다

➕ 메서드나 필드를 추가할 수 있고 인터페이스를 구현할 수 있다

➕ 열거 타입은 싱글톤이기 때문에 인스턴스 생성 시 객체 최적화 작업을 할 수 있다

### 3. 열거 타입은 적절한 문자열 출력을 지원한다.

- name(): 재정의할 수 없으며 필드자체의 이름을 출력합니다
- toString(): 기본적으로 name()와 동일하나 재정의하여 적절한 문자열 표현이 가능합니다
    
    [What is the difference between `Enum.name()` and `Enum.toString()`?](https://stackoverflow.com/questions/18031125/what-is-the-difference-between-enum-name-and-enum-tostring)
    

### 4. 열거 타입은 타입을 배열로 담고 있는 values() 메서드를 지원한다.

```java
public static Fruit myFavoriteFruit() {
        return Arrays.stream(Fruit.values())
                .filter(it -> it.rank == 0)
                .findFirst()
                .orElseThrow(AssertionError::new);
}
```

- 선언한 순서대로 배열이 생성된다

### 5. 열거 타입은 상수별로 메서드를 구현할 수 있다.

```java
public enum Calculator {
    PLUS("+") {
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DEVIDE("/") {
        public double apply(double x, double y) {
            return x / y;
        }
    };

    private final String symbol;

    Calculator(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public abstract double apply(double x, double y);
}
```

- 새로운 상수가 추가되더라도 기존의 코드에 영향을 주지 않는다

### 6. 전략 열거 타입 패턴

전략 패턴은 소프트웨어가 런타임동안 알고리즘의 동작을 변경할  수 있도록 하는 디자인 패턴.

```java
//전략 패턴 예시1. 통근자는 출근 시 운송수단이라는 전략을 선택해서 출근할 수 있다
public interface Transport {
	commute();
}

public class Car implements Transport {}
public class Bus implements Transport {}
public class Subway implements Transport {}
```

전략 패턴 용어 정리??

- `Context`: 전략 패턴을 선택하는 주체 ex) 통근자
- `Strategy`: 클라이언트가 사용함에 있어서 선택하는 알고리즘 ex) 버스, 지하철, 자동차
- `Client`: 전략을 Context에 주입하는 대상 ex) 통근자에게 출근 전략을 추천한 사람

전략 열거 타입 예시1) 출근 전략

```java
public enum CommuteStrategy {

    CAR {
        @Override
        void commute(String content) {
            System.out.println(content + "는 자차로 출근합니다.");
        }
    },
    BUS {
        @Override
        void commute(String content) {
            System.out.println(content + "는 버스로 출근합니다.");
        }
    },
    SUBWAY {
        @Override
        void commute(String content) {
            System.out.println(content + "는 지하철로 출근합니다.");
        }
    };

    abstract void commute(String content);
}
```

```java
public class CommuteContext {
	private CommuteStrategy strategy; 
	
	public CommuteContext(CommuteStrategy strategy) {
		this.strategy = strategy;	
	}

	public void updateStrategy(CommuteStrategy strategy) {
		this.strategy = strategy;
	}

	public void runStrategy() {
		this.strategy.commute();
	}
}
```

```java
public static void main(String[] args) {
	CommuteContext context = new CommuteContext(CommuteStrategy.CAR);
	context.commute();

	context.updateStrategy(CommuteStrategy.SUBWAY);
	context.commute();
}
```

➕ 전략을 싱글톤으로 관리할 수 있다

➕ 새로운 전략을 추가할 때 변경에 유연하다

➖ OCP원칙을 지키지 못한다

### 7. 정리

- 필요한 원소를 컴파일 타임에 다 알 수 있는 상수 집합이라면 열거 타입을 사용하자
- 열거 타입은 정의된 상수 개수가 불변일 필요없고, 무한하게 구현할 수 있다
- 하나의 메서드가 상수별로 다른 동작을 할 떄엔 열거 타입의 `상수별 메서드 구현` 을 사용하자

---

## Item35 ****ordinal() 대신 인스턴스 필드를 사용하라****

### 1. ordinal()은 변경에 안전하지 못한 안티 패턴 입니다.

```java
public enum Characters {
    똘기, 떵이, 호치, 새초미,
    드라고, 요롱이, 마초, 미미,
    몽키, 키키, 강당이, 찡찡이;

    //anti
    public int numberOfCharacters() {
        return ordinal() + 1;
    }
}
```

➖ 열거 타입 정의 순서 변경에 취약하다

### 2. ordinal() 주석 내용

```java
/**
     * Returns the ordinal of this enumeration constant (its position
     * in its enum declaration, where the initial constant is assigned
     * an ordinal of zero).
     *
     * Most programmers will have no use for this method.  It is
     * designed for use by sophisticated enum-based data structures, such
     * as {@link java.util.EnumSet} and {@link java.util.EnumMap}.
     *
     * @return the ordinal of this enumeration constant
     */
    public final int ordinal() {
        return ordinal;
    }
```

- 대부분의 프로그래머는 이 방법을 사용하지 않는다고 정의되어 있다
- `EnumSet` `EnumMap` 과 같은 정교한 열거 기반 데이터 구조에서 사용하도록 정의되어 있다

### 3. 정리

- ordinal()은 정교화된 열거 기반 데이터 구조에서 사용될 목적으로 정의되었다.
- ordinal()은 순서의 변경에 취약하기 때문에 사용을 지양하도록 해야 한다.
- 인스턴스 필드를 활용하여 변경에 안전하게 사용하여야 한다

---

## item36 비트 필드 대신 EnumSet을 사용하라

### 1. 비트 필드란?

 비트별 OR을 사용하여 여러 상수를 하나의 집합으로 표현하는 방법을 말한다

```java
public class Text {
		public static final int STYLE_BOLD = 1 << 0; //1
		public static final int STYLE_ITALIC = 1 << 1; //2
		public static final int STYLE_UNDERLINE = 1 << 2; //4
		public static final int STYLE_STRIKETHROUGH = 1 << 3; //8
}

public static void main(String[] args) {
	/**
         *  해석 : 둘중 하나만 1이면 1 나머지 0
         *  1 | 10  => OR 연산 0000 0001
         *                    0000 0010
         *             RESULT 0000 0011 => 3
         */
        text.addStyles(Text.STYLE_BOLD | Text.STYLE_ITALIC);

        /**
         * 해석 : & => 둘다 1 이면 1 나머지 0
         * 1 & 10  => AND 연산 0000 0001
         *                    0000 0010
         *            RESULT  0000 0000 => 0
         *
         */
        text2.addStyles(Text.STYLE_BOLD & Text.STYLE_ITALIC);
}
```

➖ 타입을 정의하는 시점에 데이터의 크기를 지정해야하기 때문에 변경에 유연하지 못하다

➖ 직관적이지 못하다

### 2.EnumSet은 비트 필드의 기능을 대체할 수 있다

```java
public class SmartText {
    public enum Style {BOLD, ITALIC, UNDERLINE, STRIKETHROUGH}

    private Set<Style> styles;

    public void applyStyles(Set<Style> styles) {
        this.styles = new HashSet<>(styles);
    }

    public Set<Style> getStyles() {
        return styles;
    }
}
```

➕ 코드가 직관적이다

➕ 크기에 제한이 없기 때문에 변경에 유연하다

### 3. 정리

- 비트 필드는 데이터 크기 지정, 가독성이 떨어진다.
- 비트 필드를 대처할 수 있는 `EnumSet` 을 사용하면 단점을 극복할 수 있다

---

## Item37 ordinal 인덱싱 대신 EnumMap을 사용하라

### 1. ordinal 인덱싱은 대신할 EnumMap

EnumMap이란 ? `Enum` 타입을 Key로 사용하는 컬렉션이다. 열거형 맵의 키는 열거형 상수가 선언된 순서로 유지된다. 

```java
public enum DayOfWeek {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
```

```java
//expect {MONDAY=1, TUESDAY=1, WEDNESDAY=1, THURSDAY=1, FRIDAY=1, SATURDAY=1, SUNDAY=1}
public static void main(String[] args) {
        EnumMap<DayOfWeek, Integer> activityMap = new EnumMap<>(DayOfWeek.class);

        activityMap.put(DayOfWeek.FRIDAY, 1);
        activityMap.put(DayOfWeek.SATURDAY, 1);
        activityMap.put(DayOfWeek.SUNDAY, 1);
        activityMap.put(DayOfWeek.WEDNESDAY, 1);
        activityMap.put(DayOfWeek.TUESDAY, 1);
        activityMap.put(DayOfWeek.THURSDAY, 1);
        activityMap.put(DayOfWeek.MONDAY, 1);
}
```

### 2. EnumMap 내부 구현은 배열로 되어있으며, 정의된 순서대로 구현되어 있다

```java
/**
     * Array representation of this map.  The ith element is the value
     * to which universe[i] is currently mapped, or null if it isn't
     * mapped to anything, or NULL if it's mapped to null.
     */
    private transient Object[] vals;
```

### 3. 정리

- ordinal() 메서드의 사용은 안티패턴이며, EnumMap은 enum 타입의 정의된 순서로 인덱싱을 제공한다.
- EnumMap의 내부 구현은 배열로되어 있으며, Map의 키가 `Enum` 타입이고 자체 출력을 제공한다
- 배열의 인덱스를 얻기 위해 ordinal() 사용하는 것보다는 EnumMap을 사용하도록 하자

---

## Item38 확장할 수 있는 열거 타입이 필요하면 인터페이스를 사용하라

### 1.확장이 가능한 열거타입

```java
public interface Operation {
    double apply(double x, double y);
}
```

```java
public enum BasicOperation implements Operation {
    PLUS("+") {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DEVIDE("/") {
        @Override
        public double apply(double x, double y) {
            return x / y;
        }
    },
    EXP("^") {
        @Override
        public double apply(double x, double y) {
            return Math.pow(x, y);
        }
    },
    REMAINDER("%") {
        @Override
        public double apply(double x, double y) {
            return x % y;
        }
    };

    private final String symbol;

    BasicOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
```

➕ `Enum` 타입은 인터페이스를 구현할 수 있다

➕ 인터페이스를 구현한 `상수별 메서드` 패턴이며, 추상화할 수 있다

➕ 연산코드(opcode)와 같이 각기 다른 메서드 구현이 필요한 곳에 적합하다

### 2. 정리

- 열거 타입 자체를 확장할 수 없으니, 인터페이스나 인터페이스를 구현하는 기본 열거 타입을 사용하여 추상화를 사용할 수 있다

---

## Item39 명명패턴보다 애너테이션을 사용하라

### 1. 명명패턴

JUnit 3.8 에서는 Naming Conventions 를 사용하였는데 `Class` `Method` 단위의 이름에 따라 일정한 기능을 수행하도록 정의한 패턴을 말한다.

[What Are JUnit 3.8 Naming Conventions?](https://interviewquestionsanswers.org/__What-Are-JUnit-38-Naming-Conventions)

➖ 오타가 났을 경우 실행되지 않기 때문에 타입 세이프티하지 못한 패턴이다

➖ 올바른 프로그램 요소에서 사용된다는 보장을 할 수 없다

➖ 프로그램 요소로 매개변수를 전달할 수 없다

### 2. 애너테이션

자바 소스코드에 추가하여 사용할 수 있는 메타데이터. 소스 코드에 영향을 주지 않는다

- 애너테이션 용어 정리
    - 메타(Meta) 애너테이션: 애너테이션 선언에 다는 애너테이션.
        - `@Retention(RetentionPolicy.RUNTIME)` `Target(ElementType.METHOD)`
    - 마커(Marker) 애너테이션: 아무 매개변수 없이 마킹한다는 개념의 애너테이션.
        - `@Override`
    - 컨테이너(Container) 애너테이션: 반복 가능 애너테이션(`@Repeatable`)이 여러 개 사용될 경우 사용되는 컨테이너 애너테이션

➕ 타입 세이프티하다.

➕ 소스 코드에 영향을 주지 않는다.

➕ 매개변수를 전달 할 수 있다.

### 3. 정리

- 명명패턴은 타입 세이프티하지 못하고 안정성을 보장하지 못한다
- 애노테이션은 소스 코드에 영향을 미치지 않고 타입 안정성을 가지는 메타데이터 구조이다.
- 명명패턴보다 애노테이션 사용을 권장한다

---

## Item40 @Override 애너테이션을 일관되게 사용하라

### 1. @Override 애너테이션은 상위 타입의 메서드를 재정의했음을 의미한다

`@Override` 애너테이션은 메서드 레벨에만 사용이 가능하며 컴파일러에게 상위 메서드를 재정의했다라는 것을 알리는 역할을 한다. 명시적으로 메서드가 재정의되었음을 알리는 대표적인 마커 애너테이션이다. 

```java
/**
 * Indicates that a method declaration is intended to override a
 * method declaration in a supertype. If a method is annotated with
 * this annotation type compilers are required to generate an error
 * message unless at least one of the following conditions hold:
 *
 * <ul><li>
 * The method does override or implement a method declared in a
 * supertype.
 * </li><li>
 * The method has a signature that is override-equivalent to that of
 * any public method declared in {@linkplain Object}.
 * </li></ul>
 *
 * @author  Peter von der Ah&eacute;
 * @author  Joshua Bloch
 * @jls 8.4.8 Inheritance, Overriding, and Hiding
 * @jls 9.4.1 Inheritance and Overriding
 * @jls 9.6.4.4 @Override
 * @since 1.5
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Override {
}
```

- 조건1. 메서드는 상위 유형에 선언된 메서드를 재정의하거나 구현해야 한다
- 조건2. 메서드에는 `Object` 에 선언된 공용 메서드의 서명과 동일한 재정의 서명이 있다

### 2. 정리

- `@Override` 는 상위 메서드를 재정의했음을 컴파일러에게 알리는 메서드 레벨의 애너테이션이다.
- 상위 클래스의 메서드를 재정의하는 경우 필수적으로 사용하여 컴파일 시점에서 오류를 미리 파악할 수 있다.
- 구체 클래스에서 상위 클래스의 추상메서드를 재정의한 경우에는 생략이 가능하다

---

## Item41 정의하려는 것이 타입이라면 마커 인터페이스를 사용하라

### 1. 마커 인터페이스(Marker Interface)란?

- 클래스가 특정 속성을 가짐을 표시해주는 인터페이스
- 마커 인터페이스는 이를 구현한 클래스의 인스턴스를 구분하는 타입으로 사용한다
- `Serializable` `Cloneable`

### 2. 마커 인터페이스 사용 예시1) java.io.Serializable

```java
//클래스의 직렬화 가능 여부를 표시하는 마커 인터페이스
public interface Serializable {
}
```

```java
//ObjectOutputStream
public final void writeObject(Object o) throws IOException;

//ObjectInputStream
public final Object readObject() throws IOException, ClassNotFoundException;
```

- `Serializable` 인터페이스를 구현하지 않는 경우 NotSerializableException 예외가 발생한다

[](https://www.baeldung.com/java-serialization)

### 3. 마커 인터페이스와 마커 애너테이션은 언제 사용하는가

- `클래스, 인터페이스 외의 프로그램 요소에 마킹` 할 경우 `마커 애너테이션`을 사용한다
- `마킹된 객체를 매개변수`로 받는 메서드가 있는 경우 `마커 인터페이스` 를 사용한다

### 4. 정리

- 단지 타입 정의가 목적이라면 `마커 인터페이스` 를 사용하자
- `ElementType.TYPE` 인 경우 `마커 인터페이스` 를 사용하자

---