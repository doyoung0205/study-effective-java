### item 21 인터페이스는 구현하는 쪽을 생각해 설계하라
- 인터페이스의 default 메서드의 사용 시 주의해야 한다. 
- 생각할 수 있는 모든 상황에서 불변식을 해치지 않는 디폴트 메서드를 작성하기란 어렵다.
  - 다중 구현을 통한 메서드 충돌을 예로 들 수 있을 것 같다
- 디폴트 메서드에 의존하는 서드 파티 라이브러리 사용에 주의해야 한다
  - apache-commons 의 `SynchronizedCollection` 은 Collection 의 구현체로 동기화 기능을 제공한다
  - Collection의 `removeIf` 라는 디폴트 메서드는 동기화 처리가 되어있지 않기 때문에 사용 시 동기화 효과를 낼 수 없다
  - `4.4` 버전을  사용하는 현재 시점에서는 동기화 메서드가 재정의되었다
    ```java
    public class SynchronizedCollection<E> implements Collection<E>, Serializable {
        @Override
        public boolean removeIf(final Predicate<? super E> filter) {
            synchronized (lock) {
                return decorated().removeIf(filter);
            }
        }
    }
    ```
- 디폴트 메서드는 (컴파일에 성공하더라도) 기존 구현체에 런타임 오류를 일으킬 수 있다(인터페이스 설계 시 주의하자)
