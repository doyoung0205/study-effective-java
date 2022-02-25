## equals 를 재정의 하려거든 hashCode 도 재정의 하라

hashCode 를 같이 override 하지 않으면 equals 결과는 True 즉, 같다고 나오지만,

HashMap 에서 같은 Key 사용할 수 없고, HashSet 에서 다른 객체로 인식하게 된다.

- 참고: `me.doyoung.studyeffectivejava.chapter2.item11.HashCodeOverrideTest`

---

### HashCode 는 어떻게 정의되어야 하는가?

- **균일성**(uniformity), **효율성**(efficiency)

- 참고: `me.doyoung.studyeffectivejava.chapter2.item11.PhoneNumber`

### 균일성

- 해시 함수의 출력값이 고르게 분포될수록 균일성이 높음
    - 입력값으로 기대하는 값에 대해 (예: 모든 정수값, 사전에 있는 단어)
- 흔히 훌륭한 해시 함수는 균일성이 높아야 한다고 함
    - 즉, 출력 범위 안의 모든 값들이 동일한 확률로 나와야 함 (균등 분포)
    - 이러면 해시 충돌이 적어 O (1) 해시 테이블을 기대할 수 있음

### 효율성

- 보통 빠른 해시 함수를 선호함
- 공간을 더 낭비해도 빠른 접근 속도를 선호
    - 저장된 데이터를 빨리 찾는 용도로 사용하는 해시 함수
- 충돌이 좀 나더라도 더 빠른 함수를 선호
    - 어차피 해시 충돌은 드문 일
    - 몇 개 난다고 O(1)에서 크게 느려지지 않음
