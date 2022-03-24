### item10 equals는 일반 규약을 지켜 재정의하라 
 - 자바는 상속 관계에 대해서 동치성을 만족할 수 없다.
 - 대칭성을 고려하여 상위 타입만 비교할 경우 추이성이 위반되기 떄문이다
 - 상속 관계의 문제를 개선하기 위해 getClass 를 통해 비교를 할 경우 리스코프 치환 법칙을 위배한다
 - 상속 관계 대신 컴포지션으로 비교를 해아 한다
 - equals 를 재정의할 경우 hashcode도 재정의해야 한다 -> hashMap, hashTable 등 컬렉션 객체 사용 시 문제가 발생한다
 - equals 메서드 구현 방법을 따른다
 - 꼭 필요하지 않은 경우라면 재정의하지 않도록 하자 

#### equivalence relation
- 반사성(reflexivity) : null이 아닌 모든 참조값 x에 대해, x.equals(x) => true
- 대칭성(symmetry) : null이 아닌 모든 참조값 x,y에 대해, x.equals(y) => true 이면 y.equals(x) => true
- 추이성(trasitivity) : null이 아닌 모든 참조값 x,y,z에 대해, x.equals(y) && y.equals(z) => x.equals(z)
- 일관성(consistency) : null이 아닌 모든 참조값 x,y에 대해, x.equals(y) => always true/false
- null-아님 : null이아닌 모든 참조값 x에 대해, x.equals(null) => false

#### equals 메서드 구현 방법
- step1. `==` 연산자를 사용해 입력이 자기 자신의 참조인지 확인한다.
- step2. `instanceof` 연산자로 입력이 볼바른 타입인지 확인한다.
- step3. `입력을 올바른 타입으로 형변환한다.`
- step4. `입력 객체와 자기 자신의 대응되는 '핵심' 필드들이 모두 일칠하는지 하나씩 검사한다.`
    - 타입별 비교
        - 기본 타입 : `==`
        - float, double : `Float.compare(), Double.comare()`.
        - null 도 정상값으로 취급할 경우 : Objects.equals()
        

#### float & double 부동 소수점 비교 
 - float 과 double 은 숫자가 아님을 나타내는 `POSITIVE_INFINITY`, `NEGATIVE_INFINITY`, `NaN` 특별한 비트 시퀀스가 존재한다
 - `==` 비교로는 해당 비트 시퀀스가 동일함을 나타내지 못한다
 - `compareTo`를 사용하자


