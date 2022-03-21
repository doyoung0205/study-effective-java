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

    /**
     * 배열을 복사하는 메서드입니다. <br/>
     * 같은 타입의 배열을 복사하기 때문에 타입의 안정성을 보장합니다 <br/>
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T[] arrayCopy(T[] t) {
        @SuppressWarnings("unchecked")
        final T[] copiedArray = (T[]) Arrays.copyOf(t, t.length, t.getClass());
        return copiedArray;
    }
}
