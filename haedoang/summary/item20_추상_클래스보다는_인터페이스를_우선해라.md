### item 20 추상 클래스보다는 인터페이스를 우선해라
- 추상 클래스와 인터페이스 차이점
  - 추상 클래스는 정의한 타입을 구현하는 클래스는 반드시 추상 클래스의 하위 클래스이어야 한다
  - 인터페이스는 선언한 메서드를 모두 정의하고 규약을 잘 지킨 클래스라면 다 같은 타입으로 취급할 수 있다
  - 인터페이스는 기존 클래스에도 손쉽게 구현할 수 있다. 반면 추상 클래스는 그렇지 못하다
- 인터페이스는 믹스인(mixin) 정의에 안성맞춤이다
  - 믹스인은 특정 선택적 행위를 제공하는 것으로 `Comparable` 인터페이스를 구현한 객체가 <br/>
    오브젝트 순서를 정할 수 있는 `선택적 행위`가 추가 된 것을 예로 들 수 있다.
- 인터페이스는 계층구조가 없는 타입 프레임워크를 만들 수 있다.
  - 추상 클래스는 상속 관계이기 때문에 계층적인 반면, 인터페이스는 타입 간 유연한 관계를 유지한다
  - 인터페이스 확장을 통해 제 3의 인터페이스를 정의할 수도 있다
    - `조합 폭발(combinational explosion)`에 주의할 것
- 래퍼 클래스 관용구와 함께 사용하면 `인터페이스는 기능을 향상시키는 안전하고 강력한 수단`이 된다.(item 18)

#### 인터페이스 & 추상 골격 구현(skeletal implementation) 클래스
- 인터페이스의 `default method` 를 사용하면 개발자가 구현할 메서드를 줄여줄 수 있다. 
  하지만 `default method` 한계는 Object 메서드를 제공해서는 안된다(인터페이스의 다중 타입을 지원하기 때문에) <br/>
  이러한 경우에는 추상 골격 구현을 활용하는 방법이 있다
  ```java
  public abstract class AbstractQueue<E>
         extends AbstractCollection<E>
         implements Queue<E> {
  }
  ```

#### 컬렉션 프레임워크의 추상 골격 구현 클래스
 - AbstractCollection
 - AbstractSet
 - AbstractList
 - AbstractMap 

#### 골격 구현을 사용한 구체 클래스
  ```java
  public static List<Integer> intArrayAsList(int[] a) {
          Objects.requireNonNull(a);
          return new AbstractList<>() {
              @Override
              public Integer get(int index) {
                  return a[index];
              }
  
              @Override
              public Integer set(int index, Integer element) {
                  int oldVal = a[index];
                  a[index] = element;
                  return oldVal;
              }
  
              @Override
              public int size() {
                  return a.length;
              }
          };
      }
  ```

#### 어떻게 구현할 것인가
- 먼저 인터페이스로 작성을 하는 것을 고려한다
- 인터페이스의 메서드 중 구현 방법이 명백한 것이 있는 경우 디폴드 메서드로 제공한다
  - 디폴트 메서드를 제공할 때는 상속하려는 사람을 위해 `@implSpec` 태그를 붙여 문서화한다
- Object 메서드를 제공해야할 경우 골격 구현 클래스를 활용한다
- 골격 구현 클래스를 통해 다른 메서드들의 구현의 기반이 되는 메서드를 선정하여 작성한다
  - 재정의가 필요한 경우 `추상 메소드`로 작성한다
- `단순 구현(Simple Implementation)` 방식으로 추상클래스가 아닌 구현체를 제공하는 방법도 있다. 

- 출처 
  - [https://www.baeldung.com/java-interface-default-method-vs-abstract-class](https://www.baeldung.com/java-interface-default-method-vs-abstract-class)
  - [https://www.baeldung.com/java-queue](https://www.baeldung.com/java-queue)