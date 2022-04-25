package study.heejin.chapter6.item39;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunExTests {

    // args = study.heejin.chapter6.item39.SampleEx
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException {
        int tests = 0;
        int passed = 0;
        Class<?> testClass = Class.forName(args[0]);

        for (Method m : testClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(ExceptionTest.class)) {
                tests++;

                try {
                    m.invoke(null);
                    System.out.printf("테스트 %s 실패: 예외를 던지지 않음%n", m);

                } catch (InvocationTargetException wrappedEx) {
                    Throwable exc = wrappedEx.getCause();
                    Class<? extends Throwable> excType = m.getAnnotation(ExceptionTest.class).value();

                    if (excType.isInstance(exc)) {
                        passed++;
                    } else {
                        System.out.printf("테스트 %s 실패: 기대한 예외 %s, 발생한 예외 %s%n", m, excType.getName(), exc);
                    }
                }
            }
        }
    }
}
