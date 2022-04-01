package chapter5.item31;

import java.util.Arrays;
import java.util.Collection;
import java.util.EmptyStackException;
import java.util.Objects;

/**
 * author : haedoang
 * date : 2022/04/01
 * description :
 */
public class Stack<E> {
    private E[] elements;
    private int size = 0;
    public static final int DEFAULT_INITIAL_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public Stack() {
        this.elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }

        E result = elements[--size];
        elements[size] = null; //다쓴 객체 참조해제

        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    public void pushAll(Iterable<E> src) {
        for (E e : src) {
            push(e);
        }
    }

    public void smartPushAll(Iterable<? extends E> src) {
        for (E e : src) {
            push(e);
        }
    }

    public void popAll(Collection<E> dst) {
        while (isEmpty()) {
            dst.add(pop());
        }
    }

    public void smartPopAll(Collection<? super E> dst) {
        while (isEmpty()) {
            dst.add(pop());
        }
    }

    private boolean isEmpty() {
        return this.size() != 0;
    }

    public int size() {
        return (int) Arrays.stream(elements)
                .filter(Objects::nonNull)
                .count();
    }
}
