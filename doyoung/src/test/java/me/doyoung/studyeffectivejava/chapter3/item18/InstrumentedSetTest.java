package me.doyoung.studyeffectivejava.chapter3.item18;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InstrumentedSetTest {

    @DisplayName("컴포지션을 사용하여 상속의 메서드 재정의 문제를 해결할 수 있다.")
    @Test
    void name() {
        InstrumentedSet<String> s = new InstrumentedSet<>(new HashSet<>());
        s.addAll(List.of("틱", "탁탁", "펑"));
        assertEquals(s.getAddCount(), 3);
    }
}
