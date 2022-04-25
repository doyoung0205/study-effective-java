package study.heejin.chapter6.item39;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunExArrayTests {

    // args = study.heejin.chapter6.item39.SampleExArray
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException {
        int tests = 0;
        int passed = 0;

        Class<?> testClass = Class.forName(args[0]);
        for (Method m : testClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(ExceptionsTest.class)) {
                tests++;

                try {
                    m.invoke(null);
                    System.out.printf("테스트 %s 실패: 예외를 던지지 않음%n", m);

                } catch (InvocationTargetException warppedEx) {
                    Throwable exc = warppedEx.getCause();
                    int oldPassed = passed;
                    Class<? extends Throwable>[] excTypes = m.getAnnotation(ExceptionsTest.class).value();

                    for (Class<? extends Throwable> excType : excTypes) {
                        if (excType.isInstance(exc)) {
                            passed++;
                            System.out.printf("테스트 성공: 예외 - %s%n", exc);
                            break;
                        }
                    }

                    if (passed == oldPassed) {
                        System.out.printf("테스트 %s 실패: %s%n", m, exc);
                    }
                }
            }
        }
    }
}
