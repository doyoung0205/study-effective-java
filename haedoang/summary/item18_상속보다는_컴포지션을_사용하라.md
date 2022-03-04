### item17. 상속보다는 컴포지션을 사용해라
- 상속은 코드를 재사용하는 강력한 수단이지만, `항상 최선은 아니다`
- 메서드 호출과 달리 상속은 캡슐화를 깨뜨린다 // 예시 찾아보기 


#### 컴포지션
- 기존 클래스가 새로운 클래스의 구성요소로 쓰임.
- 새 클래스의 메서드들은 기존 클래스의 대응하는 메서드를 호출해 결과를 반환한다
- 이 방식을 `전달(forwarding)`, 새 클래스의 메서드들을 `전달 메서드(forwarding method)` 라고 한다
- InstrumentHashSet을 `래퍼 클래스` 라고하며, `데코레이터 패턴`이라고 부른다
- 컴포지션과 전달의 조합은 넓은 의미로 `위임(delegation)` 이라고 부른다(자기 자신의 참조를 넘기는 경우)
    ```java
    // wrapper class
    public class InstrumentedHashSetNew<E> extends ForwardingSet<E> {
        private int addCount = 0;
    
        public InstrumentedHashSetNew(Set<E> s) {
            super(s);
        }
    
        @Override
        public boolean add(E e) {
            System.out.println("invoke add()");
            addCount++;
            return super.add(e);
        }
        ...
    }
    
    public class ForwardingSet<E> implements Set<E> {
        private final Set<E> s;
    
        public ForwardingSet(Set<E> s) {
            this.s = s;
        }
    
        public boolean add(E e) {
            return s.add(e);
        }
        ...
    }
    ```

#### 상속을 잘못 사용한 예시
- Stack, Properties
  ```java 
  public class Stack<E> extends Vector<E> { ... }
  public class Properties extends Hashtable<Object,Object> { ... }
  ```
- 내부 구현을 불필요하게 노출하게 된 경우 클라이언트에서 상위 클래스를 직접 수정하여 상위 클래스의 불변식을 해칠 수 있다.


#### 정리
- 상속보다는 `컴포지션`과 `전달`을 사용하라
- 확장하려는 클래스의 API에 결함이 없는지 확인해야 한다
- 상속은 하위 클래스와 `is - a` 관계일 경우 써야 한다

