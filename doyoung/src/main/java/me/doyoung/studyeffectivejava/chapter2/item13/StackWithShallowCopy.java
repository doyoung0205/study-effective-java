package me.doyoung.studyeffectivejava.chapter2.item13;

import java.util.Arrays;

// Stack의 복제 가능 버전 (80-81쪽)
public class StackWithShallowCopy implements Cloneable {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public StackWithShallowCopy() {
        this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        Object result = elements[--size];
        elements[size] = null; // 다 쓴 참조 해제
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 코드 13-2 가변 상태를 참조하는 클래스용 clone 메서드
    @Override
    public StackWithShallowCopy clone() {
        try {
            StackWithShallowCopy result = (StackWithShallowCopy) super.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    // 원소를 위한 공간을 적어도 하나 이상 확보한다.
    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }


}
