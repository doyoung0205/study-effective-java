package chapter4.item18;

import java.util.Collection;
import java.util.HashSet;

/**
 * packageName : chapter4.item18
 * fileName : Child
 * author : haedoang
 * date : 2022-03-04
 * description :
 */
public class InstrumentedHashSet<E> extends HashSet<E> {
    private int addCount = 0;

    public InstrumentedHashSet() {
    }

    public InstrumentedHashSet(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    @Override
    public boolean add(E e) {
        System.out.println("invoke add()");
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        System.out.println("invoke addAll()");
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }
}
