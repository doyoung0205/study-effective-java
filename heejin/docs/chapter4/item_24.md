# 클래스와 인터페이스

[아이템 24. 멤버 클래스는 되도록 static으로 만들라](#멤버-클래스는-되도록-static으로-만들라)   
[1) 정적 멤버 클래스](#1-정적-멤버-클래스)   
[2) 비정적 멤버 클래스](#2-비정적-멤버-클래스)   
[3) 익명 클래스](#3-익명-클래스)   
[4) 지역 클래스](#4-지역-클래스)   

<br>

## 멤버 클래스는 되도록 static으로 만들라
- 중첩 클래스(nested class)란 다른 클래스 안에 정의된 클래스를 말한다.
- **중첩 클래스는 자신을 감싼 바깥 클래스에서만 쓰여야 하며, 그 외의 쓰임새가 있다면 톱레벨 클래스로 만들어야 한다.**

### 중첩 클래스의 종류

#### 1) 정적 멤버 클래스
- private 정적 멤버 클래스는 흔히 바깥 클래스가 표현하는 개체의 한 부분(구성요소)을 나타낼 때 쓴다.
  - ex) `Map` 인스턴스의 `getKey`, `getValue`, `setValue`
- 다른 클래스 안에 선언되고, 바깥 클래스의 private 멤버에도 접근할 수 있다.
- 정적 멤버 클래스는 흔히 바깥 클래스와 함께 쓰일 때만 유용한 public 도우미 클래스로 쓰인다.
- 중첩 클래스의 인스턴스가 바깥 인스턴스와 독립적으로 존재할 수 있다면, 정적 멤버 클래스로 만들어야 한다.

```java

public class Calculator {
  private int origin = 1;
  static int result = 2;

  private static void calc(int origin, int result) {
    System.out.println("계산합니다. origin: " + origin + ", result:" + result);
  }

  public static class Operation {
    int origin = 3;
    int result = 4;

    public void test1() {
      Calculator.calc(origin, result);
      System.out.println("[정적 내부 클래스 public] origin = " + origin);  // print: 3
      System.out.println("[정적 내부 클래스 public] result = " + result);  // print: 4
      //Calculator calculator = Calculator.this;  // 컴파일 오류
      System.out.println("[정적 내부 클래스 public] Calculator.result = " + Calculator.result);  // print: 2
    }

    public static void test2() {
      int origin = 5;
      int result = 6;

      Calculator.calc(origin, result);
      System.out.println("[정적 내부 클래스 public static] origin = " + origin);  // print: 5
      System.out.println("[정적 내부 클래스 public static] result = " + result);  // print: 6
      //Calculator calculator = Calculator.this;  // 컴파일 오류
      System.out.println("[정적 내부 클래스 public static] Calculator.result = " + Calculator.result); // print: 2
    }
  }
}
```


#### 2) 비정적 멤버 클래스
- 내부 클래스이다.
- 정적 멤버 클래스와 비정적 멤버 클래스의 구문상 차이는 `static`을 붙이는지에 따라 다르지만, 의미상 차이는 꽤 크다.
- 비정적 멤버 클래스의 인스턴스는 바깥 클래스의 인스턴스와 암묵적으로 연결된다.
- 그래서 비정적 멤버클래스의 인스턴스 메서드에서 정규화된 this를 사용해 바깥 인스턴스의 메서드를 호출하거나 바깥 인스턴스의 참조를 가져올 수 있다.  
  `클래스명.this`
- 비정적 멤버 클래스는 바깥 인스턴스와 독립적으로 존재할 수 없다.
- 비정적 멤버 클래스는 바깥 인스턴스 없이는 생성할 수 없기 때문이다.
  - 바깥 클래스에서 비정적 멤버 클래스의 생성자를 호출할 때 생성되는게 보통이다.
  - 수동으로 생성할 수도 있다. → `바깥_인스턴스의_클래스.new MemberClass(args)`    
    **하지만, 이렇게 생성하면 비정적 멤버 클래스의 인스턴스 안에 만들어져 메모리 공간을 차지하며, 생성 시간도 더 걸린다.**


```java
public class Calculator {
    private int origin = 1;
    static int result = 2;

    private static void calc(int origin, int result) {
        System.out.println("계산합니다. origin: " + origin + ", result:" + result);
    }

    public class Operation2 {
        int origin = 3;
        int result = 4;

        public void test1() {
            Calculator.calc(origin, result);
            System.out.println("[비정적 내부 클래스 public] origin = " + origin);  // print: 3
            System.out.println("[비정적 내부 클래스 public] result = " + result);  // print: 4
            System.out.println("[비정적 내부 클래스 public] Calculator.this.origin = " + Calculator.this.origin);  // print: 1
            System.out.println("[비정적 내부 클래스 public] Calculator.result = " + Calculator.result);  // print: 2
        }

        // static 메서드 불가 - public static void test2() {}
    }
}
```

- 비정적 클래스는 어댑터를 정의할 때 자주 쓰인다.
- 어떤 클래스의 인스턴스를 감싸 마치 다른 클래스의 인스턴스처럼 보이게 하는 `뷰`로 사용하는 것이다. 
  - Map` 인터페이스의 구현체들은 보통 자신의 컬렉션 뷰를 구현할 때 비정적 멤버 클래스를 사용한다. 
  - ex) `keySet`, `entrySet`, `values`


#### 3) 익명 클래스
- 내부 클래스이다.
- 익명 클래스는 바깥 클래스의 멤버가 아니다.
- 쓰이는 시점에 선언과 동시에 인스터스가 만들어지낟.
- 자바가 람다를 지원하기 전, 즉석에서 작은 함수 객체나 처리 객체를 만드는 데 주로 사용했다.
- 익명 클래스의 또 다른 주 쓰임은 정적 팩터리 메서드를 구현할 때다. 

```java
public class IntArrays {
    public static List<Integer> intArrayAsList(int[] a) {
        Objects.requireNonNull(a);

        return new AbstractList<Integer>() {
            @Override
            public Integer get(int index) {
                return a[index]; // 오토박싱
            }

            @Override
            public Integer set(int index, Integer element) {
                int oldElement = a[index];
                a[index] = element; // 오토언박싱
                return oldElement; // 오토박싱
            }

            @Override
            public int size() {
                return a.length;
            }
        };
    }
}
```


#### 4) 지역 클래스
- 내부 클래스이다.
- 지역 클래스는 네 가지 중첨 클래스 중 가장 드물게 사용된다.
- 지역 변수를 선언할 수 있는 곳이면 어디서든 선언할 수 있고, 유효 범위도 지역변수와 같다.


### 정리
- 멤버 클래스에서 바깥 인스턴스에 접근할 일이 없다면 무조건 static을 붙여서 정적 멤버 클래스로 만들자
- static을 생략하면 바깥 인스턴스로 숨은 외부 참조를 갖게 된다.
- 이 경우, 가비지 컬렉션이 바깥 클래스의 인스턴스를 수거하지 못햐는 메모리 누수가 생길 수 있다.

<br>

