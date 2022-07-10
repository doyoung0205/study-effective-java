package chapter11.item79;


/**
 * author : haedoang
 * date : 2022/07/11
 * description :
 */
@FunctionalInterface
public interface BestSetObserver<E> {
    void added(BestObservableSet<E> set, E element);
}
