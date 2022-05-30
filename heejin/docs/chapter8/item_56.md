# 메서드

[아이템 56. 공개된 API 요소에는 항상 문서화 주석을 작성하라](#공개된-api-요소에는-항상-문서화-주석을-작성하라)  
[- Javadoc 작성 방법](#javadoc-작성-방법)  
[- Javadoc 주석 태그](#javadoc-주석-태그)  
[- Javadoc 생성 방법](#javadoc-생성-방법)  
[- 정리](#정리)  

<br>

## 공개된 API 요소에는 항상 문서화 주석을 작성하라
- API에는 잘 작성된 문서가 있어야 한다.
- 자바독(Javadoc)은 소스 파일에서 문서화 주석을 API 문서로 변환해 준다.


### Javadoc 작성 방법
- API를 올바르게 문서화하려면 공개된 모든 클래스, 인터페이스, 메서드, 필드 선언에 문서화 주석을 달아야 한다.
  - 문서화 주석이 없다면 자바독도 그저 공개 API 요소들의 '선언'만을 나열해주는 것이 불과하다.
  - 공개 클래스는 기본 생성자에 주석을 달 수 있는 방법이 없으니 절대 **기본 생성자를 사용하면 안된다.**
- 메서드용 문서화 주석에는 해당 메서드와 클라이언트 사이의 규약을 명료하게 기술해야 한다.
  - how가 아닌 what을 기술해야 한다. 
  - 클라이언트가 메서드를 호출하기 위한 **전제조건**을 나열해야 한다.
  - 메서드가 성공적으로 수행된 후에 만족해야 하는 **사후조건**도 나열해야 한다.
  - 사후조건으로 명확하게 나타나지는 않지만 시스템의 상태에 어떠한 변화를 가져오는 **부작용**에 대해서도 문서화해야 한다. 
    - eg. 백그라운드 스레드를 시작하시키는 메서드


### Javadoc 주석 태그
- `@param`
  - 매개변수가 뜻하는 명사구
  - 모든 매개변수에 설명을 달아야 한다.
- `@return`
  - 반환 값을 설명하는 명사구
  - 반환 타입이 void가 아니라면 달아야 한다.
- `@throws`
  - if로 시작해 해당 예외를 던지는 조건을 설명하는 절
- `{@code}`
  - 주석 내에 HTML 요소나 다른 자바독 태그를 무시한다.
  - 주석에 여러 줄로 된 코드 예시를 넣으려면 `{@code}`를 `<pre>`태그로 감싸준다. `<pre>{@code ... }</pre>`
- `{@literal}`
  - 주석 내에 HTML 요소나 다른 자바독 태그를 무시한다.
  - `{@code}`와 비슷하지만 코드 폰트로 렌더링하지 않는다.
- `@implSpec`
  - 해당 메서드와 하위 클래스 사이의 계약을 설명한다.
  - 하위 클래스들이 그 메서드를 상속하거나 super 키워드를 이용해 호출할 때 그 메서드가 어떻게 동작하는지 명확히 인지할 수 있도록 도와준다.
- `{@inheritDoc}`
  - 상위 타입의 문서화 주석 일부를 상속할 수 있다.
  

### Javadoc 생성 방법
- Javadoc 생성 명령어
  ```
  $ javadoc -d docs {file_name}
  ```
- 한글 사용시 UTF-8 인코딩
  ```
  $ javadoc -d docs *.java -encoding UTF-8 -charset UTF-8 -docencoding UTF-8
  ```
- `@implSpec` 태그 사용
  ```
  javadoc -d docs -tag "implSpec:a:Implementation Requirements:" ./heejin/src/main/java/study/heejin/chapter8/item56/DocExamples.java 
  javadoc -d docs -tag "implSpec:a:Implementation Requirements:" ./heejin/src/main/java/study/heejin/chapter8/item56/DocKorExamples.java -encoding UTF-8 -charset UTF-8 -docencoding UTF-8
  ```
  

### 정리
- 문서화 주석은 API를 문서화하는 가장 훌륭하고 효과적인 방법이다.
- 문서화 주석의 표준 규약을 일관되게 지켜서 사용하자.
- 문서화 주석에 HTML 태그를 사용할 수 있지만, 메타 문자는 특별하게 취급해야 한다. 


<br>

#### Reference
- [[Effective Java] item 56. 공개된 API 요소에는 항상 문서화 주석을 사용하라](https://github.com/Meet-Coder-Study/book-effective-java/blob/main/8%EC%9E%A5/56_%EA%B3%B5%EA%B0%9C%20API%20%EC%9A%94%EC%86%8C%EC%97%90%EB%8A%94%20%ED%95%AD%EC%83%81%20%EB%AC%B8%EC%84%9C%ED%99%94%20%EC%A3%BC%EC%84%9D%EC%9D%84%20%EC%9E%91%EC%84%B1%ED%95%98%EB%9D%BC_%EA%B9%80%EC%A7%80%EC%95%A0.md)
