package chapter11.item79;

import chapter4.item18.ForwardingSet;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * author : haedoang
 * date : 2022/07/11
 * description :
 */
public class BestObservableSet<E> extends ForwardingSet<E> {
    public BestObservableSet(Set<E> s) {
        super(s);
    }

    private final List<BestSetObserver<E>> observers
            = new CopyOnWriteArrayList<>();

    public void addObserver(BestSetObserver<E> observer) {
        System.out.println(Thread.currentThread().getName() + " addObserver invoke");
        observers.add(observer);
    }

    public boolean removeObserver(BestSetObserver<E> observer) {
        System.out.println(Thread.currentThread().getName() + " removeObserver invoke");
        return observers.remove(observer);
    }

    private void notifyElementAdded(E element) {
        System.out.println(Thread.currentThread().getName() + " notifyElementAdded invoke");
        for (BestSetObserver<E> observer : observers) {
            observer.added(this, element);
        }
    }

    @Override
    public boolean add(E e) {
        boolean added = super.add(e);
        if (added) {
            notifyElementAdded(e);
        }

        return added;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = false;

        for (E element : c) {
            result |= add(element);
        }

        return result;
    }
}
