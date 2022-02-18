package study.heejin.chapter2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

class Item9Test {

    @Test
    @DisplayName("숨겨진 스택을 추적하는지 테스트")
    void suppressed() {
        IOException ioe = new IOException();
        ioe.addSuppressed(new ArrayIndexOutOfBoundsException());
        ioe.addSuppressed(new IllegalStateException());

        Throwable throwable = copyContents(ioe, new Throwable());
        Throwable[] suppressed = throwable.getSuppressed();

        System.out.println("Throwable length = " + suppressed.length);
        Arrays.stream(suppressed).forEach(t -> {
            System.out.println("Throwable : " + t);
        });

    }

    private static <T extends Throwable> T copyContents(final Exception source, final T throwable) {
        throwable.setStackTrace(source.getStackTrace());
        final Throwable[] suppressed = source.getSuppressed();
        if (suppressed != null) for (final Throwable t : suppressed) {
            throwable.addSuppressed(t);
        }
        return throwable;
    }
}
