# 객체 생성과 파괴

[아이템 4. 인스턴스화를 막으려거든 private 생성자를 사용하라](#인스턴스화를-막으려거든-private-생성자를-사용하라)

<br>

## 인스턴스화를 막으려거든 private 생성자를 사용하라

- 이따금 단순히 정적 메서드와 정적 필드만을 담은 클래스를 만들고 싶을 때가 있을 것이다. → Util 클래스
  - `java.lang.Math`, `java.util.Arrays` 처럼 기본 타입 값이나 배열 관련 메서드들을 모아놓을 수 있다.
  - `java.util.Collections` 처럼 특정 인터페이스를 구현하는 개체를 생성해주는 정적 메서드(혹은 팩터리)를 모아놓을 수도 있다.
  - `final` 클래스와 관련한 메서드들을 모아놓을 때도 사용한다.


- 정적 멤버만 담은 유틸리티 클래스는 인스턴스로 만들어 쓰려고 설계한 것이 아니다. → 인스턴스를 생성할 수 없도록 해야 한다.
  - 추상 클래스로 만든다고 해서 인스턴스화를 막을 수는 없다. (하위 클래스를 만들어서 인스턴스화 할 수 있기 때문이다.)
  - private 생성자를 추가하여 클래스의 인스턴스화를 막을 수 있다. 이 방식은 상속을 불가능하게 하는 효과도 있다.

  ```java
  public class UtilityClass {
      private UtilityClass() {
          throw new AssertionError("유틸리티 클래스이므로 인스턴스화 할 수 없습니다.");
      }
  }
  ```
   
<br>

---
#### Reference

- [4. 인스턴스화를 막으려거든 private 생성자를 사용하라](https://eyabc.github.io/docs/java/effective-java/ch2/ITEM4)




