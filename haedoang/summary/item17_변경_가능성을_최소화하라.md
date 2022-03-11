### item17. 변경 가능성을 최소화하라
- `불변 클래스`는 인스턴스의 내부 값을 수정할 수 없는 클래스를 말한다. 다음과 같은 규칙을 가진다
  1. 객체의 상태를 변경하는 메서드(변경자)를 제공하지 않는다 
  2. 클래스를 확장하지 않는다 
  3. 모든 필드를 final로 선언한다
  4. 모든 필드를 private로 선언한다
  5. 자신 외에는 내부의 가변 컴포넌트에 접근할 수 없도록 한다
- 불변 클래스는 `함수형 프로그래밍` 메서드를 사용해야 한다.
  - 함수형 프로그래밍이란 인스턴스 자신을 수정하지 않고 새로운 인스턴스를 반환하는 것을 말하며, 이는 불변 객체에 사용하기 적합하다
  - add, remove 같은 동사형 메서드는 인스턴스를 수정함을 의미하기 떄문에 `plus`, `minus` 등의 전치사를 사용하자
- 불변 객체는 단순하며 근본적으로 스레드 안전하여 동기화할 필요가 없다
- 불변 객체는 안심하고 공유할 수 있다. 
- 불변 객체는 자유롭게 공유할 수 있음은 물론, 불변 객체끼리는 내부 데이터를 공유할 수 있다
  - 내부 데이터란 오브젝트 단위 내에서를 말하며 자유롭게 공유한다라는 것은 변경될 여지가 없는 값이기 때문에 공유해도 문제가 없다는 뜻이다
- 불변 객체는 그 자체로 실패 원자성을 제공한다 
- 모든 생성자를 private 혹은 package-private로 만들고 public 정적 팩터리를 제공한다
  - 필요에 따라 정적 팩터리는 기존의 오브젝트를 캐싱하여 불필요한 객체 생성을 막아줄 수 있다
- 클래스는 꼭 필요한 경우가 아니라면 불변이어야 한다
- 불변으로 만들 수 없는 클래스라도 변경할 수 있는 부분을 최소한을 줄이자
- 합당한 이유가 없다면 모든 필드는 private final 이어야 한다
- 생성자는 불변식 설정이 모두 완료된, 초기화가 완벽히 끝난 상태의 객체를 생성해야 한다


#### 불변 객체의 단점
- 값이 다르면 반드시 독립된 객체로 만들어야 한다
- BigInteger와 같이 비용이 큰 오브젝트가 가지는 값이 많다면 효율적이지 못하다

#### 해결 방법은?
1) 흔히 쓰일 다단계 연산들을 예측하여 기본 기능으로 제공하는 방법
   - package-private 가변 동반 클래스
   - ex) BigInteger 클래스의 `MutableBigInteger`, `SignedMutableBigInteger`
2) 가변 동반 클래스를 public으로 제공하는 방법
   - ex) String 클래스의 `StringBuffer`

#### BigInteger & BigDecimal 인스턴스 주의점
- 두 클래스는 메서드를 재정의할 수 있게 설계되었다. 
- 이와 같은 인스턴스를 인수로 받을 때는 반드시 객체를 검증해야 한다  
  ```text
  public static BigInteger safeInstance(BigInteger val) {
      return val.getClass() == BigInteger.class ?
          val : new BigInteger(val.toByteArray());
  } 
  ```

#### 불변 클래스에서 final이 아닌 변수로 캐시 기능을 구현할 수 있다
- String 클래스의 hashCode
  ```text
  public int hashCode() {
      int h = hash;
      if (h == 0 && value.length > 0) {
          hash = h = isLatin1() ? StringLatin1.hashCode(value)
                                : StringUTF16.hashCode(value);
      }
      return h;
  }
  ```
  


###  다단계 연산들을 예측하여 기본 기능으로 제공하는 방법 예시 1. MutableBigInteger
- BigInteger 클래스의 `MutableBigInteger`
- BigInteger는 객체 자체로 비용이 크기 때문에 함수형 프로그래밍을 적용한 BigInteger 객체는 값의 변화가 많이 발생하는 알고리즘은 성능에 취약하다
- 이러한 단점을 개선하기 위해 다단계 연산을 제공하기 위해 MutableBigInteger 오브젝트를 지원한다
    ```java
    /**
     * A class used to represent multiprecision integers that makes efficient
     * use of allocated space by allowing a number to occupy only part of
     * an array so that the arrays do not have to be reallocated as often.
     * When performing an operation with many iterations the array used to
     * hold a number is only reallocated when necessary and does not have to
     * be the same size as the number it represents. A mutable number allows
     * calculations to occur on the same number without having to create
     * a new number for every step of the calculation as occurs with
     * BigIntegers.
     *
     * @see     BigInteger
     * @author  Michael McCloskey
     * @author  Timothy Buktu
     * @since   1.3
     */
    
    import static java.math.BigDecimal.INFLATED;
    import static java.math.BigInteger.LONG_MASK;
    import java.util.Arrays;
    
    class MutableBigInteger {
        
    }
    ```
- 클래스 설명 번역본(google translation)
    ```text
    배열이 자주 재할당되지 않도록 숫자가 배열의 일부만 차지하도록 하여 할당된 공간을 효율적으로 사용하는 다중 정밀도 정수를 나타내는 데 사용되는 클래스입니다.
    많은 반복 작업을 수행할 때 숫자를 유지하는 데 사용되는 배열은 필요할 때만 재할당되며 숫자가 나타내는 크기와 같을 필요는 없습니다. 
    변경 가능한 숫자를 사용하면 BigIntegers에서 발생하는 것과 같이 계산의 모든 단계에 대해 새 숫자를 만들 필요 없이 동일한 숫자에서 계산을 수행할 수 있습니다.
    ```

#### BigInteger & BigDecimal 인스턴스 주의점
- BigInteger, BigDecimal 객체가 나왔을 때 불변 클래스로 만드는 것을 고려하지 않았다
- 따라서 해당 객체는 재정의해서 사용할 수 있고, 그렇기 때문에 신뢰하지 못하는 객체로 남게 되었다.
- 주의할 사항은 불확실한 클라이언트로부터 해당 객체들을 받을 경우 객체 검증을 하도록 해야 한다(가변 객체일 수 있기 때문에, 방어적 복사를 하도록 하자)

#### static

--- 
- 출처
  - [https://www.baeldung.com/java-least-common-multiple](https://www.baeldung.com/java-least-common-multiple)