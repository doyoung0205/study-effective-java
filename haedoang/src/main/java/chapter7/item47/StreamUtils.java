package chapter7.item47;

import java.util.stream.Stream;

/**
 * author : haedoang
 * date : 2022/05/06
 * description :
 */
public class StreamUtils {
    private StreamUtils() {
    }

    public static <E> Iterable<E> iterableOf(Stream<E> stream) {
        return stream::iterator;
    }
}
