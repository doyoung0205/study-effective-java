# 클래스와 인터페이스

[아이템 20. 추상 클래스보다는 인터페이스를 우선하라](#추상-클래스보다는-인터페이스를-우선하라)   
[- 인터페이스의 사용](#인터페이스의-사용)   
[- 핵심 정리](#핵심-정리)   

<br>

## 추상 클래스보다는 인터페이스를 우선하라
- 자바가 제공하는 다중 구현 메커니즘은 **인터페이스**와 **추상 클래스** 두 가지이다.
  - 자바 8부터 인터페이스도 디폴트 메서드를 제공할 수 있게 되어, 인스턴스 메서드를 구현 형태로 제공할 수 있다.
  - **추상 클래스를 구현하는 클래스는 반드시 추상 클래스의 하위 클래스가 되어야 한다.**
  - 자바는 단일 상속만 지원하니, 추상 클래스 방식은 새로운 타입을 정의하는데 커다란 제약이 생긴다.
  - **인터페이스는 기존 클래스에도 손쉽게 새로운 인터페이스를 구현해 넣을 수 있다.**
  - 반면, 기존 클래스 위에 새로운 추상 클래스를 끼워넣기는 어려운게 일반적이다.


### 인터페이스의 사용

#### ✓ 인터페이스는 믹스인 정의에 안성맞춤이다.
> 믹스인은 객체를 생성할 때 코드 일부를 클래스 안에 섞어 넣어 재사용하는 기법을 가리키는 용어다.  
> 합성이 실행 시점에 객체를 조합하는 재사용 방법이라면 믹스인은 컴파일 시점에 필요한 코드 조각을 조합하는 재사용 방법이다.
> 
> 믹스인은 상속 계층 안에서 확장한 클래스보다 더 하위에 위치하게 된다.  
> 다시 말해서 믹스인은 대상 클래스의 자식 클래스처럼 사용될 용도로 만들어지는 것이다.따라서 믹스인을 추상 서브클래스라고 부르기도 한다. 
> 
> − 오브젝트, 조영호 (p.379, p.387)

- [믹스인 예제](../../src/main/java/study/heejin/chapter4/item20/mixin) 
- [믹스인 예제 실행 테스트](../../src/test/java/study/heejin/chapter4/Item20Test.java)


#### ✓ 인터페이스로는 계층 구조가 없는 타입 프레임워크를 만들 수 있다.
- 타입을 계층적으로 정의하면 개념을 구조적으로 표현할 수 있지만, 현실에는 계층을 엄격히 구분하기 어려운 개념도 있다.
- 상속과 같은 계층적 구조에서는 공통 기능을 정의해놓은 타입이 없으니, 자칫 매개변수 타입만 다른 메서드들이 수없이 많아 질 수 있다.
  - 속성이 n개라면 지원해야 할 조합의 수는 2^n개나 된다. 이를 조합 폭발이라 부른다.
    ![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FG7qXc%2FbtqCZze3A8N%2Fi2ShiNk2au2PHhruIvjJ01%2Fimg.png)
- 반면 인터페이스는 기존 인터페이스를 확장하고, 새로운 메서드를 추가하는데 유연성을 제공한다.
  ![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fb1dTAV%2FbtqC2tLIm20%2FkhPfkkilbv7ho6v4iYUAdK%2Fimg.png)


#### ✓ 래퍼 클래스와 함께 사용하면 인터페이스는 기능을 향상시키는 안전하고 강력한 수단이 된다.
- 타입을 추상 클래스로 정의해두면 그 타입에 기능을 추가하는 방법은 상속뿐이다. 
- 상속해서 만든 클래스는 래퍼 클래스보다 활용도가 떨어지고 깨지기는 더 쉽다.


#### ✓ 인터페이스의 메서드 중 구현 방법이 명확한 것이 있다면 디폴트 메서드로 제공할 수 있다.
- 디폴트 메서드를 제공할 때는 상속하려는 사람을 위한 설명을 @implSpec 자바독 태그를 붙여 문서화해야 한다.
- 하지만 `equals`와 `hashCode` 같은 Object의 메서드들은 디폴트 메서드로 제공해서는 안된다.
- 또한, 인터페이스는 인스턴스 필드를 가질 수 없고, `public`이 아닌 정적 멤버도 가질 수 없다. `private` 정적 메서드는 가질 수 있다.
- [인터페이스 예제](../../src/main/java/study/heejin/chapter4/item20/CalculateInterface.java)
  ```java
  public interface CalculateInterface {
      int ERROR = -99999999;
  
      int add(int num1, int num2);
      int substract(int num1, int num2);
      int times(int num1, int num2);
      int divide(int num1, int num2);
  
      default void description() {
          System.out.println("정수 계산기를 구현합니다.");
      }
  
      static int total(int[] arr) {
          int total = 0;
  
          for (int i : arr) {
              total += 1;
  
          }
  
          return total;
      }
  
      private void getState() {
          System.out.println("계산을 진행중입니다.");
      }
  
      private static void printError() {
          System.out.println("ERROR : " + ERROR);
      }
  }
  ```


#### ✓ 인터페이스와 추상 골격 구현 클래스를 함께 제공하는 식으로 인터페이스와 추상 클래스의 장점을 모두 취하는 방법도 있다.
- 인터페이스로는 타입을 정의하고, 골격 구현 클래스로 메서드를 구현한다. 
  - 이렇게 하면 단순히 골격 구현을 확장하는 것만으로 인터페이스를 구현하는데 필요한 일이 대부분 완료 된다. **→ 템플릿 메서드 패턴**
- 관례상 인터페이스 이름이 Interface라면 골격 구현 클래스의 이름은 AbstractInterface 로 짓는다.
- `AbstractCollection`, `AbstractSet`, `AbstractList`, `AbstractMap` 각각이 바로 핵심 컬렉션 인터페이스의 골격 구현이다.
- 만약 인터페이스의 메서드가 모두 기반 메서드와 디폴트 메서드라면 골격 구현 클래스를 별도로 만들 이유는 없다.
- 기반 메서드나 디폴트 메서드로 만들지 못한 메서드가 남아 있다면, 인터페이스를 구현하는 골격 구현 클래스를 하나 만들어 남은 메서드들을 작성해 넣는다.
  - [골격 구현 클래스](../../src/main/java/study/heejin/chapter4/item20/AbstractMapEntry.java)
  - [골격 구현을 사용해 완성한 구체 클래스](../../src/main/java/study/heejin/chapter4/item20/IntArrays.java)
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
    − 이 예는 int 배열을 받아 Integer 인스턴스의 리스트 형태로 반환하는 어댑터이기도 하다.  
    − int 값고 Integer 인스턴스 사이의 변환(박싱과 언박싱) 때문에 성능은 그리 좋지 않다.  
    − 또한 익명클래스를 사용한 점도 주목하자.


#### ✓ 인터페이스를 구현한 클래스에서 골격 구현을 확장한 시뮬레이트한 다중 상속을 사용할 수 있다.
- Arrays 의 asList 메서드가 시뮬레이트한 다중 상속이라고 할 수 있다.
  ```java
  public static <T> List<T> asList(T... a) {
      return new ArrayList<>(a);
  }
  
  private static class ArrayList<E> extends AbstractList<E> 
                                    implements RandomAccess, java.io.Serializable {
      private static final long serialVersionUID = -2764017481108945198L;
      private final E[] a;
  
      ArrayList(E[] array) {
          a = Objects.requireNonNull(array);
      }
  
      @Override public int size() { ... }
      @Override public Object[] toArray() { ... }
      @Override public <T> T[] toArray(T[] a) { ... }
      @Override public E get(int index) { ... }
      @Override public E set(int index, E element) { ... }
      @Override public int indexOf(Object o) { ... }
      @Override public boolean contains(Object o) { ... }
      @Override public Spliterator<E> spliterator() { ... }
      @Override public void forEach(Consumer<? super E> action) { ... }
      @Override public void replaceAll(UnaryOperator<E> operator) { ... }
      @Override public void sort(Comparator<? super E> c) { ... }
  }
  ```


#### ✓ 인터페이스를 단순히 구현해서 상속을 대체한다. (단순구현)
- 단순 구현도 골격 구현과 같이 상속을 위해 인터페이스를 구현한 것이다.
- 단순 구현이 골격 구현과 다른 점은 추상 클래스가 아니라는 점이다.
- 쉽게 말해 동작하는 가장 단순한 구현이다.


### 핵심 정리
- 일반적으로 다중 구현용 타입으로는 인터페이스가 가장 적합하다.
- 복잡한 인터페이스라면 구현하는 수고를 덜어주는 골격 구현을 함께 제공하는 방법을 고려해보자.
- 골격 구현은 가능한 한 인터페이스의 디폴트 메서드로 제공하여 그 인터페이스를 구현한 모든 곳에서 활용하도록 하는 것이 좋다.


<br>

---
#### Reference

- [합성과 유연한 설계](https://hwannny.tistory.com/59)

