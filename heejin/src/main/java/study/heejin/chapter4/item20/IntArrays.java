package study.heejin.chapter4.item20;

import java.util.AbstractList;
import java.util.List;
import java.util.Objects;

public class IntArrays {

    public static List<Integer> intArrayAsList(int[] a) {
        Objects.requireNonNull(a);

        return new AbstractList<Integer>() {
            @Override
            public Integer get(int index) {
                return a[index]; // 오토박싱
            }

            @Override
            public Integer set(int index, Integer element) {
                int oldElement = a[index];
                a[index] = element; // 오토언박싱
                return oldElement; // 오토박싱
            }

            @Override
            public int size() {
                return a.length;
            }
        };
    }
}
