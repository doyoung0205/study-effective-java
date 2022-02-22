## equals 는 일반 규약을 지켜 재정의하라

꼭 필요한 경우가 아니면 equals 를 재정의하지 말자.
<br>
많은 경우에 Object 의 equals 가 여러분이 원하는 비교를 정확히 수행해준다.

재정의해야 할 때는 그 클래스의 핵심 필드 모두를 빠짐없이, 다섯 가지 규약을 확실히 지켜가며 비교 !

- 각 인스턴스가 본질적으로 고유하다.
- 인스턴스의 '논리적 동치성(logical equality)'을 검사할 일이 없다.
- 상위 클래스에서 재정의한 equals가 하위클래스에도 딱 들어 맞는다.
- 클래스가 private이거나 package-private이고 equals 메서드를 호출할 일이 없다.

### equals 메서드는 동치관계(equivalence relation)를 구현하며, 다음을 만족한다.

- 반사성(reflexivity): null 이 아닌 모든 참조 값 x에 대해, x.equals(x)는 true이다.
    - ex) 컬렉션 의 contains 메서드
- 대칭성(symmetry): null 이 아닌 모든 참조 값 x,y에 대해, x.equals(y)가 true 면 y.equals(x)도 true 이다.
    - ex) `CaseInsensitiveString.java` 참고
- 추이성(transitivity): null 이 아닌 모든 참조 값 x,y,z에 대해, x.equals(y)가 true 이고, y.equals(z) 도 true 면 x.equals(z) 도 true 이다.
    - ex) `Point.java` 참고
- 일관성(consistency): null 이 아닌 모든 참조 값 x,y에 대해, x.equals(y)를 반복해서 호출하면 항상 true 를 반환하거나 항상 false 를 반환한다.
- null-아님: null이 아닌 모든 참조 값 x에 대해, x.equals(null)은 false 이다.
