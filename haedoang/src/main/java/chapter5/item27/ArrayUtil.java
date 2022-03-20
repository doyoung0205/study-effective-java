package chapter5.item27;

import java.util.Arrays;

/**
 * author : haedoang
 * date : 2022/03/19
 * description :
 */
public class ArrayUtil {
    private ArrayUtil() {
    }

    public static <T> T[] arrayCopy(T[] t) {
//        @SuppressWarnings("unchecked")
        final T[] copiedArray = (T[]) Arrays.copyOf(t, t.length, t.getClass());
        return copiedArray;
    }
}
