## item43 람다보다는 메서드 참조를 사용하라

### 메서드 참조 유형
- 정적
  - ContainingClass::staticMethodName
- 한정적(인스턴스)
  - containingObject::instanceMethodName
- 비한정적(인스턴스)
  - ContainingType::methodName
- 클래스 생성자
  - ClassName::new
- 배열 생성자
  - Type[]::new

### 메서드 참조와 람다 
  - 메서드 참조가 람다에 비해 코드가 더 간결하다
  - 단, 메서드와 람다가 같은 클래스인 경우 람다가 더 나을 수도 있다
    ```java
    service.execute(GoshThisClassNameIsHello::action); //bad
    service.execute(() -> action()); //better
    ```

### 함수 타입
- 함수형 인터페이스의 타입을 말한다
- 구성 요소
    - 타입 파라미터
    - 인자 타입
    - 반환 타입
    - 예외 타입
   ```java
   interface G1 {
     <E extends Exception> Object m() throws E;
   }
  
   interface G2 {
     <F extends Exception> String m() throws Exception;
   }
  
   interface G extends G1, G2 {}
  
   <F extends Exception> () -> String throws F
   ```
- 함수 타입 구하기 
  - [https://docs.oracle.com/javase/specs/jls/se8/html/jls-9.html#jls-9.9](https://docs.oracle.com/javase/specs/jls/se8/html/jls-9.html#jls-9.9)


### 정리 
- 메서드 참조쪽이 짧고 명확하다면 메서드 참조를 쓰고 그렇지 않을 때만 람다를 사용하라




- 메서드 참조 유형 설명 참고 링크
  [https://dev.to/tlylt/exploring-java-method-references-oop-java-13-4ke6](https://dev.to/tlylt/exploring-java-method-references-oop-java-13-4ke6)
- 함수 타입 참고 링크
  [https://docs.oracle.com/javase/specs/jls/se8/html/jls-9.html#jls-9.9](https://docs.oracle.com/javase/specs/jls/se8/html/jls-9.html#jls-9.9)
- 함수 타입 참고 자료 
  [https://homoefficio.github.io/2017/02/19/Java8-%EB%9E%8C%EB%8B%A4-%EA%B4%80%EB%A0%A8-%EC%8A%A4%ED%8E%99-%EC%A0%95%EB%A6%AC/](https://homoefficio.github.io/2017/02/19/Java8-%EB%9E%8C%EB%8B%A4-%EA%B4%80%EB%A0%A8-%EC%8A%A4%ED%8E%99-%EC%A0%95%EB%A6%AC/)