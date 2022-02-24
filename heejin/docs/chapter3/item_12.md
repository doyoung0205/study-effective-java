# 모든 객체의 공통 메서드

[아이템 12. toString을 항상 재정의하라](#toString을-항상-재정의하라)   
[- toString 작성 방법](#tostring-작성-방법)  
[- toString 포맷 문서화](#tostring-포맷-문서화)  
[- toString 재정의](#tostring-재정의)  
[- toString 주의 사항](#tostring-주의-사항)  


<br>

## toString을 항상 재정의하라
- 이 메서드는 `클래스_이름@16진수로_표시한_해시코드`를 반환할 뿐이다.
- toString의 일반 규약에 따르면 '간결하면서 사람이 읽기 쉬운 형태의 유익한 정보'를 반환해야 한다.
- toString 메서드는 객체를 `println`, `printf`, 문자열 연결 연산자(`+`), `assert` 구문에 넘길 때, 혹은 `디버거가 객체를 출력`할 때 자동으로 쓰인다.
- **toString을 잘 구현한 클래스는 사용하기에 유용하고, 그 클래스를 사용한 시스템은 디버깅하기 쉽다.**


### toString 작성 방법
- 실전에서 toString은 그 객체가 가진 주요 정보 모두를 반환하는 게 좋다.
- 객체가 거대하거나 객체의 상태가 문자열로 표현하기 적합하지 않다면 요약 정보를 담아야한다.
- 하지만 객체의 요약 정보에서 주요 정보가 누락된 경우 혼동을 일으킬 수 있다.
  ```java
  // Assertions failure: excepted {abc, 123}, but {abc, 123}.
  ```
- 이상적으로는 toString을 재정의 할 때, 스스로를 완벽히 설명하는 문자열이어야 한다.


### toString 포맷 문서화
- toString을 구현할 때 반환값의 포맷을 문서화할지 정해야 한다.
  - 전화번호나 행렬 같은 값 클래스라면 문서화하기를 권한다.
- 포맷을 명시하면 그 객체는 표준적이고, 명확하고, 사람이 읽을 수 있게 된다. 
  - 값을 그대로 입출력에 사용하거나 CSV 파일처럼 사람이 읽을 수 있는 데이터 객체로 저장할 수 있다.
- 포맷을 명시하기로 했다면, 명시한 포맷에 맞는 문자열과 객체를 상호 전환할 수 있는 정적 팩터리나 생성자를 함께 제공해주면 좋다.
  - BigInteger, BigDecimal 과 대부분의 기본 타입 클래스가 여기 해당한다.
  
- 다만, 포맷을 한번 명시하면 평생 그 포맷에 얽매이게 된다. 프로그래머들이 이에 맞춰 코딩해야 한다.
- 반대로 포맷을 명시하지 않는 다면 향후 포맷을 개선할 수 있는 유연성을 얻게 될 것이다.
- 포맷을 명시하든 아니든 의도는 명확히 밝혀야 한다. 
- **포맷 명시 여부와 상관없이 toString이 반환한 값에 포함된 정보를 얻어올 수 있는 API를 제공하자.**


### toString 재정의
- 재정의 하는 경우
  - 객체의 값에 관해 알려주지 않는 Object의 toString보다는 자동 생성된 toString이 훨씬 유용하다.
  - 하위 클래스들이 공유해야 할 문자열 표현이 있는 추상 클래스라면 toString을 재정의해줘야 한다. - [예시) AbstractCollection의 toString](../../src/test/java/study/heejin/chapter3/Item12Test.java)
- 재정의 하지 않아도 되는 경우
  - 정적 유틸리티 클래스는 toString을 제공할 이유가 없다.
  - 대부분의 열거 타입도 자바가 이미 완벽한 toString을 제공하니 따로 재정의 할 필요가 없다.


### toString 주의 사항
- IDE에서 지원해주는 toString() 혹은 Lombok에서 지원하는 @ToString을 무분별하게 사용하면 StackOverflowError가 일어날 수 있다. - [예시) StackOverflowError](../../src/main/java/study/heejin/chapter3/item12/StackOverflowError.java)



<br>

---
#### Reference

- [아이템 12 - toString을 항상 재정의하라](https://github.com/Meet-Coder-Study/book-effective-java/blob/main/3%EC%9E%A5/12_toString%EC%9D%84_%ED%95%AD%EC%83%81_%EC%9E%AC%EC%A0%95%EC%9D%98%ED%95%98%EB%9D%BC_%EC%9D%B4%ED%98%B8%EB%B9%88.md)


