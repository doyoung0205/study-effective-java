package study.heejin.chapter4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.heejin.chapter4.item15.ArrayCopy;

import java.util.Arrays;

class Item15Test {

    @Test
    @DisplayName("public ststic final 배열 필드 보안 허점")
    void publicArray() {
        System.out.println("------- origin -------");
        Arrays.stream(ArrayCopy.PUBLIC_VALUES).forEach(System.out::println);

        ArrayCopy.PUBLIC_VALUES[0] = 6;
        ArrayCopy.PUBLIC_VALUES[1] = 7;
        ArrayCopy.PUBLIC_VALUES[2] = 8;
        ArrayCopy.PUBLIC_VALUES[3] = 9;
        ArrayCopy.PUBLIC_VALUES[4] = 10;

        System.out.println("------- public static final array modify -------");
        Arrays.stream(ArrayCopy.PUBLIC_VALUES).forEach(System.out::println);
    }

    @Test
    @DisplayName("private ststic final 배열 필드 - 방법 1")
    void privateArray() {
        // 컴파일 에러
        // ArrayCopy.VALUES[0] = 6;
        // ArrayCopy.VALUES[1] = 7;
        // ArrayCopy.VALUES[2] = 8;

        Arrays.stream(ArrayCopy.values()).forEach(value -> {
            System.out.println(++value);
        });
        System.out.println("------- private static final array modify -------");
        Arrays.stream(ArrayCopy.values()).forEach(System.out::println);
    }
}
