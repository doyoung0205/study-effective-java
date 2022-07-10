package chapter11.item79;

/**
 * author : haedoang
 * date : 2022/07/10
 * description :
 */
@FunctionalInterface
public interface SetObserver<E> {
    void added(ObservableSet<E> set, E element);
}
