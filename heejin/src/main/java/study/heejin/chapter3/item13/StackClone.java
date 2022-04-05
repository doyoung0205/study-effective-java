package study.heejin.chapter3.item13;

import java.util.Arrays;
import java.util.EmptyStackException;

public class StackClone implements Cloneable {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public StackClone() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
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
        elements[size] = null; // 다 쓴 참조 해제
        return result;
    }

    /**
     * 원소를 위한 공간을 적어도 하나 이상 확보한다.
     * 배열 크기를 늘려야 할 때마다 대략 두 배씩 늘린다.
     */
    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    @Override
    public StackClone clone() {
        try {
            StackClone result = (StackClone) super.clone();
            result.elements = elements.clone();
            return result;

        } catch (CloneNotSupportedException e) {
            throw new AssertionError("CloneNotSupportedException 발생!");
        }
    }

    public Object[] getElements() {
        return elements;
    }

    public int getSize() {
        return size;
    }
}