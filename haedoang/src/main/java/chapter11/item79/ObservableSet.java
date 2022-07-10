package chapter11.item79;

import chapter4.item18.ForwardingSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * author : haedoang
 * date : 2022/07/10
 * description :
 */
public class ObservableSet<E> extends ForwardingSet<E> {
    public ObservableSet(Set<E> s) {
        super(s);
    }

    private final List<SetObserver<E>> observers
            = new ArrayList<>();

    public void addObserver(SetObserver<E> observer) {
        System.out.println(Thread.currentThread().getName() + " addObserver invoke");
        synchronized (observers) {
            observers.add(observer);
        }
    }

    public boolean removeObserver(SetObserver<E> observer) {
        System.out.println(Thread.currentThread().getName() + " removeObserver invoke");
        synchronized (observers) {
            return observers.remove(observer);
        }
    }

    private void notifyElementAdded(E element) {
        System.out.println(Thread.currentThread().getName() + " notifyElementAdded invoke");
        synchronized (observers) {
            for (SetObserver<E> observer : observers) {
                observer.added(this, element);
            }
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
