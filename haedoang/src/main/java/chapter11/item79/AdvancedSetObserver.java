package chapter11.item79;

/**
 * author : haedoang
 * date : 2022/07/11
 * description :
 */
@FunctionalInterface
public interface AdvancedSetObserver<E> {
    void added(AdvancedObservableSet<E> set, E element);
}