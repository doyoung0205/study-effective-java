## item34 int 상수 대신 열거 타입을 사용하라

### 정수 열거 패턴은 상당히 취약하다
- 타입 안전하지 못하다
- 접두어를 통해 구분할 수 밖에 없다
- 평범한 상수를 나열한 것 뿐이기 때문에 상수 값이 변경 시 클라이언트도 다시 컴파일을 해야 한다
- 상수의 개수가 몇 개인지도 파악하기 어려우며 문자열로 출력하기도 쉽지 않다

### 열거 타입의 장점
- 타입 안전하다
- 싱글톤으로 구현이 가능하다
- 메서드나 필드를 추가할 수 있고 인터페이스를 구현할 수 있다
- 열거 타입 상수 각각을 특정 데이터와 연결지으려면 생성자에서 데이터를 받아 인스턴스 필드에 저장하면 된다
  - 열거 타입은 싱글톤이기 때문에 인스턴스 생성 시 객체를 최적화 하기 위한 작업을 수행할 수 있다

### toString() 
- 열거 타입의 적절한 문자열을 출력할 수 있다

### values()
- 열거 상수들을 선언된 순서로 배열에 담아 반환할 수 있다

### 상수별 메서드 구현
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
- 새로운 기능이 추가되더라도 기존의 기능이 영향받지 않는다

### 전략 열거 타입 패턴
- 새로운 상수를 추가할 때 `전략`을 선택하는 패턴

### 정리
- 필요한 원소를 컴파일 타임에 다 알 수 있는 상수 집합이라면 열거 타입을 사용하자 
- 열거 타입에 정의된 상수 개수가 영히 고정 불변일 필요는 없다
- 하나의 메서드가 상수별로 다르게 동작할 때엔 상수별 메서드 구현을 사용하자
- 열거 상수 일부가 같은 동작을 공유한다면 `전략 열거 타입 패턴`을 사용하자
