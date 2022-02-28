package chapter3.item13;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Objects;

/**
 * packageName : chapter3.iterm13
 * fileName : Stack
 * author : haedoang
 * date : 2022-02-25
 * description :
 */
public class Stack implements Cloneable {
    private Object[] elements;
    private int size = 0;
    public static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }

        Object result = elements[--size];
        elements[size] = null; //다쓴 객체 참조해제

        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    public int elementsLength() {
        return elements.length;
    }

    public long nonNullElementsSize() {
        return Arrays.stream(elements)
                .filter(Objects::nonNull)
                .count();
    }

    public int getElementsHashCode() {
        return elements.hashCode();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
