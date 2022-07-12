### 스레드 안정성 수준을 문서화하라

한 메서드를 여러 스레드가 동시에 호출할 때 그 메서드가 어떻게 동작하느냐는 해당 클래스와 이를 사용하는 클라이언트 사이의 중요한 계약과 같다.

메서드 선언에 synchronized 를 선한할지는 구현 이슈일 뿐 API 에 속하지 않는다.

멀티스레드 환경에서도 API를 안전하게 사용하게 하려면 클래스가 지원하는 스레드 안정성 수준을 정확히 명시해야 한다.

- 불변 (immutable)
- 무조건적 스레드 안전 (unconditionally thread-safe)
- 조건부 스레드 안전 (conditionally thread-safe)
- 스레드 안전하지 않음 (not thread-safe)
- 스레드 적대적 (thread-hostile)

스레드 안정성 관련 어노테이션

- @Immutable
- @ThreadSafe
- @NotThreadSafe


---

`/src/main/java/me/doyoung/studyeffectivejava/chapter11/item82/TestLock.java`

무조건적 스레드 안전클래스를 작성할 때는 synchronized 메서드가 아닌 비공개 락 객체를 사용하자.
이렇게 해야 클라이언트나 하위 클래스에서 동기화 매커니즘을 깨뜨리는 걸 예방할 수 있고, 
필요하다면 다음에 더 정교한 동시성을 제어 메커니즘으로 재구현할 여지가 생긴다.
