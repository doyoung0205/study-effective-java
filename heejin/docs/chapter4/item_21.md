# 클래스와 인터페이스

[아이템 21. 인터페이스는 구현하는 쪽을 생각해 설계하라](#인터페이스는-구현하는-쪽을-생각해-설계하라)   

<br>

## 인터페이스는 구현하는 쪽을 생각해 설계하라

#### 디폴트 메서드라는 도구가 생겼더라도 인터페이스를 설계할 때는 여전히 세심한 주의를 기울여야 한다.

- 자바 8에 와서 기존 인터페이스에 메서드를 추가할 수 있도록 디폴트 메서드를 소개했지만, 위험이 완전히 사라진 것은 아니다.
- 기존 인터페이스에 메서드를 추가하는 길이 열렸지만 모든 기존 구현체들과 매끄럽게 연동되리라는 보장은 없다. 생각할 수 있는 모든 상황에서 불변식을 해치지 않는 디폴트 메서드를 작성하기란 어려운 법이다.
- 디폴트 메서드는 (컴파일이 성공하더라도) 기존 구현체에 런타임 오류를 일으킬 수 있다.
- 디폴트 메서드는 인터페이스로부터 메서드를 제거하거나 기존 메서드의 시그니처를 수정하는 용도가 아님을 명심해야 한다.


#### 디폴트 메서드를 추가할 때, 해당 인터페이스를 구현하고 있는 다른 라이브러리에서 문제가 발생할 수 있다.

- 자바 8의 `Collection` 인터페이스에 추가된 removeIf 메서드는 아파치 커먼즈 라이브러리와 함께 사용할 때 동기화 문제가 발생한다.
  - 아파치 라이브러리에서는 `Collection` 대신 클라이언트가 제공한 객체로 락을 거는 기능을 추가로 제공한다.
  - 즉, 모든 메서드에서 주어진 락 객체로 동기화한 후 내부 컬렉션 객체에 기능을 위임하는 래퍼 클래스이다.
  - 하지만 `Collection`에 추가된 removeIf 메서드는 동기화에 관해 아무것도 모르므로 락 객체를 사용할 수 없다.
  - 따라서 `SynchronizedCollection` 인스턴스를 여러 스레드가 공유하는 환경에서 한 스레드가 `removeIf`를 호출하면 `ConcurrentModificationException`이 발생하거나 다른 예기치 못한 결과로 이어질 수 있다.
  

<br>

