# 예외

[아이템 74. 메서드가 던지는 모든 예외를 문서화하라](#메서드가-던지는-모든-예외를-문서화하라)
[- 정리](#정리)

<br>

## 메서드가 던지는 모든 예외를 문서화하라
- 메서드가 던지는 예외는 그 메서드를 올바로 사용하는 데 아주 중요한 정보다.
- 따라서 각 메서드가 던지는 예외를 하나하나 문서화하는 데 충분한 시간을 쏟아야 한다.


- **검사 예외는 항상 따로따로 선언하고, 각 예외가 발생하는 상황을 자바독의 `@throws` 태그를 사용하여 정확히 문서화하자.**
  - 공통 상위 클래스 하나로 뭉뚱그려 선언하는 일은 삼가자. 메서드가 `Exception`이나 `Thowable`을 던진다고 선언해서는 안된다.
  - main 메서드는 예외적으로, JVM만이 호출하므로 Exception을 던지도록 선언해도 괜찮다.


- 필수는 아니지만 비검사 예외도 검사 예외처럼 문서화해두면 프로그래밍 오류 상황이 일어나지 않도록 코딩하게 된다.
  - public 메서드라면 필요한 전제조건을 문서화해야 하며, 그 수단으로 가장 좋은 것이 바로 비검사 예외들을 문서화하는 것이다.
- **발생 가능한 비검사 예외를 문서로 남기는 일은 인터페이스 메서드에서 특히 중요하다.**
  - 이 조건이 인터페이스의 일반 규약에 속하게 되어 인터페이스를 구현한 모든 구현체가 일관되게 동작하도록 해주기 때문이다.

- **메서드가 던질 수 있는 예외를 각각 `@throws` 태그로 문서화하되, 비검사 예외는 메서드 선언의 throws 목록에 넣지 말자.**
- 검사냐 비검사냐에 따라 API 사용자가 해야 할 일이 달라지므로 이 둘을 확실히 구분해주는 게 좋다.
  - 자바독 유틸리티는 메서드 선언의 throws 절에 등장하고 메서드 주석의 @throws 태그에도 명시한 예외와 @throws 태그에만 명시한 예외를 시각적으로 구분해준다.
  - 그래서 프로그래머는 어느 것이 비검사 예외인지를 바로 알 수 있다.

- 한 클래스에 정의된 많은 메서드가 같은 이유로 같은 예외를 던진다면 그 예외를 (각각의 메서드가 아닌) 클래스 설명에 추가하는 방법도 있다. 
  - NullPointException이 가장 흔한 사례이다.


<br>

### 정리
- 메서드가 던질 가능성이 있는 모든 예외를 문서화하라.
- 검사 예외든 비검사 예외든, 추상메서드든 구체 메서드든 모두 마찬가지다.
- 문서화에는 자바독의 `@throws` 태그를 사용하면 된다.
- 검사 예외만 메서드 선언의 throws 문에 일일이 선언하고, 비검사 예외는 메서드 선언에는 기입하지 말자.


<br>
