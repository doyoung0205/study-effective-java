package me.doyoung.studyeffectivejava.chapter2.item14;

import me.doyoung.studyeffectivejava.chapter2.item14.code.ComparableClass;
import me.doyoung.studyeffectivejava.utils.SpeedCheckUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class SpeedTest {

    public static final int END_EXCLUSIVE = 1_000;
    public static final int START_INCLUSIVE = 0;
    public static final int LAST_ID = END_EXCLUSIVE - 1;
    public static final int FIRST_ID = START_INCLUSIVE;

    List<ComparableClass> comparableClasses;

    @BeforeEach
    void setUp() {
        comparableClasses = IntStream.range(START_INCLUSIVE, END_EXCLUSIVE)
                .mapToObj(value -> new ComparableClass(value))
                .collect(Collectors.toList());
    }

    @DisplayName("Stream 에서 동적으로 Comparator 를 만들경우")
    @Test
    void test() {
        final Runnable runnable = () -> {

            assertThat(getFirstId(comparableClasses)).isEqualTo(FIRST_ID);

            final List<ComparableClass> sortedComparableClasses = comparableClasses.stream()
                    .sorted(Comparator.comparingInt(ComparableClass::getId).reversed())
                    .collect(Collectors.toList());

            assertThat(getFirstId(sortedComparableClasses)).isEqualTo(LAST_ID);
        };

        SpeedCheckUtils.speedCheck(runnable);
    }

    @DisplayName("해당 클래스에 내장되어 있는 compare 메서드에 따른 sort 메서드를 사용할 경우")
    @Test
    void test2() {
        final Runnable runnable = () -> {

            assertThat(getFirstId(comparableClasses)).isEqualTo(FIRST_ID);

            Collections.sort(comparableClasses);

            assertThat(getFirstId(comparableClasses)).isEqualTo(LAST_ID);

        };
        SpeedCheckUtils.speedCheck(runnable);

    }

    // 가장 빠름..
    @DisplayName("sort 와 Comparator 를 같이 사용할 경우")
    @Test
    void test3() {
        final Runnable runnable = () -> {

            assertThat(getFirstId(comparableClasses)).isEqualTo(FIRST_ID);

//            Collections.sort(comparableClasses, ComparableClass.COMPARATOR.reversed());
            comparableClasses.sort(ComparableClass.COMPARATOR.reversed());

            assertThat(getFirstId(comparableClasses)).isEqualTo(LAST_ID);
        };
        SpeedCheckUtils.speedCheck(runnable);
    }

    @Test
    void name() {
        final BigInteger bigInteger = BigInteger.valueOf(1212);
        System.out.println(bigInteger);
        bigInteger.clearBit(12);

        System.out.println(bigInteger);
    }

    private int getFirstId(List<ComparableClass> comparableClasses) {
        return comparableClasses.get(0).getId();
    }
}
