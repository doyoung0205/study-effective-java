## item56 공개된 api 요소에는 항상 문서화 주석을 작성하라

### API문서 작성을 도와주는 자바독(javadoc) 유틸리티
- 소스코드 파일에서 `문서화 주석` 형태로 기술된 설명을 추려 API 문서로 변환해준다
- 업계 표준 API이다
- ex) `@literal`, `@code`, `@implSpec`, `@index` ...

### 공개된 클래스,인터페이스, 메서드, 필드 선언에 문서화 주석을 달아야한다
- 기본 생성자를 제외한 모든 클래스, 인터페이스, 메서드, 필드를 문서화하도록 하자

### 메서드용 문서화 주석에는 해당 메서드와 클라이언트 사이의 규약을 기술한다
- `How` 가 아닌 `What` 을 기술하도록 하자
    1. 메서드 호출하기 위한 전제조건
    2. 메서드 수행 후 만족해야 하는 사후 조건
- 전제조건은 `@throws` 태그로 비검사 예외를 선언 기술한다
- `@param` 태그를 사용하여 조건에 영향을 받는 매개변수에 대해 기술한다
- 메서드 부작용에 대해서도 문서화해야 한다
    - ex) 백그라운드 스레드 사용 메서드인 경우 메인스레드 사용 로직과 다르게 수행되기 때문에 문서화 작업이 반드시 필요하다
- void 반환 타입이 아닌경우 `@return` 태그를 사용한다
- `@param`, `@throws`, `@return` 태그에는 마침표를 붙이지 않는다

### APIs
`@code`:  태그로 감싼 내용을 코드용 폰트로 렌더링한다. 태그로 감싼 내용에 포함된 HTML 요소나 다른 자바독 태그를 무시한다
  ```text
  @throws NullPointerException if {@code name} is {@code null}
  ```
`@implSpec`: JDK8 이상. 자기사용 패턴 문서화. 해당 메서드와 하위 클래스 사이를 설명한다
  ```text
  @implSpec 정수 이외의 값은 예외를 발생시키기 때문에 재정의 주의할 것.
  ```
`@literal`: HTML 마크업이나 자바독 태그를 무시하게 해준다
  ```text
  @throws IllegalArgumentException {@literal age < 1 } 
  ```
`@index`: JDK9 이상. API 색인 기능 
  ```text
  이 메서드의 주석 내용은 {@index Effective Java Item56} 에 기술되어 있다.
  ```
`@inheritDoc`: 상위 타입의 주석을 상속할 수 있다
  ```text
  {@inheritDoc}
  ```
### 제네릭 타입이나 제네릭 메서드를 문서화 할 떄는 모든 타입 매개변수에 주석을 달아야 한다
- ex) HashMap.java
  ```java
  /**
   * 
   * @param <K> the type of keys maintained by this map
   * @param <V> the type of mapped values
   */
  public class HashMap<K,V> extends AbstractMap<K,V>
          implements Map<K,V>, Cloneable, Serializable {
  }
  ```

### 열거 타입 문서화 시 상수에도 주석을 달아야 한다
- `pulbic 메서드`도 포함한다

### 애너테이션 타입을 문서화할 때는 멤버들에도 모두 주석을 달아야 한다
- ex) @Repeatable
  ```java
  /**
   * The annotation type {@code java.lang.annotation.Repeatable} is
   * used to indicate that the annotation type whose declaration it
   * (meta-)annotates is <em>repeatable</em>. The value of
   * {@code @Repeatable} indicates the <em>containing annotation
   * type</em> for the repeatable annotation type.
   *
   * @since 1.8
   * @jls 9.6.3 Repeatable Annotation Types
   * @jls 9.7.5 Multiple Annotations of the Same Type
   */
  @Documented
  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.ANNOTATION_TYPE)
  public @interface Repeatable {
      /**
       * Indicates the <em>containing annotation type</em> for the
       * repeatable annotation type.
       * @return the containing annotation type
       */
      Class<? extends Annotation> value();
  }
  ```

### 패키지 설명하는 문서화 주석 package-info.java
- JDK9 이상부터 지원하는 모듈 시스템에서는 패키지의 공개 여부/의존성등을 `package-info.java` 에 기술한다

### 스레드 안정성/직렬화 가능성을 문서화하도록 하자
- 자주 누락되는 설명들이다
- 클래스 혹은 정적 메서드가 스레드 안전하든 그렇지 않든 스레드 안전 수준을 API 설명에 포함해야 한다

### 자바독 유틸리티가 생성한 웹 페이지를 읽어보고 잘 작성되어있는지 검토하자

### 문서 생성 방법
- tools > generate JavaDoc
- 한글 인코딩 처리 VM Options
  ```
  -locale ko_KR -encoding UTF-8 -charset UTF-8 -docencoding UTF-8
  ```
- ex) [샘플 파일](../../javadocs/index.html)

### 정리
- 문서화 주석은 API문서를 문서화하기 가장 훌륭하고 효과적인 방법이며, 표준 규약을 잘 지키도록 해야 한다
