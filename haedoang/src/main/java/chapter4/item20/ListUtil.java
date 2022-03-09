package chapter4.item20;


import org.apache.commons.collections4.keyvalue.AbstractMapEntry;

import java.util.*;

/**
 * author : haedoang
 * date : 2022/03/07
 * description :
 */
public class ListUtil {
    private ListUtil() {
    }

    public static List<Integer> intArrayAsList(int[] a) {
        Objects.requireNonNull(a);
        return new AbstractList<>() {
            @Override
            public Integer get(int index) {
                return a[index];
            }

            @Override
            public Integer set(int index, Integer element) {
                int oldVal = a[index];
                a[index] = element;
                return oldVal;
            }

            @Override
            public int size() {
                return a.length;
            }
        };
    }
}
