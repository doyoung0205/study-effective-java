### 프로그램의 동작을 스레드 스케줄러에 기대하지 말라

정확성이나 성능이 스레드 스케줄러에 따라 달라지는 프로그램이라면 다른 플랫폼에 이식하기 어렵다.

스레드의 평균적인 수를 프로세서 수보다 지나치게 많아지지 않도록 하는 것이다.
그래야 스레드 스케줄러가 고민할 거리가 줄어든다.

실행 준비가 된 스레드들은 맡은 작업을 완료할 때 까지 계속 실행되도록 만들자.

이런 프로그램이라면 스레드 스케줄링 정책이 아주 상이한 시스템에서도 동작이 크게 달리지지 않는다.

실행 가능한 스레드의 수와 전체 스레드 수는 구분해야 한다.

전체 스레드 수는 훨씬 많을 수 있고, 대기 중인 스레드는 실행 가능하지 않다.


스레드는 절대 바쁜 대기 상태가 되면 안된다.
