package chapter3.item13;

import java.util.Arrays;

/**
 * author : haedoang
 * date : 2022/02/25
 * description :
 */
public class Queue {
    private Object[] elements;
    private int index;

    public Queue(int size) {
        elements = new Object[size];
    }

    public Queue(Queue queue) {
        elements = queue.elements.clone();
        index = queue.index;
    }

    public static Queue newInstance(Queue queue) {
        return new Queue(queue);
    }

    public void push(Object obj) throws IllegalAccessException {
        if (index == elements.length) {
            throw new IllegalAccessException();
        }
        elements[index++] = obj;
    }

    public Object pop() {
        Object result = elements[0];
        elements = Arrays.copyOfRange(elements, 1, elements.length + 1);
        return result;
    }


}
