package me.doyoung.studyeffectivejava.chapter2.item13;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class StackTest {

    @DisplayName("super.clone 으로는 해당 필드까지 복사되지 않는다. " +
            " 따라서 원본이 수정되면 clone 된 객체의 필드도 수정된다.")
    @Test
    void stackWithShallowCopy() {

        // given
        StackWithShallowCopy stackWithShallowCopy = new StackWithShallowCopy();
        String[] args = {"A", "B", "C"};
        for (String arg : args) {
            stackWithShallowCopy.push(arg);
        }

        // when
        StackWithShallowCopy copy = stackWithShallowCopy.clone();
        System.out.print("original:: ");
        while (!stackWithShallowCopy.isEmpty()) {
            System.out.print(stackWithShallowCopy.pop() + " ");
        }
        System.out.println();

        // then
        System.out.print("copy:: ");
        while (!copy.isEmpty()) {
            final Object pop = copy.pop();
            System.out.println(pop + " ");
            assertNull(pop);
        }
    }

    @DisplayName("해당 필드의 값 또한 clone 을 해주면 " +
            "원본이 변경되도 clone 객체의 필드가 변경되지 않는다.")
    @Test
    void stackWithDeepCopy() {

        // given
        StackWithDeepCopy stackWithDeepCopy = new StackWithDeepCopy();
        String[] args = {"A", "B", "C"};
        for (String arg : args) {
            stackWithDeepCopy.push(arg);
        }

        // when
        StackWithDeepCopy copy = stackWithDeepCopy.clone();
        System.out.print("original:: ");
        while (!stackWithDeepCopy.isEmpty()) {
            System.out.print(stackWithDeepCopy.pop() + " ");
        }
        System.out.println();

        // then
        System.out.print("copy:: ");
        while (!copy.isEmpty()) {
            final Object pop = copy.pop();
            System.out.print(pop + " ");
            assertNotNull(pop);
        }
    }
}
