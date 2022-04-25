package chapter6.item39;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * author : haedoang
 * date : 2022/04/22
 * description :
 */
public class Sample {
    @Test
    public static void m1() { //success
    }

    public static void m2() {
    }

    @Test
    public static void m3() {
        throw new RuntimeException(); //exec
    }

    public static void m4() {
    }

    @Test
    public void m5() {
    }

    public static void m6() {
    }

    @Test
    public static void m7() {
        throw new RuntimeException("실패");
    }

    public static void m8() {
    }

    public static void main(String[] args) throws Exception {
        int tests = 0;
        int passed = 0;

        final Class<?> sampleClass = Class.forName("chapter6.item39.Sample");
        for (Method m : sampleClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                tests++;
                try {
                    m.invoke(null);
                    passed++;
                } catch (InvocationTargetException wrappedExc) {
                    Throwable exc = wrappedExc.getCause();
                    System.out.println(m + " 실패 " + exc);
                } catch (Exception e) {
                    System.out.println("잘못 사용한 @Test: " + m);
                }
            }
        }
        System.out.printf("성공: %d, 실패: %d%n", passed, tests - passed);
    }
}
