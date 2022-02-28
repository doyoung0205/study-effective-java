package study.heejin.chapter3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.heejin.chapter3.item14.ComparablePhoneNumber;
import study.heejin.chapter3.item14.ComparableString;
import study.heejin.chapter3.item14.ComparatorPhoneNumber;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.TreeSet;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class Item14Test {

    @Test
    @DisplayName("compareTo와 equals가 일관되지 않은 경우")
    void compareTo() {
        // HashSet인 경우에는 원소가 2개
        HashSet<BigDecimal> hashSet = new HashSet<>();
        hashSet.add(new BigDecimal("1.0"));
        hashSet.add(new BigDecimal("1.00"));

        assertThat(hashSet).hasSize(2);

        // TreeSet인 경우에는 원소가 1개
        TreeSet<BigDecimal> treeSet = new TreeSet<>();
        treeSet.add(new BigDecimal("1.0"));
        treeSet.add(new BigDecimal("1.00"));

        assertThat(treeSet).hasSize(1);
    }

    @Test
    @DisplayName("객체 참조 필드가 하나뿐인 비교자")
    void comparableString() {
        ComparableString a = new ComparableString("a");
        ComparableString b = new ComparableString("b");
        ComparableString c = new ComparableString("c");

        assertThat(a.compareTo(b)).isEqualTo(-1);
        assertThat(a.compareTo(c)).isEqualTo(-2);
        assertThat(c.compareTo(a)).isEqualTo(2);
        assertThat(c.compareTo(b)).isEqualTo(1);
    }

    @Test
    @DisplayName("Comparable 비교 속도 테스트")
    void compable() {
        final Runnable runnable = () -> {
            TreeSet<ComparablePhoneNumber> pn = new TreeSet<>();
            for (int i = 0; i < 1_000_000; i++) {
                pn.add(ComparablePhoneNumber.randomPhoneNumber());
            }
        };
        checkSpeed("Comparator = ", runnable);
    }


    @Test
    @DisplayName("Comparator 비교 속도 테스트")
    void comparator() {
        final Runnable runnable = () -> {
            TreeSet<ComparatorPhoneNumber> pn = new TreeSet<>();
            for (int i = 0; i < 1_000_000; i++) {
                pn.add(ComparatorPhoneNumber.randomPhoneNumber());
            }
        };
        checkSpeed("Comparator = ", runnable);
    }

    private void checkSpeed(String prefix, Runnable runnable) {
        long start = System.currentTimeMillis();
        runnable.run();
        long end = System.currentTimeMillis();
        System.out.println(prefix + (end - start) + " ms");
    }
}
