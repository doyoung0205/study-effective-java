### 명명 패턴보다 애너테이션을 사용하라

#### junit3 매커니즘의 단점

- 메서드 명이 test 로 시작해야하는데 오타가 나면 안된다.
- 올바른 프로그램 요소에서만 사용되리라 보증할 방법이 없다.
- 프로그램 요소를 매개변수로 전달할 마땅한 방법이 없다.

#### 따라서 애너테이션

```java
/**
 * 테스트 메서드임을 선언하는 애너테이션이다.
 * 매개변수 없는 정적 메서드 전용이다.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
}

```

- 메타 어노테이션: 애너테이션 선언에 다는 애너테이션 ex. Retention, Target


```java

public class RunTests {
    public static void main(String[] args) throws Exception {
        int tests = 0;
        int passed = 0;
        Class<?> testClass = Class.forName(args[0]);
        for (Method m : testClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                tests++;
                try {
                    m.invoke(null);
                    passed++;
                } catch (InvocationTargetException wrappedExc) {
                    Throwable exc = wrappedExc.getCause();
                    System.out.println(m + " 실패: " + exc);
                } catch (Exception exc) {
                    System.out.println("잘못 사용한 @Test: " + m);
                }
            }
        }
        System.out.printf("성공: %d, 실패: %d%n",
                passed, tests - passed);
    }
}


```
