package me.doyoung.studyeffectivejava.chapter1.item9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SuppressedTest {

    @Test
    void suppressedTest() {

        final Throwable throwable = new Throwable();
        throwable.addSuppressed(new IllegalArgumentException("1"));
        throwable.addSuppressed(new IllegalArgumentException("2"));
        throwable.addSuppressed(new IllegalArgumentException("3"));
        throwable.addSuppressed(new IllegalArgumentException("4"));

        try {
            throw throwable;
        } catch (Throwable e) {
            e.printStackTrace();
            final Throwable[] suppressed = e.getSuppressed();
            assertEquals(suppressed.length, 4);
        }
    }
}
