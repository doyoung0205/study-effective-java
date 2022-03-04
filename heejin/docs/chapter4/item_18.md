# 클래스와 인터페이스

[아이템 18. 상속보다는 컴포지션을 사용하라](#상속보다는-컴포지션을-사용하라)   
[- 정리](#정리)   

<br>

## 상속보다는 컴포지션을 사용하라
- 상속은 코드를 재사용하는 강력한 수단이지만, 항상 최선은 아니다.
- 확장할 목적으로 설계되었고, 문서화도 잘 된 클래스는 상속을 사용해도 안전하다. *(→ item 19)*
- 하지만, 일반적인 구체 클래스를 다른 패키지의 구체 클래스가 상속하는 일은 위험하다.

> 이번 아이템에서 논하는 문제는 (클래스가 다른 클래스를 확장하는) 구현 상속을 말한다.  
> 이번 아이템에서 논하는 문제는 클래스가 인터페이스를 구현하거나, 인터페이스가 다른 인터페이스를 확장하는 인터페이스 상속과는 무관하다.


### 상속의 문제점 - 메서드 재정의
- 메서드 호출과 달리 상속은 캡슐화를 깨뜨린다.
  - 상위 클래스가 어떻게 구현되느냐에 따라 하위 클래스의 동작에 이상이 생길 수 있다. - [예제) 상속을 통한 재정의 메서드 오작동](../../src/main/java/study/heejin/chapter4/item18/InstrumentedHashSet.java)
  
  ```java
  public class InstrumentedHashSet<E> extends HashSet<E> {
    // (.. 생략 ..)
  
    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }
        
    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }
  }
  
  ```  

  ```java
  public boolean addAll(Collection<? extends E> c) {
      boolean modified = false;
      for (E e : c)
          if (add(e))
              modified = true;
      return modified;
  }
  ```
  - HashSet의 addAll 은 각 원소마다 add 를 호출하게 구현이 되어있기 때문에, `addAll()` 메서드를 호출하면, `addCount`가 중복 증가한다.

- 상위 클래스에서 메서드가 추가되면 하위 클래스가 깨지기 쉽다.
  - 실제로도 컬렉션 프레임워크 이전부터 존재하던 `HashTable`과 `Vector`를 컬렉션 프레임워크에 포함시키자 이와 관련한 보안 구멍들을 수정해야 하는 사태가 벌어졌다.


### 컴포지션
- 상속하는 대신, 새로운 클래스를 만들고 private 필드로 기존 클래스의 인스턴스를 참조하는 방식을 사용할 수 있다.
- 기존 클래스가 새로운 클래스의 구성요소로 쓰인다는 뜻에서 이러한 설계를 컴포지션(compositon)이라 한다.

#### 전달 메서드
- 새로운 클래스의 메서드들은 private 필드로 참조하는 기존 클래스의 메서드를 호출하여 그 결과를 반환할 수 있다.
- 이 방식을 전달이라 하며, 새로운 클래스에서 이렇게 사용되는 메서드를 전달 메서드라고 한다. 
- 그 결과, 새로운 클래스는 기존 클래스의 내부 구현 방식의 영향에서 벗어나며 기존 클래스에 새로운 메서드가 추가되더라도 전혀 영향을 받지 않는다.

  - [예제) 상속 대신 컴포지션을 사용](../../src/main/java/study/heejin/chapter4/item18/InstrumentedSet.java)
  - [예제) 재사용 할 수 있는 전달 클래스](../../src/main/java/study/heejin/chapter4/item18/ForwardingSet.java)

  ```java
  public class ForwardingSet<E> implements Set<E> { ... }
  ```

  ```java
  public class InstrumentedSet<E> extends ForwardingSet<E> { ... }
  ```
  
  - 기존의 `HashSet`을 상속 받는 대신 `HashSet`의 모든 기능을 정의한 `Set` 인터페이스를 구현함으로써 설계가 견고하고 유연하게 변경되었다. 


#### 래퍼 클래스
- 다른 인스턴스를 감싸고 있는 클래스를 래퍼 클래스라고 하며, 기능을 덧씌운다는 뜻에 데코레이터 패턴이라고 한다.
- 컴포지션과 전달의 조합은 넓은 의미로 위임이라고 부른다.
- 래퍼 클래스는 단점이 거의 없다. 
  - 한 가지, 래퍼 클래스가 콜백(callback) 프레임워크와는 어울리지 않는다는 점만 주의하면 된다.
  - 콜백 프레임워크에서는 내부 객체는 자신을 감싸고 있는 래퍼의 존재를 모르니 대신 자신(this)의 참조를 넘기고, 콜백 때는 래퍼가 아닌 내부 객체를 호출하게 된다.  
    → 이를 SELF 문제라고 한다.


### 정리
- 상속은 강력하지만 캡슐화를 해친다는 문제가 있다.
- 상속은 상위 클래스와 하위 클래스가 순수한 is-a 관계일 때만 써야 한다.
- 상속의 취약점을 피하려면 상속 대신 컴포지션과 전달을 사용하자. 
  - 특히 래퍼 클래스로 구현할 적당한 인터페이스가 있다면 더욱 그렇다.
  - 래퍼 클래스는 하위 클래스보다 견고하고 강력하다.


<br>

---
#### Reference

- []()
- []()
- []()

