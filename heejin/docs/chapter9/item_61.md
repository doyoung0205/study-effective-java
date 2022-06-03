# 일반적인 프로그래밍 원칙

[아이템 61. 박싱된 기본타입보다는 기본 타입을 사용하라](#박싱된-기본타입보다는-기본-타입을-사용하라)  
[- 박싱된 기본 타입 사용시 주의할 점](#박싱된-기본-타입-사용시-주의할-점)  
[- 박싱된 기본 타입을 사용하는 경우](#박싱된-기본-타입을-사용하는-경우)  
[- 정리](#정리)  

<br>

## 박싱된 기본타입보다는 기본 타입을 사용하라
- 자바의 데이터 타입은 크게 두 가지로 나눌 수 있다.
  - 기본 타입: `int`, `double`, `boolean` 등
  - 참조 타입: `Integer`, `Double`, `Boolean` 등
- 오토박싱과 오토언박싱 덕분에 두 타입을 크게 구분하지 않고 사용할 수는 있지만, 차이점은 분명히 있다. _(→ item 6)_

### 기본 타입과 박싱된 기본 타입의 차이
1. 기본 타입은 값만 가지고 있지만, 박싱된 기본 타입은 값과 식별성이라는 속성을 갖는다.
   - 달리 말하면 박싱된 기본 타입의 두 인스턴스는 값이 같아도 서로 다르다고 식별될 수 있다.
2. 기본 타입의 값은 언제나 유효하지만, 박싱된 기본 타입은 유효하지 않은 값(`null`)을 가질 수 있다.
3. 기본 타입이 박싱된 타입보다 시간과 메모리 사용면에서 더 효율적이다.


### 박싱된 기본 타입 사용시 주의할 점
- **(같은 객체를 비교하는게 아니라면) 박싱된 기본 타입에 `==` 연산자를 사용하면 오류가 일어난다.**
  - [잘 동작하지만 결함이 있는 코드](../../src/test/java/study/heejin/chapter9/item61/BrokenComparatorTest.java#LC15)
    ```java
    Comparator<Integer> naturalOrder = (i, j) -> (i < j) ? -1 : (i == j ? 0 : 1);
    ```
    - 첫번재 검사 (i < j) 에서는 잘 동작한다.
    - 두번째 검사 (i == j) 에서는 '객체 참조'의 식별성을 검사한다.
    
  - [결함을 드러낸 코드](../../src/test/java/study/heejin/chapter9/item61/BrokenComparatorTest.java#LC32)
    ```java
    naturalOrder.compare(new Integer(42), new Integer(42));
    ```
    - 위와 같이 박싱된 기본 타입에 == 연산자를 사용하면 오류가 일어난다.
    
  - 기본 타입을 다루는 비교자가 필요하다면 `Comparator.naturalOrder()`를 사용하면된다.
    - [기본 타입을 다루는 비교자 - Comparator.naturalOrder()](../../src/test/java/study/heejin/chapter9/item61/ComparatorTest.java#LC32)
  - 박싱된 값을 기본 타입으로 저장하면 식별성 검사가 이뤄지지 않는다. 
    - [언박싱해서 결함을 해결](../../src/test/java/study/heejin/chapter9/item61/ComparatorTest.java#LC15)
    

- **기본 타입과 박싱된 타입을 혼용한 연산에서는 박싱된 기본 타입이 자동으로 언박싱된다.**
  - **`null` 참조를 언박싱하면 NullPointerExceptiond이 발생한다.**
    ```java
    public class Unbelievable {
        static Integer i;
    
        public static void main(String[] args) {
            if (i == 42) {
                System.out.println("믿을 수 없군!");
            }
        }
    }
    ```

- **박싱과 언박싱이 반복해서 일어나면 체감될 정도로 성능이 느려진다.**
  ```java
  public static void main(String[] args) {
      Long sum = 0L;
      for (long i = 0; i <= Integer.MAX_VALUE;  i++) {
          sum += 1;
      }
  }
  ```
  - [성능 비교](../../src/test/java/study/heejin/chapter9/item61/AutoBoxingSpeedTest.java)


### 박싱된 기본 타입을 사용하는 경우
1. 컬렉션의 원소, 키, 값으로 사용한다.
   - 컬렉션은 기본타입을 담을 수 없으므로 박싱된 기본 타입을 써야만 한다. 자바 언어가 타입 매개변수로 기본 타입을 지원하지 않기 때문이다.

2. 리플렉션을 통해 메서드를 호출할 때 사용한다. _(→ item 65)_



### 정리
- 기본 타입과 박싱된 기본 타입 중에 가능하다면 기본 타입을 사용하라.
- 오토박싱이 박싱된 기본 타입을 사용할 대의 번거로움을 줄여주지만, 그 위험까지 없애주지는 않는다.
- 박싱된 기본 타입을 `==` 연산자로 비교하면 식별성 비교가 이뤄진다.
- 같은 연산에서 기본 타입과 박싱된 기본 타입을 혼용하면 언박싱이 이뤄진다.
- 언박싱 과정에서 NullPointerException을 던질 수 있다.



<br>

