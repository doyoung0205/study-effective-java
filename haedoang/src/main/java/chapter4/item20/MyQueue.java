package chapter4.item20;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * author : haedoang
 * date : 2022/03/08
 * description :
 */
public class MyQueue<T> extends AbstractQueue<T> {
    private final LinkedList<T> elements;

    public MyQueue() {
        this.elements = new LinkedList<>();
    }

    @Override
    public Iterator<T> iterator() {
        return elements.iterator();
    }

    @Override
    public int size() {
        return elements.size();
    }

    /**
     * 용량 제한을 위반하지 않고 즉시 수행할 수 있는 경우 지정된 요소를 이 대기열에 삽입합니다.<br/>
     * 용량 제한 대기열을 사용할 때 일반적으로 이 방법을 추가하는 것이 좋으며 예외를 throw해야만 요소를 삽입하지 못할 수 있습니다.
     * AbstractQueue 가 Queue의 add메서드를 구현하였음. add 메서드 호출할 때 offer 메서드를 호출함을 알아두자
     */
    @Override
    public boolean offer(T t) {
        if (t == null) {
            return false;
        }
        elements.add(t);
        return true;
    }

    /**
     *
     * 이 대기열의 헤드를 검색 및 제거하거나 이 대기열이 비어 있으면 null을 반환합니다. <br/>
     * 반환값: 이 큐의 선두. 이 큐가 비어 있으면 null
     */
    @Override
    public T poll() {
        Iterator<T> iter = elements.iterator();
        T t = iter.next();
        if (t != null) {
            iter.remove();
            return t;
        }
        return null;
    }

    @Override
    public T peek() {
        return elements.getFirst();
    }
}
