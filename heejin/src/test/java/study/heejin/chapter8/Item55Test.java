package study.heejin.chapter8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

class Item55Test {

    @Test
    @DisplayName("Optional.of(value) 에 null을 넣으면 NullpointException을 던진다")
    void optional() {

        assertThrows(NullPointerException.class, () -> Optional.of(null));

    }
}
