package chapter5.item29;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Objects;

/**
 * packageName : chapter5.item28
 * fileName : Stack
 * author : haedoang
 * date : 2022-03-24
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

    public int elementsLength() {
        return elements.length;
    }

    public long nonNullElementsSize() {
        return Arrays.stream(elements)
                .filter(Objects::nonNull)
                .count();
    }
}
