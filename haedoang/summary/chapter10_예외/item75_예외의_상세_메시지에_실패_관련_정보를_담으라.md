## item75 예외의 상세 메시지에 실패 관련 정보를 담으라
- 예외를 잡지 못해 프로그래밍 시리패하면 자바 시스템은 예외 스택 추적 정보를 자동으로 출력한다
- 실패를 재현하기 어려운 경우 예외에 대한 자세한 정보가 예외를 처리하는 데 많은 도움이 될 것이다


### 실패 순간을 포착하려면 발생한 예외에 관한 모든 매개변수와 필드 값을 실패 메시지에 담아라
```java
public class UserNotFoundException extends RuntimeException {
    public static final String MESSAGE = "회원을 찾을 수 없습니다.";

    public UserNotFoundException() {
    }

    public UserNotFoundException(Long userId) {
        super(String.format(MESSAGE + " %d", userId));
    }
```
- 단, 비밀번호나 암호 키 같은 정보는 담아서는 안 된다

### 예외 상세 메시지와 최종 사용자에게 보여줄 오류 메시지를 혼동하지 말자
- 예외 상세 메시지는 분석할 프로그래머를 대상으로 하고, 최종 메시지는 사용자를 대상으로 한다

### 예외는 실패 관련 정보를 얻을 수 있는 접근자 메서드를 적절히 제공하는 것이 좋다
- 포착한 실패 정보의 예외 상황을 복구하는 데 유용하기 때문에 `검사 예외` 에 사용하기 알맞다
- 비검사 예외라도 상세 정보를 알려주는 접근자 제공은 권장한다
