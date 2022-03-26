package chapter5.item28;

/**
 * packageName : chapter4.item28
 * fileName : CollectionUtil
 * author : haedoang
 * date : 2022-03-24
 * description :
 */
public class CollectionUtil {
    private CollectionUtil() {
    }

    public static <T> T[] unsafe(T... elements) {
        return elements;
    }

    public static <T> T[] broken(T seed) {
        T[] plant = unsafe(seed, seed, seed);

        return plant;
    }
}
