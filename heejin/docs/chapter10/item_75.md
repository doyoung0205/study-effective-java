# 예외

[아이템 75. 예외의 상세 메시지에 실패 관련 정보를 담으라](#예외의-상세-메시지에-실패-관련-정보를-담으라)

<br>

## 예외의 상세 메시지에 실패 관련 정보를 담으라

- 예외를 잡지 못해 프로그램이 실패하면 자바 시스템은 그 예외의 스택 추적(stack trace) 정보를 자동으로 출력한다.
  - 스택 추적은 예외 객체의 `toString` 메서드를 호출해 얻는 문자열로, 보통은 예외 클래스 이름 뒤에 상세 메시지가 붙는 형태이다.
- 예외의 `toString` 메서드에 실패 원인에 관한 정보를 담아 반환하는 일은 아주 중요하다.
  - 예컨데, `IndexOutOfBoundsException`의 상세 메시지는 범위의 최솟값과 최댓값, 그리고 그 범위를 벗어난 인덱스의 값을 담아 보여줄 수 있다.
- 단, 보안과 관련한 정보는 주의해서 다뤄야 한다.
  - 상세 메시지에 비밀번호나 암호 키 같은 정보까지 담아서는 안된다.
- 실패를 적절히 포착하기 위해 필요한 정보를 예외 생성자에서 모두 받아서 상세 메시지까지 미리 생성해 놓는 방법을 사용할 수 있다.
  ```java
  public class IndexOutOfBoundsException extends RuntimeException {
      private final int lowerBound;
      private final int upperBound;
      private final int index;
    
      public IndexOutOfBoundsException(int lowerBound, int upperBound, int index) {
          super(String.format("최솟값: %d, 최댓값: %d, 인덱스: %d", lowerBound, upperBound, index));
          this.lowerBound = lowerBound;
          this.upperBound = upperBound;
          this.index = index;
      }
  }
  ```

- 예외는 실패와 관련한 저보를 얻을 수 있는 접근자 메서드를 적절히 제공하는 것이 좋다.


<br>
