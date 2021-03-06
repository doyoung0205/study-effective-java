## item82 스레드 안전성 수준을 문서화하라
- 한 메서드를 여러 스레드가 동시에 호출할 때 그 메서드가 어떻게 동작하느냐는 해당 클래스와 이를 사용하는 클라이언트 사이의 중요한 계약과 같다
- synchronized 한정자는 API에 속하지 않음에 유의하자
- 클래스가 지원하는 스레드 안정성 수준을 정확히 명시해야 한다 

### 스레드 안정성이 높은 순으로 나열
- 불변
- 무조건적 스레드 안전: 인스턴스는 수정될 수 있으나 내부에서 충분히 동기화되어 외부의 동기화없이 사용 가능하다
- 조건부 스레드 안전: 일부 메서드는 외부 동기화가 필요하다
- 스레드 안전하지 않음: 인스턴스가 수정될 수 있다. 동시에 사용하려면 메서드 호출을 클라이언트가 선택한 외부 동기화 메커니즘으로 감싸야 한다
  - `ArrayList`, `HashMap`...
- 스레드 적대적: 모든 메서드 호출을 외부 동기화로 감싸더라도 멀티스레드 환경에서 안전하지 않다, 정적 데이터를 아무 동기화없이 수정한다

### 클래스가 외부에서 사용할 수 있는 락을 제공하는 경우 서비스 거부 공격을 수행할 수 있음에 주의하라
- 락을 클라이언트에게 제공할 경우 서비스 거부 공격와 같은 응답 불가의 이슈가 발생할 우려가 있다

### 비공개 락 객체로 대처하라
- 클라이언트가 객체 동기화에 관여할 수 없도록 한다
```java
private final Object lock = new Object(); // 락 객체가 교체되지 않도록 final 로 정의할 것

public void foo() {
    synchronized(lock) {
        //do something        
    }
}
```


### 정리
- 모든 클래스가 자신의 스레드 안전성 정보를 명확히 문서화해야 한다
- synchronized 한정자는 문서화와 관련이 없다
- 무조건적 스레드 안전 클래스를 작성할 때는 synchronized 메서드가 아닌 비공개 락 객체를 사용하자