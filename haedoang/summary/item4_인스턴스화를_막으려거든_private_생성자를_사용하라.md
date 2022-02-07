### 아이템4 인스턴스화를 막으려거든 private 생성자를 사용하라
- 정적 멤버만을 가지는 유틸리티성 클래스는 인스턴스화를 하지 않아도 된다.
- 자바는 기본 생성자를 만들어주기 때문에 사용자로 하여금 혼란을 줄 수 있다.
- `private` 생성자를 통해 사용을 제한할 수 있다.

#### 용어 정리
- AssertionException: 개발자가 의도하지 않은 상황에서 발생한 예외. [관련 링크](https://stackoverflow.com/questions/24863185/what-is-an-assertionerror-in-which-case-should-i-throw-it-from-my-own-code)