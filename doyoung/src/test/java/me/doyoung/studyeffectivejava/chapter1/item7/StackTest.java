package me.doyoung.studyeffectivejava.chapter1.item7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Stack 메모리 누수 예시")
class StackTest {

    @Test
    @DisplayName("[메모리 누수] EmptyStackException 이 발생한 이후에도" +
            " Stack 요소들이 전부 존재한다.")
    void pop() {
        Stack stack = new Stack();
        String[] args = {"A", "B", "C", "D", "E", "F"};
        for (String arg : args) {
            stack.push(arg);
        }

        assertThrows(EmptyStackException.class, () -> {
            while (true)
                System.err.println(stack.pop());
        });

        for (int i = 0; i < args.length; i++) {
            assertNotNull(stack.getElements()[i]);
            assertEquals(stack.getElements()[i], args[i]);
        }

    }

    @Test
    @DisplayName("[메모리 누수 Null 로 해결] EmptyStackException 이 발생한 이후에" +
            " Stack 요소들이 존재하지 않는다.")
    void popAndUnreference() {
        Stack stack = new Stack();
        String[] args = {"A", "B", "C", "D", "E", "F"};
        for (String arg : args) {
            stack.push(arg);
        }

        assertThrows(EmptyStackException.class, () -> {
            while (true)
                System.err.println(stack.popAndUnreference());
        });

        assertNull(stack.getElements()[0]);
    }
}
