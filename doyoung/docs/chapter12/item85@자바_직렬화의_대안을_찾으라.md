### 자바 직렬화의 대안을 찾으라

직렬화란 자바가 객체를 바이트 스트림으로 인코딩하고 (직렬화) 그 바이트 스트림으로부터 다시 객체를 재구성하는 메커니즘이다.

### 직렬화의 무서운 점

- 보이지 않는 생성자
- API 와 구현 사이의 모호해진 경계
- 잠재적인 정확한 문제
- 성능
- 보안
- 유지보수성

**readObject 메서드는 (serializable 인터페이스를 구현했다면) 클래스패스 안의 거의 모든 타입의 객체를 만들어 낼 수 있는, 사실상 마법 같은 생성자다.**
역직렬화 하는 과정에서 해당 타입들 안의 모든 코드를 수행할 수 있다.

```
지금도 자바 하부 시스템(RMI, JMX, JMS) 를 통해 간접적으로 쓰이고 있기 때문에
신뢰할 수 없는 스트림을 역직렬화하면 원격 코드 실행 (RCE), 서비스 거부(Dos) 등의 공격을 이어질 수 있다.
잘못한 게 아무것도 없는 애플리케이션이라도 이런 공격에 취약해질 수 있다.
```

### 신뢰할 수 있는 바이트 스트림

신뢰할 수 있는 바이트 스트림은 컴퓨터 네트워킹에서 공통적인 서비스 패러다임으로,
수신자의 통신 채널에서 나오는 바이트가 송신자가 채널에 삽입했을 때와 정확히 같은 순서로 정확히 동일한 바이트 스트림을 가리킨다.

### 차세대 데이터 표현의 선두주자

- JSON (javascript)
    - 텍스트 기반
- 프로토콜 버퍼 (c++)
    - 문서를 위한 스키마

### 직렬화 위험을 회피하는 가장 좋은 방법

- 아무것도 역직렬화하지 않는 것이다.
- 역직렬화 필터링
- 기본 수용 모드에서는 '블랙리스트 방식보다는 화이트리스트 방식을 추천한다.'
  (블랙리스트는 이미 알려진 위험만 받을 수 있기 떄문이다.)


### 용어 정리

가젯(gadget): 잠재적으로 위험한 동작을 수행하는 메서드
