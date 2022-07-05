# 동시성

[아이템 79. 과도한 동기화는 피하라](#과도한 동기화는 피하라)
[- 동기화에서 발생할 수 있는 문제](#동기화에서-발생할-수-있는-문제)  
[- 동기화 문제점 해결](#동기화-문제점-해결)  
[- 동기화 성능](#동기화-성능)  
[- 정리](#정리)  

<br>

## 과도한 동기화는 피하라
- 과도한 동기화는 성능을 떨어뜨리고, 교착상태에 빠뜨리고, 심지어 예측할 수 없는 동작을 낳기도 한다.
- **응답 불가와 안전실패를 피하려면 동기화 메서드나 동기화 블록 안에서는 제어를 절대로 클라이언트에 양도하면 안된다.**
  - 예를 들어, 동기화된 영역 안에서는 재정의할 수 있는 메서드는 호출하면 안되며, 클라이언트가 넘겨준 함수 객체를 호출해서도 안된다.

### 동기화에서 발생할 수 있는 문제
- [집합에 원소가 추가되면 알림을 받는 관찰자 패턴 예시](../../src/main/java/study/heejin/chapter11/item79/ObservableSet.java)
  - [ConcurrentModificationException](../../src/test/java/study/heejin/chapter11/Item79Test.java#LC27)
    - 관찰자의 `added` 메서드 호출이 일어난 시점이 `notifyElementAdded`가 관찰자들의 리스트를 순회하는 도중이기 때문에 `ConcurrentModificationException`이 발생한다.
    - `notifyElementAdded` 메서드에서 수행하는 순회는 동기화 블록 안에 있으므로 동시 수정이 일어나지 않도록 보장하지만, 정작 자신이 콜백을 거쳐 되돌아와 수정하는 것까지 막지는 못한다.
  - [교착상태 (응답 불가)](../../src/test/java/study/heejin/chapter11/Item79Test.java#LC55)
    - 백그라운드 스레드가 removeObserver를 호출하면 관찰자를 잠그려 시도하지만, 메인 스레드가 이미 락을 가지고 있기 때문에 교착상태에 빠진다.
  - 재진입 락으로 불변식이 깨진 경우
    - 자바 언어의 락은 재진입(reentrant)을 허용하므로 교착상태에 빠지지 않는다.
    - **하지만 재진입 가능 락은 응답 불가(교착상태)가 될 상황을 안전 실패(데이터 훼손)로 변모시킬 수도 있다.**

### 동기화 문제점 해결
- 동기화의 문제점은 외계인 메서드 호출을 동기화 블록 바깥으로 옮기면 대부분 어렵지 않게 해결할 수 있다.

  ```java
  private void notifyElementAdded(E element) {
      List<SetObserver<E>> snapshot = null;
      synchronized (observers) {
          snapshot = new ArrayList<>(observers);
      }
      for (SetObserver<E> observer: snapshot) {
          observer.added(this, element);
      }
  }
  ```
  - 이처럼 동기화 영역 바깥에서 호출되는 외계인 메서드를 열린 호출이라 한다.
  - 외계인 메서드는 얼마나 오래 실행될지 알 수 없는데, 동기화 영역 안에서 호출된다면 그동안 다른 스레드는 보호된 자원을 사용하지 못하고 대기해야 한다.
  - 따라서 열린 호출은 실패 방지 효과 외에도 동시성 효율을 크게 개선해준다.


- 자바의 동시성 컬렉션 라이브러리의 `CopyOnWriteArrayList`가 정확히 이 목적으로 설계된 것이다.  
  (외계인 메서드를 동기화 블록 바깥으로 옮기는 것보다 더 나은 방법이다.)

  ```java
  private final List<SetObserver<E>> observers = new CopyOnWriteArrayList<>();
  
  public void addObserver(SetObserver<E> observer) {
      observers.add(observer);
  }
  
  public boolean removeObserver(SetObserver<E> observer) {
      return observers.remove(observer);
  }
  
  private void notifyElementAdded(E element) {
      for (SetObserver<E> observer : observers) {
          observer.added(this, element);
      }
  }
  ```
  - `CopyOnWriteArrayList` 클래스는 내부를 변경하는 작업을 항상 복사본을 만들어 수행하도록 구현되어 있다.


### 동기화 성능
- 과도한 동기화가 초래하는 비용은 락을 얻는 데 드는 CPU 시간이 아니다.
- 바로 경쟁하느라 낭비하는 시간, 즉 병렬로 실행할 기회를 잃고, 모든 코어가 메모리를 일관되게 보기 위한 지연시간이 진짜 비용이다.
- 가상머신의 코드 최적화를 제한한다는 점도 과도한 동기화의 또 다른 숨은 비용이다.

#### 가변 클래스의 동기화
1. 동기화를 전혀 하지 말고, 해당 클래스를 동시에 사용해야 하는 클래스의 외부에서 알아서 동기화하게 하자.
   - ex) `java.util`
2. 동기화를 내부에서 수행해 스레드 안전한 클래스로 만들자. _(→ item82)_
  - ex) `java.util.concurrent`
  - 단, 클라이언트가 외부에서 객체 전체에 락을 거는 것보다 동시성을 월등히 개선할 수 있을 때만 이 방법을 선택해야 한다.

#### 자바 동기화 클래스 비교
- `StringBuffer`: 거의 단일 스레드에서 쓰임에도 내부적으로 동기화를 수행한다.
- `StringBuilder`: 동기화 하지 않은 StringBuffer이다.
- `java.util.Random`: 스레드 안전한 난수 발생기이다.
- `java.util.concurrent.ThreadLocalRandom`: Random을 동기화 하지 않은 버전이다.
- 선택하기 어렵다면, 동기화하지 말고, 대신 문서에 "스레드 안전하지 않다"고 명기하자.


### 정리
- **여러 스레드가 호출할 가능성이 있는 메서드가 정적 필드를 수정한다면 그 필드를 사용하기 전에 반드시 동기화해야 한다.**
  - 그런데 클라이언트가 여러 스레드로 복제돼 구동되는 상황이라면 다른 클라이언트에서 이 메서드를 호출하는 것을 막을 수 없기 때문에 외부에서 동기화할 방법이 없다.
  - 이 정적 필드가 `private`이라도 사실상 전역 변수와 같아진다는 뜻이다.
- 교착상태와 데이터 훼손을 피하려면 동기화 영역 안에서 외계인 메서드를 절대 호출하지 말자.
- 일반화하면, 동기화 영역 안에서의 작업은 최소한으로 줄이자.


<br>
