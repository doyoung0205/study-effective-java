package me.doyoung.studyeffectivejava.chapter6.item39.annotationwitharrayparameter;

import java.util.ArrayList;
import java.util.List;

// 배열 매개변수를 받는 애너테이션을 사용하는 프로그램 (242-243쪽)
public class Sample3 {
    // 이 변형은 원소 하나짜리 매개변수를 받는 애너테이션도 처리할 수 있다. (241쪽 Sample2와 같음)
    @ExceptionTest(ArithmeticException.class)
    public static void m1() {  // 성공해야 한다.
        int i = 0;
        i = i / i;
    }
    @ExceptionTest(ArithmeticException.class)
    public static void m2() {  // 실패해야 한다. (다른 예외 발생)
        int[] a = new int[0];
        int i = a[1];
    }
    @ExceptionTest(ArithmeticException.class)
    public static void m3() { }  // 실패해야 한다. (예외가 발생하지 않음)

    // 코드 39-7 배열 매개변수를 받는 애너테이션을 사용하는 코드 (242-243쪽)
    @ExceptionTest({ IndexOutOfBoundsException.class,
                     NullPointerException.class })
    public static void doublyBad() {   // 성공해야 한다.
        List<String> list = new ArrayList<>();

        // 자바 API 명세에 따르면 다음 메서드는 IndexOutOfBoundsException이나
        // NullPointerException을 던질 수 있다.
        list.addAll(5, null);
    }
}
