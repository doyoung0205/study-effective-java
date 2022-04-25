package study.heejin.chapter6.item39;

public class SampleEx {

    @ExceptionTest(ArithmeticException.class)
    public static void m1() { // 성공
        int i = 0;
        i = i / i;
    }

    @ExceptionTest(ArithmeticException.class)
    public static void m2() { // 실패 - 다른 예외 발생
        int[] a = new int[0];
        int i = a[1];
    }

    @ExceptionTest(ArithmeticException.class)
    public static void m3() { // 실패 - 예외가 발생하지 않음
    }
}
