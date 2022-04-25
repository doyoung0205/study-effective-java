# 열거 타입과 애너테이션

[아이템 39. 명명 패턴보다 애너테이션을 사용하라](#명명-패턴보다-애너테이션을-사용하라)  
[- 애너테이션 방식의 테스트 프레임워크](#애너테이션-방식의-테스트-프레임워크)
[- 정리](#정리)

<br>

## 명명 패턴보다 애너테이션을 사용하라
- 전통적으로 특별히 다뤄야 할 프로그램 요소에는 명명 패턴을 적용했다.
  - 예컨데 테스트 프레임워크인 JUnit 버전 3까지 테스트 메서드 이름을 `test`로 시작하게 했다.
  - 이렇게 명명 패턴을 사용하면 오타, 예외처리, 매개변수 전달 등에서 한계를 가지게 된다.
- Junit 버전 4부터 애너테이션이 등장하여 명명 패턴의 문제점을 해결해 주었다.

### 애너테이션 방식의 테스트 프레임워크
- 마커 애너테이션
  - 아래의 `@Test` 예제와 같이 아무 매개변수 없이 단순히 대상에 마킹하는 애너이션을 **마커 애너테이션**이라고 한다.
  ```java
  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.METHOD)
  public @interface Test {
  }
  ```
  - `@Retention`과 `@Target` 은 애너테이션 선언에 다는 메타애너이션이다.
  - 이 애너테이션을 사용하면 프로그래머가 Test 이름에 오타를 내거나 메서드 선언 외의 프로그램 요소에 달면 컴파일 오류를 내준다.


1. [마커 애너테이션을 사용한 프로그램](../../src/main/java/study/heejin/chapter6/item39/Sample.java)  
   [마커 애너테이션을 처리하는 프로그램](../../src/main/java/study/heejin/chapter6/item39/RunTests.java)
  - 위 예제에서 `isAnnotationPresent` 메서드에서 예외가 발생하면 리플렉션 메커니즘이 `InvocationTargetException`으로 감싸서 다시 던진다.
  - InvocationTargetException 외의 예외가 발생한다면 @Test 애너테이션을 잘못 사용했다는 뜻이다. 


2. 특정 예외를 던져야만 성공하는 테스트
    ```java
    @Retention(RetentionPolicy.RUNTIME)
    @Target(value = ElementType.METHOD)
    public @interface ExceptionTest {
        Class<? extends Throwable> value();
    }
    ```
    [1) 매개변수 하나짜리 애너테이션을 사용한 프로그램- 예외 케이스](../../src/main/java/study/heejin/chapter6/item39/SampleEx.java)  
    [2) 배열 매개변수를 받는 애너테이션을 사용한 프로그램- 예외 케이스](../../src/main/java/study/heejin/chapter6/item39/SampleExArray.java)


3. 반복가능한 애너테이션
- 자바 8에서는 여러 개의 값을 받는 애너테이션을 배열 매개변수를 사용하는 대신 `@Repeatable` 메타애너테이션을 다는 방식으로 사용할 수 있다.
  ```java
  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.METHOD)
  @Repeatable(ExceptionTestContainer.class)
  public @interface RepeatableExcptionTest {
    Class<? extends Throwable> value();
  }
  ```
  - [반복가능한 애너테이션을 사용한 프로그램](../../src/main/java/study/heejin/chapter6/item39/SampleRepeatable.java)


### 정리
- 테스트 프레임워크 예제는 아주 간단하지만 애너테이션이 명명 패턴보다 낫다는 점을 확실히 보여준다.
- 자바 프로그래머라면 자바가 제공하는 애너테이션 타입들을 사용해야 한다.