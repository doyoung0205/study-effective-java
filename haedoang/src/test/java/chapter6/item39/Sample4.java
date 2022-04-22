package chapter6.item39;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * author : haedoang
 * date : 2022/04/22
 * description :
 */
public class Sample4 {

    @RepeatableExceptionTest(IndexOutOfBoundsException.class)
    @RepeatableExceptionTest(NullPointerException.class)
    public static void doublyBad() {
        List<String> list = new ArrayList<>();
        list.addAll(5, null);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        int tests = 0;
        int passed = 0;

        final Class<?> sample2Class = Class.forName("chapter6.item39.Sample4");
        for (Method m : sample2Class.getDeclaredMethods()) {
            if (m.isAnnotationPresent(RepeatableExceptionTest.class) || m.isAnnotationPresent(ExceptionTestContainer.class)) {
                tests++;
                try {
                    m.invoke(null);
                    System.out.printf("테스트 %s 실패: 예외를 던지지 않음%n", m);
                } catch (Throwable wrapperExc) {
                    final Throwable exc = wrapperExc.getCause();
                    int oldPassed = passed;
                    final RepeatableExceptionTest[] excTests = m.getAnnotationsByType(RepeatableExceptionTest.class);
                    for (RepeatableExceptionTest excTest : excTests) {
                        if (excTest.value().isInstance(exc)) {
                            passed++;
                            break;
                        }
                    }
                    if (passed == oldPassed) {
                        System.out.printf("테스트 %s 실패: %s %n", m, exc);
                    }
                }
            }
        }
        System.out.printf("성공: %d, 실패: %d%n", passed, tests - passed);
    }
}
