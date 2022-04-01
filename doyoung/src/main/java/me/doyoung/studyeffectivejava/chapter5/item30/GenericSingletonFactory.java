package me.doyoung.studyeffectivejava.chapter5.item30;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.UnaryOperator;

// 제네릭 싱글턴 팩터리 패턴 (178쪽)
public class GenericSingletonFactory {
    // 코드 30-4 제네릭 싱글턴 팩터리 패턴 (178쪽)
    private static UnaryOperator<Object> IDENTITY_FN = (t) -> t;

    @SuppressWarnings("unchecked")
    public static <T> UnaryOperator<T> identityFunction() {
        return (UnaryOperator<T>) IDENTITY_FN;
    }

    // 코드 30-5 제네릭 싱글턴을 사용하는 예 (178쪽)
    public static void main(String[] args) {
        String[] strings = { "삼베", "대마", "나일론" };
        UnaryOperator<String> sameString = identityFunction();
        for (String s : strings)
            System.out.println(sameString.apply(s));

        Number[] numbers = { 1, 2.0, 3L };
        UnaryOperator<Number> sameNumber = identityFunction();
        for (Number n : numbers)
            System.out.println(sameNumber.apply(n));

    }
}
