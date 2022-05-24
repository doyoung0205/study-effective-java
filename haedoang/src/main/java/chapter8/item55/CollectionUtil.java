package chapter8.item55;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

/**
 * packageName : chapter8.item55
 * fileName : CollectionUtil
 * author : haedoang
 * date : 2022-05-24
 * description :
 */
public class CollectionUtil {
    private CollectionUtil() {
    }

    public static <E extends Comparable<E>> Optional<E> max(Collection<E> e) {
        if (e.isEmpty()) {
            return Optional.empty();
        }

        E result = null;

        for (E element : e) {
            if (result == null || element.compareTo(result) > 0) {
                result = Objects.requireNonNull(element);
            }
        }

        return Optional.of(result);
    }
}
