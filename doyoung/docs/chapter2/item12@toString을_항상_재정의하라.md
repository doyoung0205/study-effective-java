## toString 을 항상 재정의하라

- 기본적인 Object toString 메서드 : 클래스_이름@16진수로_표시한_해시코드
- 실전에서 toString 은 그 객체가 가진 주요 정보 모두를 반환하는 게 좋다.
- `AbstractCollection` 같이 하위 클래스들이 공유해야 할 문자열 표현이 있는 추상 클래스라면 toString 을 재정의해줘야한다.
