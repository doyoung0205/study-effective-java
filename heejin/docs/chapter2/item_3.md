# 객체 생성과 파괴

[아이템 3. private 생성자나 열거 타입으로 싱글턴임을 보증하라](#private-생성자나-열거-타입으로-싱글턴임을-보증하라)     
[- 싱글턴을 만드는 방식](#싱글턴을-만드는-방식)  
[- 싱글턴 사용시 주의사항](#싱글턴-사용시-주의사항)

<br>

## private 생성자나 열거 타입으로 싱글턴임을 보증하라

- 싱글턴(singleton)이란 인스턴스를 오직 하나만 생성할 수 있는 클래스를 말한다.
- 싱글턴의 전형적인 예로는 함수와 같은 무상태(stateless) 객체나 설계상 유일해야 하는 시스템 컴포넌트가 있다. *(→ item 24)*

### 싱글턴을 만드는 방식 
1) [public static final 필드 방식](../../src/main/java/study/heejin/chapter2/item3/SingletonField.java)
    - 장점  
     ① 해당 클래스가 싱글턴임이 API에 명백히 드러난다.    
     ② 간결하다.  
    - (예외)  
      권한이 있는 클라이언트는 리플렉션 API인 `AccessibleObject.setAccessible`을 사용해  private 생성자를 호출할 수 있다.  
      → 이러한 공격을 방어하려면 생성자를 수정하여 두 번째 객체가 생성되려 할 때 예외를 던지면 된다.
   
 
2) [정적 팩터리 방식의 싱글턴](../../src/main/java/study/heejin/chapter2/item3/SingletonStaticFactory.java)
   - 장점  
     ① (마음이 바뀌면) API를 바꾸지 않고도 싱글턴이 아니게 변경할 수 있다.    
     ② 정적 팩터리 메서드를 제네릭 싱글턴 팩터리로 만들 수 있다. *(→ item 30)*  
     ③ 정적 팩터리의 메서드 참조를 [공급자(supplier)](../../src/test/java/study/heejin/chapter2/Item3Test.java)로 사용할 수 있다. *(→ item 43, 44)*  
     &nbsp;&nbsp;&nbsp; `Singleton::getInstance` 대신 `Supplier<Singleton>` 으로 사용할 수 있다.  
     &nbsp;&nbsp;&nbsp; `Supplier<T>` : 함수형 인터페이스, 매개변수를 받지 않고 단순히 무엇인가를 반환하는 추상메서드
   - (예외)  
     권한이 있는 클라이언트는 리플렉션 API인 `AccessibleObject.setAccessible`을 사용해  private 생성자를 호출할 수 있다.  
     → 이러한 공격을 방어하려면 생성자를 수정하여 두 번째 객체가 생성되려 할 때 예외를 던지면 된다.
   

3) [열거 타입 방식의 싱글턴](../../src/main/java/study/heejin/chapter2/item3/SingletonEnum.java)
   - 장점  
     ① 필드 방식보다 더 간결하다.    
     ② 추가 노력 없이 직렬화 할 수 있다.  
     ③ 아주 복잡한 직렬화 상황이나 리플렉션 공격에서도 제2의 인스턴스가 생기는 일을 완벽히 막아준다.
   
- **대부분 상황에서는 원소가 하나뿐인 열거 타입이 싱글턴을 만드는 가장 좋은 방법이다.**

### 싱글턴 사용시 주의사항 
- 클래스를 싱글턴으로 만들면 이를 사용하는 클라이언트를 테스트하기가 어려워질 수 있다. 싱글턴 인스턴스를 가짜(mock) 구현으로 대체할 수 없기 때문이다.
- 필드 방식 또는 정적 팩터리 방식으로 만든 싱글턴 클래스를 직렬화화려면 단순히 `Serialisable`을 구현한다고 선언하는 것만으로는 부족하다.
  - 모든 인스턴스 필드를 일시적(transient)이라고 선언하고 readResolve 메서드를 제공해야한다. *(→ item 89)*
  - 이렇게 하지 않으면, 직렬화된 인스턴스를 역직렬화할 때마다 새로운 인스턴스가 만들어진다. (가짜 싱글턴 인스턴스가 탄생한다.)

<br>

---
#### Reference

- [[Java/자바] - Supplier<T> interface](https://m.blog.naver.com/zzang9ha/222087025042)



