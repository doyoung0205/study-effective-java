package chapter4.item18;


import java.util.Collection;
import java.util.Set;

/**
 * packageName : chapter4.item18
 * fileName : InstrumentedHashSetNew
 * author : haedoang
 * date : 2022-03-04
 * description :
 */
public class InstrumentedHashSetNew<E> extends ForwardingSet<E> {
    private int addCount = 0;

    public InstrumentedHashSetNew(Set<E> s) {
        super(s);
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
