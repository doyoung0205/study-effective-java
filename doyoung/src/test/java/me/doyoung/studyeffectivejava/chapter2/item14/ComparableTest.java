package me.doyoung.studyeffectivejava.chapter2.item14;

import me.doyoung.studyeffectivejava.chapter2.item14.code.ComparableClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ComparableTest {

    @DisplayName("Comparable 을 구현한 객체(a)들은 Arrays.sort(a) 로 손쉽게 정렬 가능하다.")
    @Test
    void sort() {

        // given
        final ComparableClass c1 = new ComparableClass(1);
        final ComparableClass c2 = new ComparableClass(2);
        final ComparableClass c3 = new ComparableClass(3);
        final ComparableClass c4 = new ComparableClass(4);
        ComparableClass[] cArr = {c3, c1, c4, c2};

        // when
        Arrays.sort(cArr);

        // then
        assertArrayEquals(cArr, new ComparableClass[]{c1, c2, c3, c4});
    }
}
