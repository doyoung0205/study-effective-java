package me.doyoung.studyeffectivejava.chapter3.item18;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class InstrumentedHashSetTest {

    @DisplayName("상속을 잘못 사용예시: 3개의 원소를 추가한 경우, 추가한 갯수가 6이 된다.")
    @Test
    void getAddCount() {
        InstrumentedHashSet<String> s = new InstrumentedHashSet<>();
        s.addAll(List.of("틱", "탁탁", "펑"));
        assertNotEquals(s.getAddCount(), 3);
    }
}
