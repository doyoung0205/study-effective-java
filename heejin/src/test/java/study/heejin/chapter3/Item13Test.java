package study.heejin.chapter3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.heejin.chapter3.item13.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Item13Test {

    @Test
    @DisplayName("Object clone 명세 테스트")
    void cloneExample() {
        CloneableExample x = new CloneableExample(100);

        assertThat(x.clone() != x).isTrue();
        assertThat(x.clone().getClass() == x.getClass()).isTrue();
        assertThat(x.clone().equals(x)).isTrue();
    }

    @Test
    @DisplayName("참조형 타입의 잘못된 복제")
    void stackBadClone() throws CloneNotSupportedException {
        StackBadClone stack = new StackBadClone();
        stack.push("A");
        stack.push("B");
        stack.push("C");

        StackBadClone clone = stack.clone();
        clone.pop();

        System.out.println("==== stack ====");
        while (stack.getSize() != 0) {
            System.out.println(stack.pop());
        }

        System.out.println("==== clone ====");
        while (clone.getSize() != 0) {
            System.out.println(clone.pop() + " ");
        }
    }

    @Test
    @DisplayName("참조형 타입의 재귀적 복제")
    void stackClone() {
        StackClone stack = new StackClone();
        stack.push("A");
        stack.push("B");
        stack.push("C");

        StackClone clone = stack.clone();

        System.out.println("==== stack ====");
        while (stack.getSize() != 0) {
            System.out.println(stack.pop());
        }

        System.out.println("==== clone ====");
        while (clone.getSize() != 0) {
            System.out.println(clone.pop() + " ");
        }
    }

    @Test
    @DisplayName("연결 리스트의 재귀적 복사 실패")
    void hashTableBadClone() {
        HashTableBadClone hashTable = new HashTableBadClone();
        hashTable.put(0, "A");
        hashTable.put(1, "B");
        hashTable.put(2, "C");

        HashTableBadClone clone = hashTable.clone();
        clone.put(2, "D");

        System.out.println(hashTable);
        System.out.println(clone);

        assertThat(hashTable.get(2)).isEqualTo("C");
        assertThat(clone.get(2)).isEqualTo("D");

        assertThat(hashTable.getNext(1)).isEqualTo("D"); // linked list 가 변경됨
        assertThat(clone.getNext(1)).isEqualTo("D");
    }

    @Test
    @DisplayName("연결 리스트의 깊은 복사")
    void hashTableClone() {
        HashTableClone hashTable = new HashTableClone();
        hashTable.put(0, "A");
        hashTable.put(1, "B");
        hashTable.put(2, "C");

        HashTableClone clone = hashTable.clone();
        clone.put(2, "D");

        System.out.println(hashTable);
        System.out.println(clone);

        assertThat(hashTable.get(2)).isEqualTo("C");
        assertThat(clone.get(2)).isEqualTo("D");

        assertThat(hashTable.getNext(1)).isEqualTo("C"); // linked list 가 변경되지 않음
        assertThat(clone.getNext(1)).isEqualTo("D");
    }
}
