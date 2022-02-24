# 모든 객체의 공통 메서드

[아이템 10. equals는 일발 규약을 지켜 재정의하라](#equals는-일발-규약을-지켜-재정의하라)   
[- equals 메서드를 재정의해야 하는 상황](#equals-메서드를-재정의해야-하는-상황)  
[- Object 명세에 적힌 규약](#Object-명세에-적힌-규약)  
[- Object 명세 위배](#Object-명세-위배)  
[- 양질의 equals 메서드 구현 방법](#양질의-equals-메서드-구현-방법)  
[- equals 메서드 구현시 주의사항](#equals-메서드-구현시-주의사항)  

<br>

## equals는 일발 규약을 지켜 재정의하라
- equals 메서드는 재정의하기 쉬워 보이지만 곳곳에 함정이 도사리고 있어서 자칫하면 끔찍한 결과를 초래한다.
- 따라서 eauqls 메서드를 꼭 사용해야 할 상황이 아니라면 재정의하지 않는 편이 좋다. 


### equals 메서드를 재정의해야 하는 상황
- 객체 식별성(두 객체가 물리적으로 같은가)이 아니라 논리적 동치성을 확인해야 하는데, 상위 클래스의 equals가 논리적 동치성을 비교하도록 재정의되지 않았을 때이다.
- 주로 값 클래스들이 여기 해당한다.
- 값 클래스라도 값이 같은 인스턴스가 둘 이상 만들어지지 않음을 보장하는 인스턴스 통제 클래스라면 equals를 재정의하지 않아도 된다. *(→ item 1)*
- Enum도 논리적으로 같은 인스턴스가 2개 이상 만들어지지 않기 때문에 equals를 재정의하지 않아도 된다. *(→ item 34)*


### Object 명세에 적힌 규약
- 반사성 : null이 아닌 모든 참조값 x에 대해, `x.equals(x)`는 true다.
- 대칭성 : null이 아닌 모든 참조값 x, y에 대해, `x.equals(y)`가 true면 `y.equals(x)`도 true다.
- 추이성 : null이 아닌 모든 참조값 x, y, z에 대해, `x.equals(y)`가 true이고, `y.equals(z)`도 true면 `x.equals(z)`도 true다.
- 일관성 : null이 아닌 모든 참조값 x, y에 대해, `x.equals(y)`를 반복해서 호출하면 항상 true를 반환하거나 항상 false를 반환한다.
- null 아님 : null이 아닌 모든 참조값 x에 대해, `x.equals(null)`은 false다.


### Object 명세 위배
- [대칭성 위배](../../src/main/java/study/heejin/chapter3/item10/CaseInsensitiveString.java)
  - `CaseInsensitiveString의` equals는 일반 String을 알고 있지만, `String`의 equals는 CaseInsensitiveString의 존재를 모르기 때문에 대칭성에 위배되는 결과가 나타난다.


- [추이성 위배](../../src/main/java/study/heejin/chapter3/item10/ColorPoint.java)
  - **구체 클래스를 확장해서 새로운 값을 추가하면서 equals 규약을 만족시킬 방법은 존재하지 않는다.**
  - 그렇다고 `equals` 메소드의 검사를 `instanceof` 대신 `getClass` 로 바꾸면, 리스코프 치환 원칙에 위배된다. (같은 구현 클래스의 객체와 비교할 때만 true 값을 반환)
    - `instanceof`는 동일한 인스턴스이거나 하위 타입 인스턴스인 경우 true를 반환하고, `getClass`는 동일한 인스턴스인 경우에만 ture를 반환한다.
  - 상속대신 컴포지션을 사용하는 우회 방식을 통해 확장해서 사용할 수 있다. *(→ item 18)*


- null 아님
  - 동치성을 검사하려면 equals는 객체를 적절히 형변환한 후 필수 필드의 값을 알아내야 한다. 
  - 그러려면 형변환에 앞서 `instanceof` 연산자로 입력 매개변수가 올바른 타입인지 검사해야 한다.
  - `instanceof`는 (두 번째 피연사자와 무관하게) 첫 번째 피연산자가 null이면 false를 반환한다.
  
    ```java
    assertFalse(null instanceof String);
    ```


### 양질의 equals 메서드 구현 방법

1. `==` 연산자를 사용해 입력이 자기 자신의 참조인지 확인한다.
2. instanceof 연산자로 입력이 올바른 타입인지 확인한다.
3. 입력을 올바른 타입으로 형변환한다.
4. 입력 객체와 자기 자신의 대응되는 핵심 필드들이 모두 일치하는지 하나씩 검사한다.


- 타입별 equals 비교
  - `==` : float, double을 제외한 기본 타입 필드
  - `Float.compare(float, float)`, `Double.compare(double, double)` : float, double    
    `Float.equals(float, float)`, `Double.equals(double, double)` 로 비교 할 수도 있지만, 이 메서드들은 오토박싱을 수반할 수 있어 성능상 좋지 않다.
  - `equals` : 참조 타입 필드
  - `Arrays.equals` : 배열의 모든 요소가 핵심 필드 

  
### equals 메서드 구현시 주의사항
- equals를 재정의할 땐 hashCode도 반드시 재정의하자 *(→ item 11)*
- 너무 복잡하게 해결하려 들지 말자. 필드들의 동치성만 검사해도 equals 규약을 어렵지 않게 지킬 수 있다.
- Object 외의 타입을 매개변수로 받는 equals 메서드는 선언하지 말자

  ```java
  public boolean equals(MyObject o) {
    ...
  }
  ``` 
  - 이 메서드는 Object.equals를 재정의한 게 아니라, 다중정의한 것이다. *(→ item 11)*
  - 타입을 구체적으로 명시한 equals는 오히려 해가 된다.
  - 이 메서드는 하위 타입에서 긍정 오류를 내게 하고 보안 측면에서도 잘못된 정보를 준다.


- equals (hashCode 도 마찬가지) 메서드를 작성하고 테스트하는 일은 지루하고, 테스트 코드도 비슷하기 때문에 AutoValue와 같은 프레임워크를 사용할 수 있다.


<br>

---
#### Reference

- [AutoValue](https://dahye-jeong.gitbook.io/java/java/advanced/2020-02-02-autovalue)
- [Introduction to AutoValue](https://www.baeldung.com/introduction-to-autovalue)



