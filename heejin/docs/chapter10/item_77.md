# 예외

[아이템 77. 예외를 무시하지 말라](#예외를-무시하지-말라)

<br>

## 예외를 무시하지 말라
- 예외를 무시하기란 아주 쉽다.
  - 해당 메서드 호출을 `try` 문으로 감싼 후 `catch` 블록에서 아무 일도 하지 않으면 끝이다.
  ```java
  try {
      ...
  } catch (SomeException e) {
  }
  ```

- 예외는 문제 상황에 잘 대처하기 위해 존재하는데 catch 블록을 비워두면 예외가 존재할 이유가 없어진다.

- 반면, 예외를 무시해야 할 때도 있다.
  - `FileInputStream`을 닫을 때, 필요한 정보는 이미 다 읽었다는 뜻이기 때문이다.
  
- 예외를 무시하기로 했다면 `catch` 블록 안에 그렇게 결정한 이유를 주석으로 남기고, 예외 변수의 이름도 `ignored`로 바꿔놓놓도록 하자.
  - 빈 catch 블록을 지나치면 그 프로그램은 오류를 내재한 채 동작하게 된다.

    ```java
    Future<Integer> f = exec.submit(planarMap::chromaticNumber);
    int numColors = 4;  // 기본값. 어떤 지도라도 이 값이면 충분하다.
    try {
        numColors = f.get(1L, TimeUnit.SECONDS);
    } catch (TimeoutException | ExecutionException ignored) {
        // 기본값을 사용한다(색상 수를 최소화하면 좋지만, 필수는 아니다).
    }
    ```

- 예외를 적절히 처리하면 오류를 완전히 피할 수 있고, 무시하지 않고 바깥으로 전파하기만 해도 최소한 디버깅 정보를 남긴 채 프로그램이 신속히 중단되게 할 수 있다.

<br>
