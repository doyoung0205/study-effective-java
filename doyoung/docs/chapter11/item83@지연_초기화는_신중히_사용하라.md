### 지연 초기화는 신중히 사용하라

지연 초기화는 주로 최적화 용도로 쓰이지만, 클래스와 인스턴스 초기화 때 발생하는 위험한 순환 문제를 해결하는 효과도 있다.


양날의 검

인스턴스 생성 시의 초기화 비용은 줄지만, 그 대신 지연 초기화하는 필드에 접근하는 비용은 커진다.


### 대부분의 상황에는 일반적인 초기화가 지연 초기화보다 낫다.

### 지연 초기화시 고려해야하는 동시성

1. synchronized 접근
2. 성능 때문에 정적 필드를 지연초기화 해야 한다면 홀더 클래스 사용
3. 성능 때문에 인스턴스 필드를 지연 초기화 해야 한다면 이중검사 관용구 또는 vloatile

여러번 수행해도 괜찮다면 따릿한 단일검사 관용구이다.
이는 초기화가 스레드당 최대 한번 더 이뤄질 수 있다.


