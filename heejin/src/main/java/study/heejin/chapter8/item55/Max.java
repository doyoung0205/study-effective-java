package study.heejin.chapter8.item55;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

public class Max {

    public static <E extends Comparable<E>> E maxException(Collection<E> c) {
        if (c.isEmpty()) {
            throw new IllegalArgumentException("빈 컬렉션");
        }

        E reuslt = null;
        for (E e : c) {
            if (reuslt == null || e.compareTo(reuslt) > 0) {
                reuslt = Objects.requireNonNull(e);
            }
        }
        return reuslt;
    }

    public static <E extends Comparable<E>> Optional<E> maxOptional(Collection<E> c) {
        if (c.isEmpty()) {
            return Optional.empty();
        }

        E result = null;
        for (E e : c) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }
        return Optional.of(result);
    }

    public static <E extends Comparable<E>> Optional<E> maxStream(Collection<E> c) {
        return c.stream()
                .max(Comparator.naturalOrder());
    }
}
