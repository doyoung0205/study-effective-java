### 가능한 한 실패 원자적으로 만들라

호출된 메서드가 실패하더라도 해당 객체는 메서드 호출 전 상태를 유지해야 한다.
이러한 특성을 실패 원자적(failure-atomic) 이라고 한다.

실패 원자적으로 만드는 방법

1. 불변객체로 설계
2. 작업 수행하기전에 매개변수의 유효성 검사
3. 유효성 검사가 어려울 떄 객체의 임시 복사본을 만들어 작업을 수행한뒤 교체
4. 작업 도중 발생하는 실패를 가로채는 복구 코드를 작성하여 작업 전 상태로 되돌리기

실패 원자성이 꺠질 수 있는 상황

1. 동시성 오류
2. 복잡도가 아주 큰 연산



