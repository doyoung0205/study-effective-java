package study.heejin.chapter4.item15;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayCopy {
    public static final Integer[] PUBLIC_VALUES = {1, 2, 3, 4, 5};

    private static final Integer[] PRIVATE_VALUES = {1, 2, 3, 4, 5};
    public static final List<Integer> VALUES = Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));

    public static final Integer[] values() {
        return PRIVATE_VALUES.clone();
    }
}
