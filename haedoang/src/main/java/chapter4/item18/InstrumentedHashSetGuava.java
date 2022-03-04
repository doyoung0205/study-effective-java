package chapter4.item18;

import java.util.Collection;
import java.util.Set;

/**
 * packageName : chapter4.item18 <br/>
 * fileName : Child <br/>
 * author : haedoang <br/>
 * date : 2022-03-04 <br/>
 * description : <a href="https://github.com/google/guava/wiki/CollectionHelpersExplained">구아바 CollectionHelpers</a>
 */
public class InstrumentedHashSetGuava<E> extends com.google.common.collect.ForwardingSet<E> {
    private int addCount = 0;
    private final Set<E> delegate;

    public InstrumentedHashSetGuava(Set<E> s) {
        this.delegate = s;
    }

    @Override
    protected Set<E> delegate() {
        return delegate;
    }

    @Override
    public boolean add(E element) {
        System.out.println("invoke add()");
        addCount++;
        return super.add(element);
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        System.out.println("invoke addAll()");
        addCount += collection.size();
        return super.addAll(collection);
    }

    public int getAddCount() {
        return addCount;
    }
}
