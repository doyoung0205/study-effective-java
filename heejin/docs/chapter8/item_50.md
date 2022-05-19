# 메서드

[아이템 50. 적시에 방어적 복사본을 만들라](#적시에-방어적-복사본을-만들라)  
[- 방어적 복사](#방어적-복사)  
[- 방어적 복사를 사용하는 이유](#방어적-복사를-사용하는-이유)  
[- 방어적 복사에서 고려할 점](#방어적-복사에서-고려할-점)  

<br>

## 적시에 방어적 복사본을 만들라
- 자바는 안전한 언어이지만, 그럼에도 불변식이 깨지지 않도록 방어적으로 프로그래밍해야 한다.
- 주의를 기울이지 않으면 객체의 외부에서 내부를 수정하는 일이 발생하기도 한다.
  ```java
  public class MutablePeriod {
    private final Date start;
    private final Date end;

    public MutablePeriod(Date start, Date end) {
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException(start + "가 " + end + "보다 늦다.");
        }
        this.start = start;
        this.end = end;
    }
  }
  ``` 
  - 얼핏 이 클래스는 불변처럼 보이고, 시작 시각이 종료 시각보다 늦을 수 없다는 불변식이 지켜질 것 같이 보인다.
  - 하지만 Date가 가변이라는 사실을 이용하면 불변식을 깨뜨릴 수 있다. - [불변식을 지키지 못한 예제](../../src/test/java/study/heejin/chapter8/Item50Test.java)
  - `Date` 대신 불변인 `Instant`(혹은 `LocalDateTime` 이나 `ZonedDateTime`) 를 사용하면 된다.


### 방어적 복사
- **외부 공격으로부터 인스턴스의 내부를 보호하려면 생성자에서 받은 가변 매개변수 각각을 방어적으로 복사해야 한다.**
  ```java
  public ImmutablePeriod(Date start, Date end) {
      this.start = new Date(start.getTime());
      this.end = new Date(end.getTime());
      
      if (start.compareTo(end) > 0) {
          throw new IllegalArgumentException(start + "가 " + end + "보다 늦다.");
      }
  }
  ```
  - **매개변수의 유효성을 검사하기 전에 방어적 복사본을 만들고, 이 복사본으로 유효성을 검사한 점에 주목해야 한다.**
    - 멀티스레딩 환경이라면 원본 객체의 유효성을 검사한 후 복사본을 만드는 그 찰나의 취약한 순간에 다른 스레드가 원본 객체를 수정할 위험이 있다.
    - 이런 공격을 검사시점/사용시점 공격(time-of-check/time-of-use) 라고 한다.(줄여서 TOCTOU 라고도 한다.)
  - 방어적 복사에 Date의 clone메서드를 사용하지 않은 점에도 주목해야 한다.
    - Date는 final이 아니므로 clone이 Date가 정의한 게 아닐 수 있다.
    - 즉, **제 3자에 의해 확장될 수 있는 타입이라면 방어적 복사본을 만들 때 clone을 사용해서는 안된다.**
    
- 접근자 메서드가 내부의 가변 정보를 직접 드러내기 때문에 접근자 메서드도 방어적으로 복사해야 한다.
  ```java
   public Date start() {
       return new Date(start.getTime());
   }

   public Date end() {
       return new Date(end.getTime());
   }
  ```
  - 생성자와 달리 접근자 메서드에서는 방어적 복사에 clone을 사용해도 된다.  
    - Period가 가지고 있는 Date 객체는 신뢰할 수 없는 하위 클래스가 아닌 java.util.Date 임이 확실하기 때문이다.
    - 그렇더라도 인스턴스를 복사하는 데는 일반적으로 생성자나 정적 팩터리를 쓰는 편이 좋다. _(→ item13)_
    

### 방어적 복사를 사용하는 이유
- **불변 객체**를 만들기 위해서 매개변수를 방어적으로 복사한다.
- 클라이언트가 제공한 객체의 참조를 **내부의 자료구조에 보관**해야 할 때, 해당 객체가 잠재적으로 변경될 수 없도록 방어적으로 복사한다.
- **내부 객체를 클라이언트에게 반환**하기 전에 해당 객체가 잠재적으로 변경될 수 없도록  방어적으로 복사한다.

➡️ 되도록 불변 객체들을 조합해야 방어적 복사를 할 일이 줄어든다.


### 방어적 복사에서 고려할 점
- 방어적 복사에는 성능 저하가 따르고, 항상 쓸수 있는 것도 아니다.
- 해당 클래스와 클라이언트가 상호 신뢰할 수 있다면 방어적 복사를 생략할 수 있다.
- 불변식이 깨지더라도 그 영향이 오직 호출한 클라이언트로 국한될 때 방어적 복사를 생략할 수 있다.
  - 래퍼 클래스 패턴에서 클라이언트가 래퍼에 넘긴 객체에 직접 접근할 수 있어서 불변식을 파괴할 수 있지만, 그 영향을 오직 클라이언트 자신만 받게 된다.
  
<br>
