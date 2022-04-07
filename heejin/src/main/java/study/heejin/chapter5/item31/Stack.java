package study.heejin.chapter5.item31;

import java.util.*;

public class Stack<E> {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private E[] elements;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public Stack() {
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e) {
        ensureCapatity();
        elements[size++] = e;
    }

    private void ensureCapatity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }

        E result = elements[--size];
        elements[size] = null;
        return result;
    }

    public void pushAll(Iterable<? extends E> src) {
        for (E e : src) {
            push(e);
        }
    }

    public void popAll(Collection<? super E> dst) {
        while (!isEmpty()) {
            dst.add(pop());
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        Stack<Number> numberStack = new Stack<>();
        Iterable<Integer> integers = Arrays.asList(3, 1, 4, 1, 5, 9);
        numberStack.pushAll(integers);

        Collection<Object> objects = new ArrayList<>();
        numberStack.popAll(objects);

        System.out.println(objects);
    }
}
