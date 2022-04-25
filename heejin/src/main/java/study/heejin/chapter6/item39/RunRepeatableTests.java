package study.heejin.chapter6.item39;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunRepeatableTests {

    // args = study.heejin.chapter6.item39.SampleRepeatable
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException {
        int tests = 0;
        int passed = 0;

        Class<?> testClass = Class.forName(args[0]);
        for (Method m : testClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(RepeatableExcptionTest.class) | m.isAnnotationPresent(ExceptionTestContainer.class)) {
                tests++;

                try {
                    m.invoke(null);
                    System.out.printf("테스트 %s 실패: 예외를 던지지 않음 %n", m);

                } catch (InvocationTargetException wrappedExc) {
                    Throwable exc = wrappedExc.getCause();
                    int oldPassed = passed;

                    RepeatableExcptionTest[] excTests = m.getAnnotationsByType(RepeatableExcptionTest.class);
                    for (RepeatableExcptionTest excTest : excTests) {
                        if (excTest.value().isInstance(exc)) {
                            passed++;
                            System.out.printf("테스트 성공: 예외 - %s%n", exc);
                            break;
                        }
                    }

                    if (oldPassed == passed) {
                        System.out.printf("테스트 %s 실패: %s%n", m, exc);
                    }
                }
            }
        }
    }
}
