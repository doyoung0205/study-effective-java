### item11 equals를 재정의하려거든 hashCode도 재정의하라

--- 
 
- equals를 재정의한 클래스 모두에서 hashCode도 재정의해야 한다
  - Hash를 구현한 콜렉션 객체들에서는 hash값을 기준으로 객체 동치성 비교를 하기 때문에 hasCode를 재정의하지 않은 경우는 항상 false를 반환할 것이다
  - Object 명세 규약 참조
    - equals 비교에 사용되는 정보가 변경되지 않았다면, 어플리케이션이 실행되는 동안 그 객체의 hashCode 메서드는 일관성이 있어야 한다
    - equals(Object)가 같을 때 hashCode값도 같아야 한다
    - equals(Object)가 다르더라도 hashCode값은 같을 수 있다. 단, 다른 인스턴스의 경우 다른 해시코드를 반환하는 것이 성능에 좋다


- hash는 객체 동치성을 비교하는 값이기 떄문에 서로 다른 인스턴스는 다른 해시코드를 반환해야 한다
  - 같은 해시코드를 반환하는 경우 해시테이블의 성능이 느려지게 된다.(모든 엘리먼트가 동치성 true를 반환하고 같은 객체인지 equals를 통해 비교하기 때문)


- 좋은 해시코드 작성하는 방법
  1) int 변수 result를 선언한 후 값 `c(고유값 1번)` 으로 초기화한다
  2) 나머지 필드에 대해서 기본 타입인 경우 Type.hashcode(f) 를 수행한다. (아래 작업을 반복한다) 
     > result += 31 * result + Type.hashcode(f)
     - 곱셈을 하는 이유는 첫번째, 아나그램에 대해서 처리하기 위함이다.
     - `31`을 정한 이유는, 홀수이면서 소수이기 때문. 짝수이고 오버플로우가 발생한다면 정보를 잃게 된다.(2를 곱하는 것은 시프트 연산과 같은 결과)
       - 밑에 부연 설명있음!!


- 해시코드 성능을 개선하는 방법 중 지연 초기화 방식이 있다.
  ```text
    private int hashCode;
    @Override public int hashCode() {
        int result = hashCode;
        if (result != 0) {
            ... hashCode 구현
        }
    }
  ```
  - 멀티스레드 환경에서 문제가 될 수 있다. 
  

- haseCode를 반환하는 값의 생성규칙은 API에 공표하지 않는 것이 좋다. 클라이언트가 해당 값에 의지하는 것은 변화에 대응하기 어렵기 때문.

#### 해시코드에 왜 31을 곱하나요? 
 - [출처1](https://stackoverflow.com/questions/299304/why-does-javas-hashcode-in-string-use-31-as-a-multiplier) [출처2](https://computinglife.wordpress.com/2008/11/20/why-do-hash-functions-use-prime-numbers/)
 - 이펙티브 자바에서는 31이 홀수이면서 소수이기 때문에 사용했다고 한다. 2도 소수이고, 3도 소수인데 왜 31일까라는 의문이 남는다
 - 위의 내용을 요약하면 hash 알고리즘 hash key값을 가지고 탐색을 하는데, hash 키값이 중복될 경우 성능이 저하가 될 수 있다.
 - 따라서 같은 키 값을 가지지 않는 것이 성능 개선의 중요한 것이기 떄문에 소수만을 사용하는 것이 가장 좋지만, 쉽게 구현할 수 없기 떄문에 소수에 값을 곱한다
 - 소수를 곱하면 어느정도 UNIQUE한 key값을 가질 수 있다.
 - 31을 곱한 이유는 개발자가 가장 key 값 충돌이 적은 소수로 정하였기 때문이다
 