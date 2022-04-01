package chapter5.item30;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * author : haedoang
 * date : 2022/04/01
 * description :
 */
public class CollectionUtil {
    private static UnaryOperator<Object> IDENTITY_FN = (t) -> t;

    private CollectionUtil() {
    }

    public static List union(List s1, List s2) {
        List result = new ArrayList<>(s1);
        result.addAll(s2);

        return result;
    }

    public static <E> List<E> smartUnion(List<E> e1, List<E> e2) {
        ArrayList<E> result = new ArrayList<>(e1);
        result.addAll(e2);

        return result;
    }

    public static <E> List<E> verySmartUnion(List<? extends E> e1, List<? extends E> e2) {
        ArrayList<E> result = new ArrayList<>(e1);
        result.addAll(e2);

        return result;
    }


    @SuppressWarnings("unchecked")
    public static <T> UnaryOperator<T> identityFunction() {
        return (UnaryOperator<T>) IDENTITY_FN;
    }

    public static <E extends Comparable<E>> E max(Collection<E> c) {

        if (c.isEmpty()) {
            throw new IllegalArgumentException("컬렉션이 비어 있습니다.");
        }

        E result = null;

        for (E e : c) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }

        return result;
    }

    public static <E extends Comparable<? super E>> E smartMax(Collection<? extends E> c) {

        if (c.isEmpty()) {
            throw new IllegalArgumentException("컬렉션이 비어 있습니다.");
        }

        E result = null;

        for (E e : c) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }

        return result;
    }

    public static void swap(List<?> list, int i, int j) {
        swapHelper(list, i, j);
    }

    private static <E> void swapHelper(List<E> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }
}