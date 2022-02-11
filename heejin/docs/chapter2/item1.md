# 객체 생성과 파괴

[아이템 1. 생성자 대신 정적 팩터리 메서드를 고려하라](#생성자-대신-정적-팩터리-메서드를-고려하라)

## 생성자 대신 정적 팩터리 메서드를 고려하라

- public 생성자를 통해 클래스의 인스턴스를 얻는다.
- 생성자와 별도로 그 클래스의 인스턴스를 반환하는 정적 팩터리 메서드를 제공할 수 있다.  
  (여기서, 정적 팩터리 메서드는 디자인 패턴에서의 팩터리 메서드와 다르다.)
- 클래스는 public 생성자 대신 (혹은 생성자와 함께) 정적 책터리 메서드를 제공할 수 있다.

### 정적 팩터리 메서드의 장점

1. 이름을 가질 수 있다.
    - 생성자의 매개변수와 생성자 자체만으로는 반환될 객체의 특성을 제대로 설명하지 못한다.
    - 정적 팩터리 메서드는 이름만 잘 지으면 반환될 객체의 특성을 쉽게 묘사할 수 있다.
       ```java
       // 이 생성자는 어떤 인스턴스를 반환할지 파악하기 어려움
       BigInteger bigInteger = new BigInteger(5, 2, new Random());
       
       // 소수 값의 BigInteger를 반환한다는 의미를 담고 있음
       BigInteger primeBigInteger = BigInteger.probablePrime(5, new Random());
       ```
    - 한 클래스에 시그니처가 같은 생성자가 여러 개 필요할 것 같으면, 생성자를 정적 팩터리 메서드로 바꾸고 각각의 차이를 잘 드러내는 이름을 지어주자.


2. 호출될 때마다 인스턴스를 새로 생성하지 않아도 된다.
    - 불변 클래스는 인스턴스를 미리 만들어 놓거나 새로 생성한 인스턴스를 캐싱하여 재활용하는 식으로 불필요한 객체 생성을 피할 수 있다.
       ```java
       // 반복되는 요청에 같은 객체를 반환
       public final class Boolean implements java.io.Serializable, Comparable<Boolean> {
          public static final Boolean TRUE = new Boolean(true);
          public static final Boolean FALSE = new Boolean(false);
          public static Boolean valueOf(boolean b) {
             return (b ? TRUE : FALSE);
          }
          public static Boolean valueOf(String s) {
              return parseBoolean(s) ? TRUE : FALSE;
          }
       }
       ```
    - 같은 객체가 자주 요청되는 상황이라면 성능을 상당히 끌어올려 준다.
    - 플라이웨이트 패턴도 이와 비슷한 기법이라 할 수 있다.
    - 반복되는 요청에 같은 객체를 반환하는 식으로 정적 팩터리 방식의 클래스는 언제 어느 인스턴스를 살아 있게 할지 통제할 수 있다.  
      이런 클래스를 인스턴스 통제 클래스라고 한다.  
      인스턴스를 통제하면 클래스를 싱글턴으로 만들 수도 있고, 인스턴스화 불가로 만들 수도 있다.  
      또한 불변 값 클래스에서 동치인 인스턴스가 단 하나뿐임을 보장할 수 있다. (a = b 일 때만 a.equals(b)가 성립)


3. 반환 타입의 하위 타입 객체를 반환할 수 있다.
    - 반환할 객체의 클래스를 자유롭게 선택할 수 있게 하는 유연성을 제공한다.
    - API를 만들 때 이 유연성을 응용하면 구현 클래스를 공개하지 않고도 객체를 반환할 수 있어 API를 작게 유지할 수 있다.
       ```java
       public class Coffee {
          private String bean;
      
          public static Espresso espresso() {
             return new Espresso();
          }
       }
       
       class Espresso extends Coffee { }
       ```


4. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다.
    - 반환 타입의 하위 타입이기만 하면 어떤 클래스의 객체를 반환하든 상관없다.
       ```java
       public class Coffee {
           private String bean;
       
           public static Coffee espresso(String bean) {
               if (bean.equals("Kenya")) {
                   return new EspressoKenya();
               }
               if (bean.equals("Brazil")) {
                   return new EspressoBrazil();
               }
               return new Espresso();
           }
       }
       
       class Espresso extends Coffee { }
       class EspressoKenya extends Coffee { }
       class EspressoBrazil extends Coffee { }
      
       ```
       ```java
       public static <E extends Enum<E>> EnumSet<E> noneOf(Class<E> elementType) {
          Enum<?>[] universe = getUniverse(elementType);
          if (universe == null)
              throw new ClassCastException(elementType + " not an enum");
  
          if (universe.length <= 64)
              return new RegularEnumSet<>(elementType, universe);
          else
              return new JumboEnumSet<>(elementType, universe);
       }
       ```


5. 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.
    - 서비스 제공자 프레임워크를 만드는 근간이 되는 유연함을 제공한다.
    - 서비스 제공자 프레임워크에서 제공자(provider)는 서비스의 구현체다. 그리고 이 구현체들을 클라이언트에 제공하는 역할을 프레임워크가 통제하여, 클라이언트를 구현체로부터 분리해준다.
    - 서비스 제공자 프레임워크는 3개의 핵심 컴포넌트로 이루어진다.
        - (1) 서비스 인터페이스 : 구현체의 동작을 정의
        - (2) 제공자 등록 API : 제공자가 구현체를 등록할 때 사용
        - (3) 서비스 접근 API : 클라이언트가 서비스의 인스턴스를 얻을 때 사용
        - (+) 서비스 제공자 인터페이스 : 서비스 인터페이스의 인스턴스를 생성하는 팩터리 객체를 설명 (3개의 핵심 컴포넌트와 더불어 네 번째 컴포넌트가 쓰이기도 한다.)
    - 서비스 접근 API가 바로 서비스 제공자 프레임워크의 근간이라고 한 유연한 정적 팩터리이다.
    - 대표적인 서비스 제공자 프레임워크는 JDBC가 있다.
        - 서비스 인터페이스 : Connection
        - 제공자 등록 API: DriverManager.registerDriver()
        - 서비스 접근 API : DriverManager.getConnection()
        - 서비스 제공자 인터페이스 : Driver
          ```java
          public interface Connection  extends Wrapper, AutoCloseable { }
          ```
          ```java
          Connection conn = null;
          try {
             conn = DriverManager.getConnection("jdbcDriver", "dbUser", "dbPw");
          
          } catch (Exception e) {
             // 에러 처리
          } finally {
             // 자원 닫기
          }
          ```
    - 서비스 제공자 프레임워크 패턴에는 여러 변형이 있다.
        - 브리지 패턴 : 서비스 접근 API는 공급자가 제공하는 것보다 더 풍부한 서비스 인터페이스를 클라이언트에 반환할 수 있다.
        - 의존 객체 주입(DI) 프레임워크도 서비스 제공자라고 생각할 수 있다.
        - 자바 6부터는 `java.util.ServiceLoader`라는 범용 서비스 제공자 프레임워크가 제공되어 프레임워크를 직접 만들 필요가 거의 없어졌다.

### 정적 팩터리 메서드의 단점

1. 상속을 하려면 public이나 protected 생성자가 필요한데, 정적 팩터리 메서드만 제공하면 하위 클래스를 만들 수 없다.
   - 어찌보면 이 제약은 상속보다 컴포지션을 사용하도록 유도하고 불변 타입으로 만들려면 이 제약을 지켜야 한다는 점에서 오히려 장점으로 받아들일 수도 있다.

2. 정적 팩터리 메서드는 프로그래머가 찾기 어렵다.
    - 생성자처럼 API 설명에 명확히 드러나지 않으니, API 문서에 정적 팩터리 방식 클래스를 인스턴스화 할 방법을 정리한다.
    - 정적 팩터리 메서드 이름을 널리 알려진 규약을 따라 짓는 식으로 문제를 완화한다.
      - `from`: 매개변수를 하나 받아서 해당 타입의 인스턴스를 반환   
        `Date d = Date.from(instant);`
      - `of`: 여러 매개변수를 받아 적합한 타입의 인스턴스를 반환  
        `Set<Rank> faceCards = EnumSet.of(JACK, QUEEN, KING);`
      - `valueOf`: from과 of의 더 자세한 버전  
        `BigInteger prime = BigInteger.valueOf(Integer.MAX_VALUE);`
      - `instance 혹은 getInstance`: 매개변수로 명시한 인스턴스를 반환하지만, 같은 인스턴스임을 보장하지는 않음  
        `StackWalker luke = StackWalker.getInstace(options);`
      - `create 혹은 newInstance`: instance 혹은 getInstance와 같지만, 매번 새로운 인스턴스를 생성해 반환함을 보장  
        `Object newArray = Array.newInstance(classObject, arrayLen);`
      - `getType`: getInstace와 같으나, 생성할 클래스가 아닌 다른 클래스에 팩터리 메서드를 정의할 때 사용  
        `FileStore fs = Files.getFileStore(path);` 
      - `newType`: newInstance와 같으나, 생성할 클래스가 아닌 다른 클래스에 팩터리 메서드를 정의할 떄 사용  
        `BufferedReader br = Files.newBufferedReader(path);` 
      - `type`: getType과 newType의 간결한 버전  
        `List<Complaint> litany = Collections.list(legacyLitany);`

        
<br>

---
#### Reference

- [[Effective Java] item1 - 생성자 대신 정적 팩토리 메서드를 고려하라.](https://vsfe.tistory.com/13")
