package me.doyoung.studyeffectivejava.chapter8.item49;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AssertTest {

    @DisplayName("assert 문의 실패하면 AssertionError 가 발생한다.")
    @Test
    void assertTest() {
        assertThrows(AssertionError.class, () -> {
            assert false;
        });
    }

    @DisplayName("requireNonNull 은 null 일 결우 NullPointerException 가 발생한다.")
    @Test
    void requireNonNull() {
        assertThrows(NullPointerException.class, () -> {
            Objects.requireNonNull(null);
        });
    }

    @Test
    void checkFromIndexSize() {
        final int fromIndex = 0;
        final int size = 10;
        final int length = 1;
        assertThrows(IndexOutOfBoundsException.class, () -> Objects.checkFromIndexSize(fromIndex, size, length));

    }

    @Test
    void checkIndex() {
        final int index = 10;
        final int size = 1;
        assertThrows(IndexOutOfBoundsException.class, () -> Objects.checkIndex(index, size));
    }
}
